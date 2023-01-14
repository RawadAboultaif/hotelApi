package com.company.hotelaria.hotel.core.dto.unit;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class UnitRequest {

    @Schema(description = "Número do quarto", example = "205")
    @NotNull(message = "O número não pode ser nulo")
    @NotBlank(message = "O número nao pode ser vazio")
    private String name;

    @Schema(description = "Valor da diária", example = "800")
    @NotNull(message = "O valor não pode ser nulo")
    private Double price;

    @Schema(description = "Limete de clientes no quarto", example = "4")
    @NotNull(message = "O limite de pessoas nao pode vazio")
    private Integer limitGuest;
}
