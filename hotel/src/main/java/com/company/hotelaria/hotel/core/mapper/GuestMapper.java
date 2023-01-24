package com.company.hotelaria.hotel.core.mapper;

import com.company.hotelaria.hotel.core.entities.Guest;
import com.company.hotelaria.hotel.core.model.guest.GuestFullResponse;
import com.company.hotelaria.hotel.core.model.guest.GuestRequest;
import com.company.hotelaria.hotel.core.model.guest.GuestResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GuestMapper {

    List<GuestResponse> listEntityToListResponse(List<Guest> guest);

    Guest requestToEntity(GuestRequest request);

    GuestFullResponse entityToResponseFull(Guest request);

    GuestResponse entityToResponse(Guest request);
}
