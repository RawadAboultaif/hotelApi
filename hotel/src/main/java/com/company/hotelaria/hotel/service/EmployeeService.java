package com.company.hotelaria.hotel.service;

import com.company.hotelaria.hotel.core.dto.employee.EmployeeRequest;
import com.company.hotelaria.hotel.core.dto.employee.EmployeeResponse;
import com.company.hotelaria.hotel.core.entities.Address;
import com.company.hotelaria.hotel.core.entities.Employee;
import com.company.hotelaria.hotel.core.mapper.AddressMapper;
import com.company.hotelaria.hotel.core.mapper.EmployeeMapper;
import com.company.hotelaria.hotel.enums.Message;
import com.company.hotelaria.hotel.repository.AddressRepository;
import com.company.hotelaria.hotel.repository.EmployeeRepository;
import com.company.hotelaria.hotel.repository.GuestRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
@Validated
@Slf4j
public class EmployeeService {

    private AddressRepository addressRepository;

    private AddressMapper addressMapper;

    private EmployeeMapper employeeMapper;

    private EmployeeRepository employeeRepository;

    private GuestRepository guestRepository;

    public List<EmployeeResponse> findAll(){
        log.info("findAll");
        return this.employeeMapper.listEntityToListResponse(this.employeeRepository.findAll());
    }

    public EmployeeResponse findBySocialSecurityNumber(String socialSecurityNumber) {
        log.info("findBySocialSecurityNumber", socialSecurityNumber);
        Employee employee = this.employeeRepository.findBySocialSecurityNumber(socialSecurityNumber)
                .orElseThrow(Message.ID_DO_NOT_EXIST::asBusinessException);
        EmployeeResponse employeeResponse = this.employeeMapper.entityToResponse(employee);
        List<Address> employeeAddressEntities = this.addressRepository.findAllByEmployeeId(employee.getId());
        employeeResponse.setEmployeeAddressEntities(employeeAddressEntities);
        return employeeResponse;
    }

    @Transactional
    public EmployeeResponse save(@Valid EmployeeRequest request){

        log.info("save request = {}", request);
        Employee employee = this.employeeMapper.requestToEntity(request);
        this.employeeRepository.findBySocialSecurityNumber(request.getSocialSecurityNumber()).ifPresent(p -> {
            throw Message.SECURITY_NUMBER_IS_PRESENT.asBusinessException();
        });
        this.guestRepository.findBySocialSecurityNumber(request.getSocialSecurityNumber()).ifPresent(p -> {
            throw Message.SECURITY_NUMBER_IS_PRESENT.asBusinessException();
        });
        Employee employeeResult = this.employeeRepository.save(employee);
        EmployeeResponse employeeResponse = this.employeeMapper.entityToResponse(employeeResult);
        List<Address> addressList = new ArrayList<>();
        for(int i = 0; i < request.getEmployeeAddress().size(); i++) {
            Address address = this.addressMapper.requestToEntity(request.getEmployeeAddress().get(i));
            Address addressResult = this.addressRepository.save(address);
            addressResult.updateEmplyee(employeeResult);
            addressList.add(addressResult);
        }
        employeeResponse.setEmployeeAddressEntities(addressList);
        return employeeResponse;
    }

    @Transactional
    public EmployeeResponse update(@Valid EmployeeRequest request, Long id){
        log.info(" update request = {}", request);
        Employee employee = this.employeeRepository.findById(id).orElseThrow(Message.ID_DO_NOT_EXIST::asBusinessException);
        List<Address> employeeAddressEntities = this.addressRepository.findAllByEmployeeId(employee.getId());
        if(!request.getSocialSecurityNumber().equalsIgnoreCase(employee.getSocialSecurityNumber())) {
            this.employeeRepository.findBySocialSecurityNumber(request.getSocialSecurityNumber()).ifPresent(p -> {
                throw Message.SECURITY_NUMBER_IS_PRESENT.asBusinessException();
            });
        }
            employee.updateEmployee(
                    request.getName(),
                    request.getRole(),
                    request.getRemuneration(),
                    request.getWorkschedule(),
                    request.getEmail(),
                    request.getSocialSecurityNumber());
        for(int i = 0; i < request.getEmployeeAddress().size(); i++) {
            if(i < employeeAddressEntities.size()) {
                employeeAddressEntities.get(i).updateAddress(
                        request.getEmployeeAddress().get(i).getStreetName(),
                        request.getEmployeeAddress().get(i).getNumber(),
                        request.getEmployeeAddress().get(i).getComplement(),
                        request.getEmployeeAddress().get(i).getCity(),
                        request.getEmployeeAddress().get(i).getState(),
                        request.getEmployeeAddress().get(i).getZipcode(),
                        request.getEmployeeAddress().get(i).getCountry()
                );
            } else {
                Address address = this.addressMapper.requestToEntity(request.getEmployeeAddress().get(i));
                Address addressResult = this.addressRepository.save(address);
                addressResult.updateEmplyee(employee);
                employeeAddressEntities.add(addressResult);
            }
        }
        EmployeeResponse employeeResponse = this.employeeMapper.entityToResponse(employee);
        employeeResponse.setEmployeeAddressEntities(employeeAddressEntities);
        return employeeResponse;
    }

    public void delete(Long id) {
        Employee guest = this.employeeRepository.findById(id).orElseThrow(Message.ID_DO_NOT_EXIST::asBusinessException);
        this.employeeRepository.deleteById(guest.getId());
        log.info("method = delete number = {}",id);
    }
}
