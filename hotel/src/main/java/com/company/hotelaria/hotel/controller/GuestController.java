package com.company.hotelaria.hotel.controller;


import com.company.hotelaria.hotel.annotation.guest.*;
import com.company.hotelaria.hotel.core.dto.guest.GuestFullResponse;
import com.company.hotelaria.hotel.core.dto.guest.GuestRequest;
import com.company.hotelaria.hotel.core.dto.guest.GuestResponse;
import com.company.hotelaria.hotel.service.GuestService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PreRemove;
import java.lang.annotation.Target;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/guest")
@Tag(name = "Guest")
public class GuestController {

    private GuestService guestService;

    @GetMapping
    @ListAllGuestStandard
    public ResponseEntity<List<GuestResponse>> listAllGuests() { return ResponseEntity.ok(this.guestService.findAll()); }

    @GetMapping("/{socialSecurityNumber}")
    @FindGuestbySocialSecurityNumberStandard
    public ResponseEntity<GuestFullResponse> findBySocialSecurityNumber(@PathVariable("socialSecurityNumber") String socialSecurityNumber) {
        return ResponseEntity.ok(this.guestService.findBySocialSecurityNumber(socialSecurityNumber));
    }

    @PostMapping
    @GuestSaveStandard
    public ResponseEntity<GuestResponse> save(@RequestBody GuestRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(guestService.save(request));
    }

    @DeleteMapping("/{id}")
    @GuestDeleteStandard
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        this.guestService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    @GuestUpdadeStandard
    public ResponseEntity<GuestResponse> update(@RequestBody GuestRequest request, @PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(guestService.update(request, id));
    }
}
