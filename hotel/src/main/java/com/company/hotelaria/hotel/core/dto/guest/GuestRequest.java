package com.company.hotelaria.hotel.core.dto.guest;


import com.company.hotelaria.hotel.core.dto.address.AddressRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class GuestRequest {


    @Schema(description = "Nome", example = "Pietro Bianchi")
    @NotNull(message = "O nome não pode ser nulo")
    @NotBlank(message = "O nome não pode ser estar vazio")
    private String name;

    @Schema(description = "Cpf", example = "123456789123")
    @NotNull(message = "O cpf não pode ser nulo")
    @NotBlank(message = "O cpf não pode ser estar vazio")
    private String socialSecurityNumber;

    @Schema(description = "Data de nascimento", example = "1991-19-1991")
    @NotNull(message = "A data de nascimento não pode ser nulo")
    private LocalDate dateOfBirth;

    @Schema(description = "Email", example = "email@emailtest.com")
    @NotNull(message = "O email não pode ser nulo")
    @NotBlank(message = "O email não pode estar vazio")
    private String email;

    @Schema(description = "Telefone", example = "9999-9999")
    @NotNull(message = "O telefone não pode ser nulo")
    @NotBlank(message = "O telefone não pode estar vazio")
    private String phone;

    @Schema(description = "Endereço")
    @NotNull(message = "O endereço não pode ser nulo")
    private List<AddressRequest> guestAddress;

}
