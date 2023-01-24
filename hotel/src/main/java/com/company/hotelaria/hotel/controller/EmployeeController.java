package com.company.hotelaria.hotel.controller;

import com.company.hotelaria.hotel.annotation.employee.*;
import com.company.hotelaria.hotel.core.model.employee.EmployeeFullResponse;
import com.company.hotelaria.hotel.core.model.employee.EmployeeRequest;
import com.company.hotelaria.hotel.core.model.employee.EmployeeResponse;
import com.company.hotelaria.hotel.service.EmployeeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/employee")
@Tag(name = "Employee ")
public class EmployeeController {

    EmployeeService employeeService;

    @GetMapping("/list-all-guest")
    @ListAllEmployeeStandard
    public ResponseEntity<List<EmployeeResponse>> listAllEmployees() { return ResponseEntity.ok(this.employeeService.findAll()); }

    @GetMapping("/cpf/{cpf}")
    @FindEmployeebySocialSecurityNumberStandard
    public ResponseEntity<EmployeeFullResponse> findBySocialSecurityNumber(@PathVariable("cpf") String socialSecurityNumber) {
        return ResponseEntity.ok(this.employeeService.findBySocialSecurityNumber(socialSecurityNumber));
    }

    @PostMapping
    @EmployeeSaveStandard
    public ResponseEntity<EmployeeResponse> save(@RequestBody EmployeeRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.save(request));
    }

    @DeleteMapping("/{id}")
    @EmployeeDeleteStandard
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        this.employeeService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/update/{id}")
    @EmployeeUpdadeStandard
    public ResponseEntity<EmployeeResponse> update(@RequestBody EmployeeRequest request, @PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.update(request, id));
    }
}
