package com.company.hotelaria.hotel.core.model.unit;

import com.company.hotelaria.hotel.enums.UnitEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class UnitResponse {

    private Long id;
    private String name;
    private Double price;
    private Integer limitGuest;
    private UnitEnum status;
}
