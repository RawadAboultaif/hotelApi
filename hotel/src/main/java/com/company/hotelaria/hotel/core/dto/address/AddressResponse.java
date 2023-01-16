package com.company.hotelaria.hotel.core.dto.address;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class AddressResponse {

    private Long id;
    private String streetName;
    private String number;
    private String complement;
    private String city;
    private String state;
    private String zipcode;
    private String country;
}
