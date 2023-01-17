package com.company.hotelaria.hotel.service;

import com.company.hotelaria.hotel.core.dto.address.AddressResponse;
import com.company.hotelaria.hotel.core.dto.guest.GuestFullResponse;
import com.company.hotelaria.hotel.core.dto.guest.GuestRequest;
import com.company.hotelaria.hotel.core.dto.guest.GuestResponse;
import com.company.hotelaria.hotel.core.dto.payment.PaymentResponse;
import com.company.hotelaria.hotel.core.entities.Address;
import com.company.hotelaria.hotel.core.entities.Guest;
import com.company.hotelaria.hotel.core.mapper.AddressMapper;
import com.company.hotelaria.hotel.core.mapper.GuestMapper;
import com.company.hotelaria.hotel.enums.Message;
import com.company.hotelaria.hotel.repository.AddressRepository;
import com.company.hotelaria.hotel.repository.EmployeeRepository;
import com.company.hotelaria.hotel.repository.GuestRepository;
import com.company.hotelaria.hotel.repository.PaymentsRepository;
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

    private EmployeeRepository employeeRepository;

    private PaymentsRepository paymentsRepository;


    public List<GuestResponse> findAll(){
        log.info("findAll");
        return this.guestMapper.listEntityToListResponse(this.guestRepository.findAll());
    }


    public GuestFullResponse findBySocialSecurityNumber(String socialSecurityNumber) {
        log.info("findBySocialSecurityNumber", socialSecurityNumber);
        Guest guest = this.guestRepository.findBySocialSecurityNumber(socialSecurityNumber)
                .orElseThrow(Message.ID_DO_NOT_EXIST::asBusinessException);
        GuestFullResponse guestResponse = this.guestMapper.entityToResponseFull(guest);
        List<AddressResponse> guestAddressResponse = this.addressRepository.findAllByGuestId(guest.getId());
        guestResponse.setGuestAddress(guestAddressResponse);
        List<PaymentResponse> guestPaymentResponse = this.paymentsRepository.findAllByGuestId(guest.getId());
        guestResponse.setGuestPayment(guestPaymentResponse);
        return guestResponse;
    }

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
        return guestResponse;
    }

    @Transactional
    public GuestResponse update(@Valid GuestRequest request, Long id){
        log.info(" update request = {}", request);
        Guest guest = this.guestRepository.findById(id).orElseThrow(Message.ID_DO_NOT_EXIST::asBusinessException);
        if(!request.getSocialSecurityNumber().equalsIgnoreCase(guest.getSocialSecurityNumber())) {
            this.guestRepository.findBySocialSecurityNumber(request.getSocialSecurityNumber()).ifPresent(p -> {
                throw Message.SECURITY_NUMBER_IS_PRESENT.asBusinessException();
            });
            this.employeeRepository.findBySocialSecurityNumber(request.getSocialSecurityNumber()).ifPresent(p -> {
                throw Message.SECURITY_NUMBER_IS_PRESENT.asBusinessException();
            });
        }
            guest.updateGuest(
                    request.getName(),
                    request.getEmail(),
                    request.getDateOfBirth(),
                    request.getSocialSecurityNumber(),
                    request.getPhone());
        GuestResponse guestResponse = this.guestMapper.entityToResponse(guest);
        return guestResponse;
    }

    public void delete(Long id) {
        Guest guest = this.guestRepository.findById(id).orElseThrow(Message.ID_DO_NOT_EXIST::asBusinessException);
        this.guestRepository.deleteById(guest.getId());
        log.info("method = delete number = {}",id);
    }
}
