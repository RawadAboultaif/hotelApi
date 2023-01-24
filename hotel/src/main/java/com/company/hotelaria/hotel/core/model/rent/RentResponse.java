package com.company.hotelaria.hotel.core.model.rent;

import com.company.hotelaria.hotel.core.model.guest.GuestResponse;
import com.company.hotelaria.hotel.core.model.unit.UnitResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class RentResponse {

    private Long id;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private Double totalPrice;
    private GuestResponse guest;
    private UnitResponse unit;
}
