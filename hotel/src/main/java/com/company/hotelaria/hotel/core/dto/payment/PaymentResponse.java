package com.company.hotelaria.hotel.core.dto.payment;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class PaymentResponse {

    private Long id;
    private String method;
    private String card;
}
