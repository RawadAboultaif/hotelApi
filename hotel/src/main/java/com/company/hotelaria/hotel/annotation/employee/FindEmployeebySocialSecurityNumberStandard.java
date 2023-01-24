package com.company.hotelaria.hotel.annotation.employee;

import com.company.hotelaria.hotel.core.model.employee.EmployeeFullResponse;
import com.company.hotelaria.hotel.exception.BusinessException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Retorna o funcionário buscado pelo cpf",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = EmployeeFullResponse.class))),
        @ApiResponse(responseCode = "404", description = "CPF não cadastrado",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = BusinessException.BusinessExceptionBody.class))),
        @ApiResponse(responseCode = "500", description = "Sistema indisponivel",content=@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)) })
@Operation(summary = ConstantsEmployee.EMPLOYEE_FIND_BY_SOCIALSECURITYNUMBER_SUMMARY, description = ConstantsEmployee.EMPLOYEE_FIND_BY_SOCIALSECURITYNUMBER_DESCRIPTION)
public @interface FindEmployeebySocialSecurityNumberStandard {
}
