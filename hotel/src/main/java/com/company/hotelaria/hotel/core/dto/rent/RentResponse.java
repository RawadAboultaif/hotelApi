package com.company.hotelaria.hotel.core.dto.rent;

import com.company.hotelaria.hotel.core.dto.guest.GuestResponse;
import com.company.hotelaria.hotel.core.dto.unit.UnitResponse;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

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
