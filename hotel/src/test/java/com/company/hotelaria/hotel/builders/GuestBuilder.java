package com.company.hotelaria.hotel.builders;

import com.company.hotelaria.hotel.core.dto.address.AddressRequest;
import com.company.hotelaria.hotel.core.dto.address.AddressResponse;
import com.company.hotelaria.hotel.core.dto.guest.GuestFullResponse;
import com.company.hotelaria.hotel.core.dto.guest.GuestRequest;
import com.company.hotelaria.hotel.core.dto.guest.GuestResponse;
import com.company.hotelaria.hotel.core.entities.Address;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GuestBuilder {

    public GuestRequest novoGuestRequest() {
        return criarGuestRequest();
    }

    public GuestResponse novoGuestResponse() {
        return criarGuestResponse();
    }

    public GuestFullResponse novoGuestFullResponse() {
        return criarGuestFullResponse();
    }

    private GuestResponse criarGuestResponse() {

        return GuestResponse.builder()
                .name("teste")
                .socialSecurityNumber("12345678912")
                .dateOfBirth(LocalDate.of(1999, 10,19))
                .email("teste@teste.com")
                .phone("(31)99999-9999")
                .build();
    }

    private GuestRequest criarGuestRequest() {


        return GuestRequest.builder()

                .name("teste")
                .socialSecurityNumber("12345678912")
                .dateOfBirth(LocalDate.of(1999, 10,19))
                .email("teste@teste.com")
                .phone("(31)99999-9999")
                .build();
    }

    private GuestFullResponse criarGuestFullResponse() {

        List<AddressResponse> guestAddressResponseEntity = new ArrayList<>();
        guestAddressResponseEntity.add(AddressResponse.builder()
                .id(1L)
                .streetName("Rua olinda")
                .number("204")
                .complement("ap105")
                .city("Belo horizonte")
                .state("Minas Gerais")
                .zipcode("32838-129")
                .country("brazil")
                .build());

        return GuestFullResponse.builder()
                .name("teste")
                .socialSecurityNumber("12345678912")
                .dateOfBirth(LocalDate.of(1999, 10,19))
                .email("teste@teste.com")
                .phone("(31)99999-9999")
                .guestAddress(guestAddressResponseEntity)
                .build();
    }
}
