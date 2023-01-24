package com.company.hotelaria.hotel.core.model.unit;

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

    @Schema(description = "RoomNumber", example = "205")
    @NotNull(message = "Room number cannot be null")
    @NotBlank(message = "Room number cannot be empty")
    private String name;

    @Schema(description = "Price", example = "800")
    @NotNull(message = "Price cannot be null")
    private Double price;

    @Schema(description = "RoomGuestLimit", example = "4")
    @NotNull(message = "Room guest limit cannot be null")
    private Integer limitGuest;
}
