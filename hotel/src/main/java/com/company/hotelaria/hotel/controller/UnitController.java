package com.company.hotelaria.hotel.controller;

import com.company.hotelaria.hotel.annotation.guest.GuestDeleteStandard;
import com.company.hotelaria.hotel.annotation.guest.GuestSaveStandard;
import com.company.hotelaria.hotel.annotation.guest.GuestUpdadeStandard;
import com.company.hotelaria.hotel.annotation.payment.FindPaymentStandard;
import com.company.hotelaria.hotel.annotation.unit.*;
import com.company.hotelaria.hotel.core.dto.guest.GuestRequest;
import com.company.hotelaria.hotel.core.dto.guest.GuestResponse;
import com.company.hotelaria.hotel.core.dto.payment.PaymentResponse;
import com.company.hotelaria.hotel.core.dto.unit.UnitRequest;
import com.company.hotelaria.hotel.core.dto.unit.UnitResponse;
import com.company.hotelaria.hotel.service.UnitService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/unit")
@Tag(name = "Unit")
public class UnitController {

    private UnitService unitService;

    @GetMapping("/{unitNumber}")
    @FindUnitStandard
    public ResponseEntity<UnitResponse> find(@PathVariable("unitNumber")String name)
    { return ResponseEntity.ok(this.unitService.findByName(name)); }

    @PostMapping
    @UnitSaveStandard
    public ResponseEntity<UnitResponse> save(@RequestBody UnitRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(unitService.save(request));
    }

    @DeleteMapping("/{id}")
    @UnitDeleteStandard
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        this.unitService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    @UnitUpdadeStandard
    public ResponseEntity<UnitResponse> update(@RequestBody UnitRequest request, @PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(unitService.update(request, id));
    }

    @PatchMapping("/checkout/{unitName}")
    @UnitUpdateStatusStandard
    public ResponseEntity<UnitResponse> updateCheckOut(@PathVariable("unitName") String name){
        return ResponseEntity.status(HttpStatus.OK).body(unitService.updateCheckOut(name));
    }

    @PatchMapping("/checkin/{unitName}")
    @UnitUpdateStatusStandard
    public ResponseEntity<UnitResponse> updateCheckIn(@PathVariable("unitName") String name){
        return ResponseEntity.status(HttpStatus.OK).body(unitService.updateCheckIn(name));
    }
}
