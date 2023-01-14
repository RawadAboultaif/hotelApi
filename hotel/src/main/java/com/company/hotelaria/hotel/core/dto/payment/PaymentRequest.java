package com.company.hotelaria.hotel.core.dto.payment;

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
public class PaymentRequest {

    @Schema(description = "Bandeira", example = "Visa")
    @NotNull(message = "A Bandeira do cartão não pode ser nulo")
    @NotBlank(message = "A Bandeira do cartão nao pode ser vazia")
    private String method;

    @Schema(description = "Número do cartao", example = "123456789123")
    @NotNull(message = "O número não pode ser nulo")
    @NotBlank(message = "O número nao pode ser vazio")
    private String card;
}
