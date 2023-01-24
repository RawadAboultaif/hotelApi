package com.company.hotelaria.hotel.core.model.guest;

import com.company.hotelaria.hotel.core.model.address.AddressResponse;
import com.company.hotelaria.hotel.core.model.payment.PaymentResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class GuestFullResponse {

    private Long id;
    private String name;
    private String socialSecurityNumber;
    private LocalDate dateOfBirth;
    private String email;
    private String phone;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<AddressResponse> guestAddress;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<PaymentResponse> guestPayment;
}
