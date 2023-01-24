package com.company.hotelaria.hotel.core.model.payment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class PaymentRequest {

    @Schema(description = "CardFlag", example = "Visa")
    @NotNull(message = "Card flag cannot be null")
    @NotBlank(message = "Card flag cannot be empty")
    private String method;

    @Schema(description = "CardNumber", example = "4915533592253476")
    @CreditCardNumber(message = "Invalid card number")
    private String card;
}
