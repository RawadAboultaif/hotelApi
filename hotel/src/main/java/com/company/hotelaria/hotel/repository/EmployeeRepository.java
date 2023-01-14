package com.company.hotelaria.hotel.repository;

import com.company.hotelaria.hotel.core.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository  extends JpaRepository<Employee, Long> {

    Optional<Employee> findBySocialSecurityNumber(String socialSecurityNumber);
}
