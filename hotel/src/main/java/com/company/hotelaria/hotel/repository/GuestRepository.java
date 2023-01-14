package com.company.hotelaria.hotel.repository;

import com.company.hotelaria.hotel.core.entities.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {

    Optional<Guest> findBySocialSecurityNumber(String socialSecurityNumber);


}
