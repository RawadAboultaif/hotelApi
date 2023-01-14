package com.company.hotelaria.hotel.controller;

import com.company.hotelaria.hotel.annotation.address.AddressDeleteStandard;
import com.company.hotelaria.hotel.annotation.address.AddressSaveStandard;
import com.company.hotelaria.hotel.annotation.address.ListAllAddressStandard;
import com.company.hotelaria.hotel.annotation.guest.GuestDeleteStandard;
import com.company.hotelaria.hotel.annotation.guest.GuestSaveStandard;
import com.company.hotelaria.hotel.annotation.guest.GuestUpdadeStandard;
import com.company.hotelaria.hotel.annotation.guest.ListAllGuestStandard;
import com.company.hotelaria.hotel.core.dto.address.AddressRequest;
import com.company.hotelaria.hotel.core.dto.address.AddressResponse;
import com.company.hotelaria.hotel.core.dto.guest.GuestRequest;
import com.company.hotelaria.hotel.core.dto.guest.GuestResponse;
import com.company.hotelaria.hotel.service.AddressService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/address")
@Tag(name = "Address")
public class AddressController {

    private AddressService addressService;


    @GetMapping
    @ListAllAddressStandard
    public ResponseEntity<List<AddressResponse>> listAllGuests() { return ResponseEntity.ok(this.addressService.findAll()); }

    @PostMapping("/{idGuest}")
    @AddressSaveStandard
    public ResponseEntity<AddressResponse> saveAdressGuest(@RequestBody AddressRequest request, @PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(addressService.saveAddressGuest(request, id));
    }

    @PostMapping("/{idEmployee}")
    @AddressSaveStandard
    public ResponseEntity<AddressResponse> saveAdressEmployee(@RequestBody AddressRequest request, @PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(addressService.saveAddressEmployee(request, id));
    }

    @DeleteMapping("/{id}")
    @AddressDeleteStandard
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        this.addressService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    @AddressDeleteStandard
    public ResponseEntity<AddressResponse> update(@RequestBody AddressRequest request, @PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(addressService.update(request, id));
    }
}
