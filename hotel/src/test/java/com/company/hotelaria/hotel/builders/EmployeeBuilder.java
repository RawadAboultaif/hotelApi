package com.company.hotelaria.hotel.builders;

import com.company.hotelaria.hotel.core.dto.employee.EmployeeFullResponse;
import com.company.hotelaria.hotel.core.dto.employee.EmployeeRequest;
import com.company.hotelaria.hotel.core.dto.employee.EmployeeResponse;
import com.company.hotelaria.hotel.core.entities.Employee;
import com.company.hotelaria.hotel.core.entities.Guest;

import java.util.ArrayList;
import java.util.List;

public class EmployeeBuilder {

    public static EmployeeRequest novoEmployeeRequest() {
        return criarEmployeeRequest();
    }

    public static EmployeeResponse novoEmployeeResponse() {
        return criarEmployeeResponse(1L);
    }

    public static EmployeeFullResponse novoEmployeeFullResponse() {
        return criarEmployeeFullResponse(1L);
    }

    public static Employee novoEmployee() {
        return criarEmployee(1L);
    }

    public static Employee novoEmployeeComSocialSecurityNumberDiferente() {
        Employee employee = criarEmployee(1L);
        employee.setSocialSecurityNumber("97321231245");

        return employee;
    }

    public static List<Employee> novaListaEmployee() {
        Employee employee1 = criarEmployee(1L);
        Employee employee2 = criarEmployee(2L);
        List<Employee> listEmployee = new ArrayList<>();
        listEmployee.add(employee1);
        listEmployee.add(employee2);
        return listEmployee;
    }

    public static List<EmployeeResponse> novaListaEmployeeResponse() {
        EmployeeResponse employee1 = criarEmployeeResponse(1L);
        EmployeeResponse employee2 = criarEmployeeResponse(2L);
        List<EmployeeResponse> listEmployee = new ArrayList<>();
        listEmployee.add(employee1);
        listEmployee.add(employee2);
        return listEmployee;
    }

    private static Employee criarEmployee(Long id) {
        return Employee.builder()
                .id(id)
                .name("Rawad Aboultaif")
                .role("Gerente")
                .remuneration(1000.00)
                .workschedule("08:00 16:00")
                .email("teste@gmail.com")
                .phone("9999-8888")
                .socialSecurityNumber("13813281238")
                .build();
    }

    private static EmployeeFullResponse criarEmployeeFullResponse(Long id) {
        return EmployeeFullResponse.builder()
                .id(id)
                .name("Rawad Aboultaif")
                .role("Gerente")
                .remuneration(1000.00)
                .workschedule("08:00 16:00")
                .email("teste@gmail.com")
                .phone("9999-8888")
                .socialSecurityNumber("13813281238")
                .employeeAddress(AddressBuilder.novaListaAddressResponse())
                .build();
    }

    private static EmployeeResponse criarEmployeeResponse(Long id) {
        return EmployeeResponse.builder()
                .id(id)
                .name("Rawad Aboultaif")
                .role("Gerente")
                .remuneration(1000.00)
                .workschedule("08:00 16:00")
                .email("teste@gmail.com")
                .phone("9999-8888")
                .socialSecurityNumber("13813281238")
                .build();
    }

    private static EmployeeRequest criarEmployeeRequest() {
        return EmployeeRequest.builder()
                .name("Rawad Aboultaif")
                .role("Gerente")
                .remuneration(1000.00)
                .workschedule("08:00 16:00")
                .email("teste@gmail.com")
                .phone("9999-8888")
                .socialSecurityNumber("13813281238")
                .build();
    }
}
