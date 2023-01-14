package com.company.hotelaria.hotel.core.dto.address;

import com.company.hotelaria.hotel.core.dto.employee.EmployeeRequest;
import com.company.hotelaria.hotel.core.dto.guest.GuestRequest;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class AddressRequest {


    @Schema(description = "Logradouro", example = "Rua são paulo")
    @NotNull(message = "O nome da rua não pode ser nulo")
    @NotBlank(message = "O nome da rua nao pode estar vazia")
    private String streetName;

    @Schema(description = "número", example = "123")
    @NotNull(message = "O número do local não pode ser nulo")
    @NotBlank(message = "O número do local não pode estar vazio")
    private String number;

    @Schema(description = "Complemento", example = "Ap101")
    private String complement;

    @Schema(description = "Cidade", example = "Belo Horizonte")
    @NotNull(message = "O nome da cidade não pode ser nulo")
    @NotBlank(message = "O nome da cidade não pode estar vazia")
    private String city;

    @Schema(description = "Estado", example = "Minas Gerais")
    @NotNull(message = "O nome do estado não pode ser nulo")
    @NotBlank(message = "O número do estado não pode estar vazio")
    private String state;

    @Schema(description = "Codigo Postal", example = "76907-187")
    @NotNull(message = "O código postal não pode ser nulo")
    @NotBlank(message = "O código postal não pode estar vazio")
    private String zipcode;

    @Schema(description = "País", example = "Brasil")
    @NotNull(message = "O nome do país não pode ser nulo")
    @NotBlank(message = "O nome do país não pode estar vazio")
    private String country;

}
