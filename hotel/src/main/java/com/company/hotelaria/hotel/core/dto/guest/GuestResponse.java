package com.company.hotelaria.hotel.core.dto.guest;


import com.company.hotelaria.hotel.core.entities.Address;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class GuestResponse {

    private Long id;
    private String name;
    private String socialSecurityNumber;
    private LocalDate dateOfBirth;
    private String email;
    private String phone;
}
