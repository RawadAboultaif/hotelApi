package com.company.hotelaria.hotel.builders;

import com.company.hotelaria.hotel.core.model.unit.UnitRequest;
import com.company.hotelaria.hotel.core.model.unit.UnitResponse;
import com.company.hotelaria.hotel.core.entities.Unit;
import com.company.hotelaria.hotel.enums.UnitEnum;

public class UnitBuilder {

    public static Unit novoUnit(){
        return criarNovoUnit(1L);
    }

    public static UnitResponse novoUnitResponse() {
        return criarUnitResponse(1L);
    }

    public static UnitResponse novoUnitFullResponse() {
        UnitResponse unitResponse = criarUnitResponse(1L);
        unitResponse.setStatus(UnitEnum.FULL);
        return unitResponse;
    }

    public static Unit novoUnitStatusFull() {
        Unit unit = criarNovoUnit(1L);
        unit.setStatus(UnitEnum.F);
        return unit;
    }

    public static UnitRequest novoUnitRequest() {
        return criarUnitRequest();
    }

    public static Unit novoUnitComUnitNameDiferente() {
        Unit unit = criarNovoUnit(1L);
        unit.setName("805");
        return unit;
    }

    private static UnitRequest criarUnitRequest() {
        return UnitRequest.builder()
                .name("104")
                .price(1200.00)
                .limitGuest(3)
                .build();
    }

    private static UnitResponse criarUnitResponse(Long id) {
        return UnitResponse.builder()
                .id(id)
                .name("104")
                .price(1200.00)
                .limitGuest(3)
                .status(UnitEnum.EMPTY)
                .build();
    }

    private static Unit criarNovoUnit(Long id) {
        return Unit.builder()
                .id(id)
                .name("104")
                .price(1200.00)
                .limitGuest(3)
                .status(UnitEnum.EMPTY)
                .build();
    }
}
