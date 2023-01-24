package com.company.hotelaria.hotel.core.model.rent;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class RentRequest {

    @Schema(description = "CheckIn", example = "2023-10-11")
    @NotNull(message = "CheckIn cannot be null")
    @FutureOrPresent
    private LocalDate checkIn;

    @Schema(description = "Checkout", example = "2023-10-14")
    @NotNull(message = "Checkout cannot be null")
    @FutureOrPresent
    private LocalDate checkOut;

}
