package com.company.hotelaria.hotel.core.model.guest;


import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class GuestResponse {

    private Long id;
    private String name;
    private String socialSecurityNumber;
    private LocalDate dateOfBirth;
    private String email;
    private String phone;
}
