package com.company.hotelaria.hotel.core.mapper;

import com.company.hotelaria.hotel.core.entities.Address;
import com.company.hotelaria.hotel.core.model.address.AddressRequest;
import com.company.hotelaria.hotel.core.model.address.AddressResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    Address requestToEntity(AddressRequest request);

    AddressResponse entityToResponse(Address request);

}
