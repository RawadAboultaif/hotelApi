package com.company.hotelaria.hotel.controller;


import com.company.hotelaria.hotel.annotation.guest.*;
import com.company.hotelaria.hotel.core.model.guest.GuestFullResponse;
import com.company.hotelaria.hotel.core.model.guest.GuestRequest;
import com.company.hotelaria.hotel.core.model.guest.GuestResponse;
import com.company.hotelaria.hotel.service.GuestService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/guest")
@Tag(name = "Guest")
public class GuestController {

    private GuestService guestService;

    @GetMapping("/list-all-guest")
    @ListAllGuestStandard
    public ResponseEntity<List<GuestResponse>> listAllGuests() { return ResponseEntity.ok(this.guestService.findAll()); }

    @GetMapping("/cpf/{cpf}")
    @FindGuestbySocialSecurityNumberStandard
    public ResponseEntity<GuestFullResponse> findBySocialSecurityNumber(@PathVariable("cpf") String socialSecurityNumber) {
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

    @PutMapping("/update/{id}")
    @GuestUpdadeStandard
    public ResponseEntity<GuestResponse> update(@RequestBody GuestRequest request, @PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(guestService.update(request, id));
    }
}
