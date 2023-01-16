package com.company.hotelaria.hotel.core.dto.employee;

import com.company.hotelaria.hotel.core.entities.Address;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class EmployeeResponse {

    private Long id;
    private String name;
    private String role;
    private Double remuneration;
    private String workschedule;
    private String email;
    private String phone;
    private String socialSecurityNumber;
}
