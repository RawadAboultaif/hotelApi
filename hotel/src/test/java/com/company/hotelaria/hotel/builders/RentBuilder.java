package com.company.hotelaria.hotel.builders;

import com.company.hotelaria.hotel.core.entities.Rent;
import com.company.hotelaria.hotel.core.model.rent.RentRequest;
import com.company.hotelaria.hotel.core.model.rent.RentResponse;

import java.time.LocalDate;

public class RentBuilder {

    public static Rent novoRent() {
        return criarNovoRent(1L);
    }

    public static RentRequest novoRentRequest() {
        return criarNovoRentRequest(1L);
    }


    public static RentResponse novoRentResponse() {
        return criarNovoRentResponse(1L);
    }

    private static RentResponse criarNovoRentResponse(Long id) {
        return RentResponse.builder()
                .id(id)
                .checkIn(LocalDate.of(2023, 11, 20))
                .checkOut(LocalDate.of(2023, 12, 12))
                .guest(GuestBuilder.novoGuestResponse())
                .unit(UnitBuilder.novoUnitResponse())
                .build();
    }

    private static RentRequest criarNovoRentRequest(Long id) {
        return RentRequest.builder()
                .checkIn(LocalDate.of(2023, 11, 20))
                .checkOut(LocalDate.of(2023, 12, 12))
                .build();
    }

    private static Rent criarNovoRent(Long id) {
        return Rent.builder()
                .id(id)
                .checkIn(LocalDate.of(2023, 11, 20))
                .checkOut(LocalDate.of(2023, 12, 12))
                .guest(GuestBuilder.novoGuest())
                .unit(UnitBuilder.novoUnit())
                .build();
    }
}
