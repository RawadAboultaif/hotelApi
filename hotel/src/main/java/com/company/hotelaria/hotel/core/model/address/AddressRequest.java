package com.company.hotelaria.hotel.core.model.address;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class AddressRequest {


    @Schema(description = "StreetName", example = "Rua são paulo")
    @NotNull(message = "Street name cannot be null")
    @NotBlank(message = "Street name cannot be empty")
    private String streetName;

    @Schema(description = "Number", example = "123")
    @NotNull(message = "Number cannot be null")
    @NotBlank(message = "Number cannot be empty")
    private String number;

    @Schema(description = "Complement", example = "Ap101")
    private String complement;

    @Schema(description = "City", example = "Belo Horizonte")
    @NotNull(message = "City cannot be null")
    @NotBlank(message = "City cannot be empty")
    private String city;

    @Schema(description = "State", example = "Minas Gerais")
    @NotNull(message = "State cannot be null")
    @NotBlank(message = "State cannot be empty")
    private String state;

    @Schema(description = "Zipcode", example = "76907-187")
    @NotNull(message = "Zipcode cannot be null")
    @NotBlank(message = "Zipcode cannot be empty")
    private String zipcode;

    @Schema(description = "Country", example = "Brasil")
    @NotNull(message = "Country cannot be null")
    @NotBlank(message = "Country cannot be empty")
    private String country;

}
