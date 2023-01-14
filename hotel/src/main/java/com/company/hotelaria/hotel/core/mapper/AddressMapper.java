package com.company.hotelaria.hotel.core.mapper;

import com.company.hotelaria.hotel.core.dto.address.AddressRequest;
import com.company.hotelaria.hotel.core.dto.address.AddressResponse;
import com.company.hotelaria.hotel.core.dto.guest.GuestResponse;
import com.company.hotelaria.hotel.core.entities.Address;
import com.company.hotelaria.hotel.core.entities.Guest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    Address requestToEntity(AddressRequest request);

    AddressResponse entityToResponse(Address request);

    List<AddressResponse> listEntityToListResponse(List<Address> request);


}
