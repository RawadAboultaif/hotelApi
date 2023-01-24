package com.company.hotelaria.hotel.builders;

import com.company.hotelaria.hotel.core.entities.Payment;
import com.company.hotelaria.hotel.core.model.payment.PaymentRequest;
import com.company.hotelaria.hotel.core.model.payment.PaymentResponse;

import java.util.ArrayList;
import java.util.List;

public class PaymentBuilder {

    public static PaymentResponse novoPaymentResponse() {
        return criarPaymentResponse(1L);
    }

    public static Payment novoPayment() {
        return criarPayment(1L);
    }

    public static PaymentRequest novoPaymentRequest() {
        return criarPaymentRequest();
    }

    public static List<PaymentResponse> novaListaPaymentResponse() {
        PaymentResponse payment1 = criarPaymentResponse(1L);
        PaymentResponse payment2 = criarPaymentResponse(2L);
        List<PaymentResponse> listPayment = new ArrayList<>();
        listPayment.add(payment1);
        listPayment.add(payment2);
        return listPayment;
    }

    private static Payment criarPayment(Long id) {
        return Payment.builder()
                .id(id)
                .method("visa")
                .card("123456789")
                .build();

    }

    private static PaymentRequest criarPaymentRequest() {
        return PaymentRequest.builder()
                .method("visa")
                .card("123456789")
                .build();
    }

    private static PaymentResponse criarPaymentResponse(Long id) {
        return PaymentResponse.builder()
                .id(id)
                .method("visa")
                .card("123456789")
                .build();
    }
}
