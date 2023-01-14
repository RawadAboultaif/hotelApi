package com.company.hotelaria.hotel.controller;

import com.company.hotelaria.hotel.annotation.address.AddressDeleteStandard;
import com.company.hotelaria.hotel.annotation.address.AddressSaveStandard;
import com.company.hotelaria.hotel.annotation.address.FindAddressStandard;
import com.company.hotelaria.hotel.core.dto.address.AddressRequest;
import com.company.hotelaria.hotel.core.dto.address.AddressResponse;
import com.company.hotelaria.hotel.service.AddressService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/address")
@Tag(name = "Address")
public class AddressController {

    private AddressService addressService;


    @GetMapping("/{id}")
    @FindAddressStandard
    public ResponseEntity<AddressResponse> find(@PathVariable("id")Long id)
    { return ResponseEntity.ok(this.addressService.findById(id)); }

    @PostMapping("/idGuest/{id}")
    @AddressSaveStandard
    public ResponseEntity<AddressResponse> saveAdressGuest(@RequestBody AddressRequest request, @PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(addressService.saveAddressGuest(request, id));
    }

    @PostMapping("/idEmployee/{id}")
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
    @AddressSaveStandard
    public ResponseEntity<AddressResponse> update(@RequestBody AddressRequest request, @PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(addressService.update(request, id));
    }
}
