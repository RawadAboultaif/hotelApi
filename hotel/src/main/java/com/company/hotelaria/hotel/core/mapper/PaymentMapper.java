package com.company.hotelaria.hotel.core.mapper;

import com.company.hotelaria.hotel.core.dto.guest.GuestFullResponse;
import com.company.hotelaria.hotel.core.dto.guest.GuestRequest;
import com.company.hotelaria.hotel.core.dto.guest.GuestResponse;
import com.company.hotelaria.hotel.core.dto.payment.PaymentRequest;
import com.company.hotelaria.hotel.core.dto.payment.PaymentResponse;
import com.company.hotelaria.hotel.core.entities.Guest;
import com.company.hotelaria.hotel.core.entities.Payment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    Payment requestToEntity(PaymentRequest request);

    PaymentResponse entityToResponse(Payment request);
}
