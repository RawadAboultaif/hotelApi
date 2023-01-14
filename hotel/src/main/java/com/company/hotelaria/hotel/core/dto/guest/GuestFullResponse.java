package com.company.hotelaria.hotel.core.dto.guest;

import com.company.hotelaria.hotel.core.dto.address.AddressResponse;
import com.company.hotelaria.hotel.core.entities.Address;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GuestFullResponse {

    private Long id;
    private String name;
    private String socialSecurityNumber;
    private LocalDate dateOfBirth;
    private String email;
    private String phone;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<AddressResponse> guestAddress;
}
