package com.company.hotelaria.hotel.builders;

import com.company.hotelaria.hotel.core.dto.payment.PaymentResponse;

import java.util.ArrayList;
import java.util.List;

public class PaymentBuilder {

    public static List<PaymentResponse> novaListaPaymentResponse() {
        PaymentResponse payment1 = criarPaymentResponse(1L);
        PaymentResponse payment2 = criarPaymentResponse(2L);
        List<PaymentResponse> listPayment = new ArrayList<>();
        listPayment.add(payment1);
        listPayment.add(payment2);
        return listPayment;
    }

    private static PaymentResponse criarPaymentResponse(Long id) {
        return PaymentResponse.builder()
                .id(id)
                .method("visa")
                .card("123456789")
                .build();
    }
}
