package com.company.hotelaria.hotel.enums;

public enum UnitEnum {

    EMPTY("E"),
    FULL("F"),
    E("EMPTY"),
    F("FULL");

    private final String status;

    UnitEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }
}
