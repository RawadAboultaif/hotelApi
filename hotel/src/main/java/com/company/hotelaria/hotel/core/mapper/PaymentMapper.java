package com.company.hotelaria.hotel.core.mapper;

import com.company.hotelaria.hotel.core.entities.Payment;
import com.company.hotelaria.hotel.core.model.payment.PaymentRequest;
import com.company.hotelaria.hotel.core.model.payment.PaymentResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    Payment requestToEntity(PaymentRequest request);

    PaymentResponse entityToResponse(Payment request);
}
