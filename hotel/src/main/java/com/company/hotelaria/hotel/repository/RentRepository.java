package com.company.hotelaria.hotel.repository;

import com.company.hotelaria.hotel.core.entities.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentRepository extends JpaRepository<Rent, Long> {
}
