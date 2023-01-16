package com.company.hotelaria.hotel.core.dto.rent;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class RentRequest {

    @Schema(description = "Dia do checkin", example = "2023-10-11")
    @NotNull(message = "O checkin não pode ser nulo")
    @FutureOrPresent
    private LocalDate checkIn;

    @Schema(description = "Dia do checkout", example = "2023-10-14")
    @NotNull(message = "O checkout não pode ser nulo")
    @FutureOrPresent
    private LocalDate checkOut;

}
