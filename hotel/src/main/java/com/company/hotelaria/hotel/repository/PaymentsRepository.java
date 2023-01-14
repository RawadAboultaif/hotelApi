package com.company.hotelaria.hotel.repository;

import com.company.hotelaria.hotel.core.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentsRepository extends JpaRepository<Payment, Long> {
}
