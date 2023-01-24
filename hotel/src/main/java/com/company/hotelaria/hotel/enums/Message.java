package com.company.hotelaria.hotel.enums;


import com.company.hotelaria.hotel.exception.BusinessException;
import org.springframework.http.HttpStatus;

public enum Message {

    SECURITY_NUMBER_IS_PRESENT("Cpf already registered", HttpStatus.BAD_REQUEST),
    ID_DO_NOT_EXIST("Id not found", HttpStatus.NOT_FOUND),

    AGE_UNDER_EIGHTEEN("Age under eighteen", HttpStatus.BAD_REQUEST),

    RENT_CHECKIN_GREATER_THAN_CHECKOUT("Checkin não pode ser maior que o Checkout", HttpStatus.BAD_REQUEST),

    UNIT_ALREADY_OCCUPIED("Room already occupied", HttpStatus.BAD_REQUEST),

    SECURITY_NUMBER_IS_NOT_PRESENT("Cpf not registered", HttpStatus.NOT_FOUND),

    UNIT_NAME_DO_NOT_EXIST("Room number does not exist", HttpStatus.NOT_FOUND),

    UNIT_NAME_EXIST("Room number already exist", HttpStatus.BAD_REQUEST),

    UNIT_PRICE_OR_LIMITGUEST_UNDER_ZERO("Room price or limit guest cannot be under zero", HttpStatus.BAD_REQUEST)
    ;

    private String value;
    private String description;
    private HttpStatus statusCode;

    private Message(String value, HttpStatus statusCode) {
        this.value = value;
        this.statusCode = statusCode;
    }

    private Message(String value, String description, HttpStatus statusCode) {
        this.value = value;
        this.description = description;
        this.statusCode = statusCode;
    }

    private Message(String value) {
        this.value = value;
    }

    public String getMessage() {
        return this.value;
    }

    public HttpStatus getStatus() {
        return this.statusCode;
    }

    public String getDescription() {
        return description;
    }

    public BusinessException asBusinessException() {
        return BusinessException.builder().httpStatusCode(this.getStatus())
                .status(String.valueOf(this.getStatus().value())).message(this.getMessage())
                .description(this.getDescription()).build();
    }
}
