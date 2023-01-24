package com.company.hotelaria.hotel.core.model.guest;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class GuestRequest {


    @Schema(description = "Name", example = "Pietro Bianchi")
    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be empty")
    private String name;

    @Schema(description = "Cpf", example = "85768465006")
    @CPF(message = "Cpf is invalid")
    private String socialSecurityNumber;

    @Schema(description = "Birthday", example = "1991-19-1991")
    @NotNull(message = "Birthday cannot be null")
    @Past(message = "Birthday must be in the past")
    private LocalDate dateOfBirth;

    @Schema(description = "Email", example = "email@emailtest.com")
    @NotNull(message = "Email cannot be null")
    @NotBlank(message = "Email cannot be empty")
    private String email;

    @Schema(description = "Phone", example = "9999-9999")
    @NotNull(message = "Phone cannot be null")
    @NotBlank(message = "Phone cannot be empty")
    private String phone;
}
