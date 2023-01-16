package com.company.hotelaria.hotel.annotation.rent;

import com.company.hotelaria.hotel.core.dto.rent.RentResponse;
import com.company.hotelaria.hotel.core.dto.unit.UnitResponse;
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
        @ApiResponse(responseCode = "200", description = "Quarto atualizado",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = RentResponse.class))),
        @ApiResponse(responseCode = "404", description = "Id não encontrado",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = BusinessException.BusinessExceptionBody.class))),
        @ApiResponse(responseCode = "500", description = "Sistema indisponivel",content=@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)) })
@Operation(summary = ConstantsUnit.RENT_UPDATE_SUMARY, description = ConstantsUnit.RENT_UPDATE_DESCRIPTION)
public @interface RentUpdadeStandard {
}
