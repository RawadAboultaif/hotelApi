package com.company.hotelaria.hotel.core.dto.unit;

import com.company.hotelaria.hotel.enums.UnitEnum;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UnitResponse {

    private String name;
    private Double price;
    private Integer limitGuest;
    private UnitEnum status;
}
