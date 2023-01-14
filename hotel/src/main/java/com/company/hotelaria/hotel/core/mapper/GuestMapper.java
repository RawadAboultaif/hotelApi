package com.company.hotelaria.hotel.core.mapper;

import com.company.hotelaria.hotel.core.dto.guest.GuestRequest;
import com.company.hotelaria.hotel.core.dto.guest.GuestResponse;
import com.company.hotelaria.hotel.core.entities.Guest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GuestMapper {

    List<GuestResponse> listEntityToListResponse(List<Guest> guest);

    Guest requestToEntity(GuestRequest request);

    GuestResponse entityToResponse(Guest session);
}
