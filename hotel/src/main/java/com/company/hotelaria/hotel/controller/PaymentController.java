package com.company.hotelaria.hotel.controller;

import com.company.hotelaria.hotel.annotation.address.AddressDeleteStandard;
import com.company.hotelaria.hotel.annotation.address.AddressSaveStandard;
import com.company.hotelaria.hotel.annotation.address.FindAddressStandard;
import com.company.hotelaria.hotel.annotation.payment.FindPaymentStandard;
import com.company.hotelaria.hotel.annotation.payment.PaymentSaveStandard;
import com.company.hotelaria.hotel.core.dto.address.AddressRequest;
import com.company.hotelaria.hotel.core.dto.address.AddressResponse;
import com.company.hotelaria.hotel.core.dto.payment.PaymentRequest;
import com.company.hotelaria.hotel.core.dto.payment.PaymentResponse;
import com.company.hotelaria.hotel.service.PaymentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/payment")
@Tag(name = "Payment ")
public class PaymentController {

    private PaymentService paymentService;

    @GetMapping("/{id}")
    @FindPaymentStandard
    public ResponseEntity<PaymentResponse> find(@PathVariable("id")Long id)
    { return ResponseEntity.ok(this.paymentService.findById(id)); }

    @PostMapping("/idGuest/{id}")
    @PaymentSaveStandard
    public ResponseEntity<PaymentResponse> saveAdressGuest(@RequestBody PaymentRequest request, @PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.save(request, id));
    }

    @PutMapping("/{id}")
    @PaymentSaveStandard
    public ResponseEntity<PaymentResponse> update(@RequestBody PaymentRequest request, @PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(paymentService.update(request, id));
    }


    @DeleteMapping("/{id}")
    @PaymentSaveStandard
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        this.paymentService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
