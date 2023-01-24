package com.company.hotelaria.hotel.controller;

import com.company.hotelaria.hotel.annotation.rent.FindRentStandard;
import com.company.hotelaria.hotel.annotation.rent.RentDeleteStandard;
import com.company.hotelaria.hotel.annotation.unit.UnitSaveStandard;
import com.company.hotelaria.hotel.core.model.rent.RentRequest;
import com.company.hotelaria.hotel.core.model.rent.RentResponse;
import com.company.hotelaria.hotel.service.RentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/rent")
@Tag(name = "Rent")
public class RentController {

    private RentService rentService;

    @GetMapping("/{id}")
    @FindRentStandard
    public ResponseEntity<RentResponse> find(@PathVariable("id")Long id)
    { return ResponseEntity.ok(this.rentService.findById(id)); }

    @PostMapping("/{idGuest}/{unitName}")
    @UnitSaveStandard
    public ResponseEntity<RentResponse> save(@RequestBody RentRequest request, @PathVariable("idGuest")Long id, @PathVariable("unitName") String name) {
        return ResponseEntity.status(HttpStatus.CREATED).body(rentService.save(request, id , name));
    }

    @DeleteMapping("/{id}")
    @RentDeleteStandard
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        this.rentService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
