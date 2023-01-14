package com.company.hotelaria.hotel.service;

import com.company.hotelaria.hotel.core.dto.guest.GuestRequest;
import com.company.hotelaria.hotel.core.dto.guest.GuestResponse;
import com.company.hotelaria.hotel.core.entities.Address;
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
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
@Validated
@Slf4j
public class GuestService {

    private GuestRepository guestRepository;

    private GuestMapper guestMapper;

    private AddressRepository addressRepository;

    private AddressMapper addressMapper;

    private EmployeeRepository employeeRepository;


    public List<GuestResponse> findAll(){
        log.info("findAll");
        return this.guestMapper.listEntityToListResponse(this.guestRepository.findAll());
    }


    public GuestResponse findBySocialSecurityNumber(String socialSecurityNumber) {
        log.info("findBySocialSecurityNumber", socialSecurityNumber);
        Guest guest = this.guestRepository.findBySocialSecurityNumber(socialSecurityNumber)
                .orElseThrow(Message.ID_DO_NOT_EXIST::asBusinessException);
        GuestResponse guestResponse = this.guestMapper.entityToResponse(guest);
        List<Address> guestAddressEntities = this.addressRepository.findAllByGuestId(guest.getId());
        guestResponse.setGuestAddressEntities(guestAddressEntities);
        return guestResponse;
    }

    @Transactional
    public GuestResponse save(@Valid GuestRequest request){

        log.info("save request = {}", request);
        Guest guest = this.guestMapper.requestToEntity(request);
        this.guestRepository.findBySocialSecurityNumber(request.getSocialSecurityNumber()).ifPresent(p -> {
            throw Message.SECURITY_NUMBER_IS_PRESENT.asBusinessException();
        });
        this.employeeRepository.findBySocialSecurityNumber(request.getSocialSecurityNumber()).ifPresent(p -> {
            throw Message.SECURITY_NUMBER_IS_PRESENT.asBusinessException();
        });
        Guest guestResult = this.guestRepository.save(guest);
        GuestResponse guestResponse = this.guestMapper.entityToResponse(guestResult);
        List<Address> addressList = new ArrayList<>();
        for(int i = 0; i < request.getGuestAddress().size(); i++) {
            Address address = this.addressMapper.requestToEntity(request.getGuestAddress().get(i));
            Address addressResult = this.addressRepository.save(address);
            addressResult.updateGuest(guestResult);
           addressList.add(addressResult);
        }
        guestResponse.setGuestAddressEntities(addressList);
        return guestResponse;
    }

    @Transactional
    public GuestResponse update(@Valid GuestRequest request, Long id){
        log.info(" update request = {}", request);
        Guest guest = this.guestRepository.findById(id).orElseThrow(Message.ID_DO_NOT_EXIST::asBusinessException);
        List<Address> guestAddressEntities = this.addressRepository.findAllByGuestId(guest.getId());
        if(!request.getSocialSecurityNumber().equalsIgnoreCase(guest.getSocialSecurityNumber())) {
            this.guestRepository.findBySocialSecurityNumber(request.getSocialSecurityNumber()).ifPresent(p -> {
                throw Message.SECURITY_NUMBER_IS_PRESENT.asBusinessException();
            });
        }
            guest.updateGuest(
                    request.getName(),
                    request.getEmail(),
                    request.getDateOfBirth(),
                    request.getSocialSecurityNumber(),
                    request.getPhone());
        for(int i = 0; i < request.getGuestAddress().size(); i++) {
            if(i < guestAddressEntities.size()) {
            guestAddressEntities.get(i).updateAddress(
                    request.getGuestAddress().get(i).getStreetName(),
                    request.getGuestAddress().get(i).getNumber(),
                    request.getGuestAddress().get(i).getComplement(),
                    request.getGuestAddress().get(i).getCity(),
                    request.getGuestAddress().get(i).getState(),
                    request.getGuestAddress().get(i).getZipcode(),
                    request.getGuestAddress().get(i).getCountry());
        } else {
                Address address = this.addressMapper.requestToEntity(request.getGuestAddress().get(i));
                Address addressResult = this.addressRepository.save(address);
                addressResult.updateGuest(guest);
                guestAddressEntities.add(addressResult);
            }
        }
        GuestResponse guestResponse = this.guestMapper.entityToResponse(guest);
        guestResponse.setGuestAddressEntities(guestAddressEntities);
        return guestResponse;
    }

    public void delete(Long id) {
        Guest guest = this.guestRepository.findById(id).orElseThrow(Message.ID_DO_NOT_EXIST::asBusinessException);
        this.guestRepository.deleteById(guest.getId());
        log.info("method = delete number = {}",id);
    }
}
