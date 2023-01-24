package com.company.hotelaria.hotel.core.mapper;

import com.company.hotelaria.hotel.core.model.unit.UnitRequest;
import com.company.hotelaria.hotel.core.model.unit.UnitResponse;
import com.company.hotelaria.hotel.core.entities.Unit;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UnitMapper {

    Unit requestToEntity(UnitRequest request);

    UnitResponse entityToResponse(Unit request);
}
