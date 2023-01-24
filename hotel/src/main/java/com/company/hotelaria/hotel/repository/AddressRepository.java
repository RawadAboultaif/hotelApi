package com.company.hotelaria.hotel.repository;

import com.company.hotelaria.hotel.core.model.address.AddressResponse;
import com.company.hotelaria.hotel.core.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

   List<AddressResponse> findAllByGuestId (Long id_guest);

   List<AddressResponse> findAllByEmployeeId (Long id_employee);
}
