package com.company.hotelaria.hotel.core.model.address;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
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
