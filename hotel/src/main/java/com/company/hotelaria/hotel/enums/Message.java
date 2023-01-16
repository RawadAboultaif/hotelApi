package com.company.hotelaria.hotel.enums;


import com.company.hotelaria.hotel.exception.BusinessException;
import org.springframework.http.HttpStatus;

public enum Message {

    SECURITY_NUMBER_IS_PRESENT("O Cpf ja esta cadastrado", HttpStatus.BAD_REQUEST),
    ID_DO_NOT_EXIST("O id nao existe", HttpStatus.NOT_FOUND),

    UNIT_ALREADY_OCCUPIED("O quarto não esta vazio", HttpStatus.BAD_REQUEST),

    SECURITY_NUMBER_IS_NOT_PRESENT("O Cpf não esta cadastrado"),

    UNIT_NAME_DO_NOT_EXIST("O número do quarto não existe", HttpStatus.NOT_FOUND),

    UNIT_NAME_EXIST("O número do quarto ja esta cadastrado", HttpStatus.BAD_REQUEST)
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
