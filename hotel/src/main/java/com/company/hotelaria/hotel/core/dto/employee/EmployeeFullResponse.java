package com.company.hotelaria.hotel.core.dto.employee;

import com.company.hotelaria.hotel.core.dto.address.AddressResponse;
import com.company.hotelaria.hotel.core.entities.Address;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EmployeeFullResponse {

    private Long id;
    private String name;
    private String role;
    private Double remuneration;
    private String workschedule;
    private String email;
    private String phone;
    private String socialSecurityNumber;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<AddressResponse> employeeAddress;
}
