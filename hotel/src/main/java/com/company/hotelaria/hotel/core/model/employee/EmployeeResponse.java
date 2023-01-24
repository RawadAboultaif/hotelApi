package com.company.hotelaria.hotel.core.model.employee;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
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
