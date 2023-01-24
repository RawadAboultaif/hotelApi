package com.company.hotelaria.hotel.core.mapper;

import com.company.hotelaria.hotel.core.model.employee.EmployeeFullResponse;
import com.company.hotelaria.hotel.core.model.employee.EmployeeRequest;
import com.company.hotelaria.hotel.core.model.employee.EmployeeResponse;
import com.company.hotelaria.hotel.core.entities.Employee;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    List<EmployeeResponse> listEntityToListResponse(List<Employee> guest);

    Employee requestToEntity(EmployeeRequest request);

    EmployeeFullResponse entityToResponseFull(Employee request);
    EmployeeResponse entityToResponse(Employee request);
}
