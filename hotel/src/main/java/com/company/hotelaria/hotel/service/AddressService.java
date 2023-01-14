package com.company.hotelaria.hotel.service;

import com.company.hotelaria.hotel.core.dto.address.AddressRequest;
import com.company.hotelaria.hotel.core.dto.address.AddressResponse;
import com.company.hotelaria.hotel.core.dto.guest.GuestRequest;
import com.company.hotelaria.hotel.core.dto.guest.GuestResponse;
import com.company.hotelaria.hotel.core.entities.Address;
import com.company.hotelaria.hotel.core.entities.Employee;
import com.company.hotelaria.hotel.core.entities.Guest;
import com.company.hotelaria.hotel.core.mapper.AddressMapper;
import com.company.hotelaria.hotel.core.mapper.GuestMapper;
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
import java.util.List;

@AllArgsConstructor
@Service
@Validated
@Slf4j
public class AddressService {

    private AddressRepository addressRepository;
    private AddressMapper addressMapper;

    private GuestRepository guestRepository;

    private EmployeeRepository employeeRepository;


    public AddressResponse findById(Long id){
        log.info("findById");
        return this.addressMapper.entityToResponse(this.addressRepository.findById(id)
                .orElseThrow(Message.ID_DO_NOT_EXIST::asBusinessException
        ));
    }

    @Transactional
    public AddressResponse saveAddressGuest(@Valid AddressRequest request, Long id){

        log.info("save request = {}", request);
        Guest guestResult = this.guestRepository.findById(id)
                .orElseThrow(Message.ID_DO_NOT_EXIST::asBusinessException);
        Address address = this.addressMapper.requestToEntity(request);
        Address addressResult = this.addressRepository.save(address);
        addressResult.updateGuest(guestResult);
        AddressResponse addressResponse = this.addressMapper.entityToResponse(addressResult);

        return addressResponse;
    }

    @Transactional
    public AddressResponse saveAddressEmployee(@Valid AddressRequest request, Long id){

        log.info("save request = {}", request);
        Employee employeeResult = this.employeeRepository.findById(id)
                .orElseThrow(Message.ID_DO_NOT_EXIST::asBusinessException);
        Address address = this.addressMapper.requestToEntity(request);
        Address addressResult = this.addressRepository.save(address);
        addressResult.updateEmplyee(employeeResult);
        AddressResponse addressResponse = this.addressMapper.entityToResponse(addressResult);

        return addressResponse;
    }

    @Transactional
    public AddressResponse update(@Valid AddressRequest request, Long id){
        log.info(" update request = {}", request);
        Address address = this.addressRepository.findById(id).orElseThrow(Message.ID_DO_NOT_EXIST::asBusinessException);
        address.updateAddress(
                request.getStreetName(),
                request.getNumber(),
                request.getComplement(),
                request.getCity(),
                request.getState(),
                request.getZipcode(),
                request.getCountry()
        );
        AddressResponse addressResponse = this.addressMapper.entityToResponse(address);
        return addressResponse;
    }

    public void delete(Long id) {
        Address address = this.addressRepository.findById(id).orElseThrow(Message.ID_DO_NOT_EXIST::asBusinessException);
        this.guestRepository.deleteById(address.getId());
        log.info("method = delete number = {}",id);
    }
}
