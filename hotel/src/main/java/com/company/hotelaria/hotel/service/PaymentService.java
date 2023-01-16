package com.company.hotelaria.hotel.service;

import com.company.hotelaria.hotel.core.dto.address.AddressRequest;
import com.company.hotelaria.hotel.core.dto.address.AddressResponse;
import com.company.hotelaria.hotel.core.dto.guest.GuestRequest;
import com.company.hotelaria.hotel.core.dto.guest.GuestResponse;
import com.company.hotelaria.hotel.core.dto.payment.PaymentRequest;
import com.company.hotelaria.hotel.core.dto.payment.PaymentResponse;
import com.company.hotelaria.hotel.core.entities.Address;
import com.company.hotelaria.hotel.core.entities.Guest;
import com.company.hotelaria.hotel.core.entities.Payment;
import com.company.hotelaria.hotel.core.mapper.GuestMapper;
import com.company.hotelaria.hotel.core.mapper.PaymentMapper;
import com.company.hotelaria.hotel.enums.Message;
import com.company.hotelaria.hotel.repository.GuestRepository;
import com.company.hotelaria.hotel.repository.PaymentsRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@AllArgsConstructor
@Service
@Validated
@Slf4j
public class PaymentService {

    private PaymentsRepository paymentsRepository;
    private PaymentMapper paymentMapper;
    private GuestRepository guestRepository;

    public PaymentResponse findById(Long id){
        log.info("findById");
        return this.paymentMapper.entityToResponse(this.paymentsRepository.findById(id)
                .orElseThrow(Message.ID_DO_NOT_EXIST::asBusinessException
                ));
    }

    @Transactional
    public PaymentResponse save(@Valid PaymentRequest request, Long id){

        log.info("save request = {}", request);
        Guest guestResult = this.guestRepository.findById(id)
                .orElseThrow(Message.ID_DO_NOT_EXIST::asBusinessException);
        Payment payment = this.paymentMapper.requestToEntity(request);
        payment.updateGuest(guestResult);
        Payment paymentResult = this.paymentsRepository.save(payment);
        PaymentResponse paymentResponse = this.paymentMapper.entityToResponse(paymentResult);

        return paymentResponse;
    }

    @Transactional
    public PaymentResponse update(@Valid PaymentRequest request, Long id){
        log.info(" update request = {}", request);
        Payment payment = this.paymentsRepository.findById(id).orElseThrow(Message.ID_DO_NOT_EXIST::asBusinessException);
        payment.updatePayment(
                request.getMethod(),
                request.getCard());
        PaymentResponse paymentResponse = this.paymentMapper.entityToResponse(payment);
        return paymentResponse;
    }

    public void delete(Long id) {
        Payment payment = this.paymentsRepository.findById(id).orElseThrow(Message.ID_DO_NOT_EXIST::asBusinessException);
        this.paymentsRepository.deleteById(payment.getId());
        log.info("method = delete number = {}",id);
    }
}
