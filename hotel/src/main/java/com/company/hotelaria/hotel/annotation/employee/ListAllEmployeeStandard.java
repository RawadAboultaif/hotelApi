package com.company.hotelaria.hotel.annotation.employee;

import com.company.hotelaria.hotel.annotation.guest.ConstantsGuest;
import com.company.hotelaria.hotel.core.dto.employee.EmployeeResponse;
import com.company.hotelaria.hotel.core.dto.guest.GuestResponse;
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
        @ApiResponse(responseCode = "200", description = "Retorna uma lista de funcionários",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = EmployeeResponse.class))),
        @ApiResponse(responseCode = "500", description = "Sistema indisponivel",content=@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)) })
@Operation(summary = ConstantsEmployee.LIST_ALL_EMPLOYEE_SUMMARY, description = ConstantsEmployee.LIST_ALL_EMPLOYEE_DESCRIPTION)
public @interface ListAllEmployeeStandard {
}
