package com.company.hotelaria.hotel.core.mapper;

import com.company.hotelaria.hotel.core.dto.address.AddressRequest;
import com.company.hotelaria.hotel.core.entities.Address;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    Address requestToEntity(AddressRequest request);

    List<Address> listEntityToListResponse(List<Address> guest);


}
