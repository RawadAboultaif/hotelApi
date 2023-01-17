package com.company.hotelaria.hotel.core.dto.employee;


import com.company.hotelaria.hotel.core.dto.address.AddressRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class EmployeeRequest {


    @Schema(description = "Nome", example = "Rawad Aboultaif")
    @NotNull(message = "O nome não pode ser nulo")
    @NotBlank(message = "O nome não pode estar vazio")
    private String name;

    @Schema(description = "Cargo", example = "Gerente")
    @NotNull(message = "O cargo não pode ser nulo")
    @NotBlank(message = "O cargo não pode estar vazio")
    private String role;

    @Schema(description = "Salário", example = "800")
    @NotNull(message = "O salário não pode ser nulo")
    private Double remuneration;

    @Schema(description = "Horário de trabalho", example = "08:00 - 18:00")
    @NotNull(message = "O horário de trabalho não pode ser nulo")
    @NotBlank(message = "O horário de trabalho não pode estar vazio")
    private String workschedule;

    @Schema(description = "Email", example = "teste@emailteste.com")
    @NotNull(message = "O email não pode ser nulo")
    @NotBlank(message = "O email não pode estar vazio")
    private String email;

    @Schema(description = "Phone", example = "99876-5432")
    @NotNull(message = "O telefone não pode ser nulo")
    @NotBlank(message = "O telefone não pode estar vazio")
    private String phone;

    @Schema(description = "Cpf", example = "123456789123")
    @NotNull(message = "O cpf não pode ser nulo")
    @NotBlank(message = "O telefone não pode estar vazio")
    private String socialSecurityNumber;

}
