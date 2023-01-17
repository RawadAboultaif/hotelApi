package com.company.hotelaria.hotel.builders;

import com.company.hotelaria.hotel.core.dto.address.AddressRequest;
import com.company.hotelaria.hotel.core.dto.address.AddressResponse;
import com.company.hotelaria.hotel.core.dto.guest.GuestFullResponse;
import com.company.hotelaria.hotel.core.dto.guest.GuestRequest;
import com.company.hotelaria.hotel.core.dto.guest.GuestResponse;
import com.company.hotelaria.hotel.core.dto.payment.PaymentResponse;
import com.company.hotelaria.hotel.core.entities.Address;
import com.company.hotelaria.hotel.core.entities.Guest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GuestBuilder {

    public static GuestRequest novoGuestRequest() {
        return criarGuestRequest();
    }

    public static Guest novoGuest() {
        return criarGuest(1L);
    }

    public static Guest novoGuestComSocialSecurityNumberDiferente() {
        Guest guest = criarGuest(1L);
        guest.setSocialSecurityNumber("3458394859");

        return guest;
    }

    public static GuestResponse novoGuestResponse() {
        return criarGuestResponse(1L);
    }

    public static GuestFullResponse novoGuestFullResponse() {
        return criarGuestFullResponse(1L);
    }

    public static List<GuestResponse> novaListaDeGuestResponse() {
        GuestResponse guest1 = criarGuestResponse(1L);
        GuestResponse guest2 = criarGuestResponse(2L);
        List<GuestResponse> listGuests = new ArrayList<>();
        listGuests.add(guest1);
        listGuests.add(guest2);

        return listGuests;
    }

    public static List<Guest> novaListaGuest() {
        Guest guest1 = criarGuest(1L);
        Guest guest2 = criarGuest(2L);
        List<Guest> listGuest = new ArrayList<>();
        listGuest.add(guest1);
        listGuest.add(guest2);
        return listGuest;
    }

    private static GuestResponse criarGuestResponse(Long id) {

        return GuestResponse.builder()
                .id(id)
                .name("teste")
                .socialSecurityNumber("12345678912")
                .dateOfBirth(LocalDate.of(1999, 10,19))
                .email("teste@teste.com")
                .phone("(31)99999-9999")
                .build();
    }

    private static GuestRequest criarGuestRequest() {


        return GuestRequest.builder()

                .name("teste")
                .socialSecurityNumber("12345678912")
                .dateOfBirth(LocalDate.of(1999, 10,19))
                .email("teste@teste.com")
                .phone("(31)99999-9999")
                .build();
    }

    private static GuestFullResponse criarGuestFullResponse(Long id) {

        return GuestFullResponse.builder()
                .id(id)
                .name("teste")
                .socialSecurityNumber("12345678912")
                .dateOfBirth(LocalDate.of(1999, 10,19))
                .email("teste@teste.com")
                .phone("(31)99999-9999")
                .guestAddress(AddressBuilder.novaListaAddressResponse())
                .guestPayment(PaymentBuilder.novaListaPaymentResponse())
                .build();
    }

    private static Guest criarGuest(Long id) {
        return Guest.builder()
                .id(id)
                .name("teste")
                .socialSecurityNumber("12345678912")
                .dateOfBirth(LocalDate.of(1999, 10,19))
                .email("teste@teste.com")
                .phone("(31)99999-9999")
                .build();
    }
}
