package com.company.hotelaria.hotel.core.mapper;

import com.company.hotelaria.hotel.core.dto.rent.RentRequest;
import com.company.hotelaria.hotel.core.dto.rent.RentResponse;
import com.company.hotelaria.hotel.core.entities.Rent;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RentMapper {

    Rent requestToEntity(RentRequest request);

    RentResponse entityToResponse(Rent request);
}
