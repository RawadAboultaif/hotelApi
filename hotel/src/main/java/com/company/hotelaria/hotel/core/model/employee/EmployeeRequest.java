package com.company.hotelaria.hotel.core.model.employee;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class EmployeeRequest {


    @Schema(description = "Name", example = "Rawad Aboultaif")
    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be empty")
    private String name;

    @Schema(description = "Role", example = "Gerente")
    @NotNull(message = "Role cannot be null")
    @NotBlank(message = "Role cannot be empty")
    private String role;

    @Schema(description = "Remuneration", example = "800")
    @NotNull(message = "Remuneration cannot be null")
    @Positive(message = "Remuneration must be above zero")
    private Double remuneration;

    @Schema(description = "WorkSchedule", example = "08:00 - 18:00")
    @NotNull(message = "Work schedule cannot be null")
    @NotBlank(message = "Work schedule cannot be empty")
    private String workschedule;

    @Schema(description = "Email", example = "teste@emailteste.com")
    @NotNull(message = "Email cannot be null")
    @NotBlank(message = "Email cannot be empty")
    private String email;

    @Schema(description = "Phone", example = "99876-5432")
    @NotNull(message = "Phone cannot be null")
    @NotBlank(message = "Phone cannot be empty")
    private String phone;

    @Schema(description = "Cpf", example = "123456789123")
    @CPF(message = "Cpf is invalid")
    private String socialSecurityNumber;

}
