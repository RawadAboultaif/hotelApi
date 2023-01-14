package com.company.hotelaria.hotel.core.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Builder
@Setter
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_id_seq")
    @SequenceGenerator(name = "payment_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "method")
    private String method;

    @Column(name = "card")
    private String card;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_guest", referencedColumnName = "id")
    private Guest guest;

    public void updateGuest(Guest guest) {
        this.guest = guest;
    }

    public void updatePayment(String method, String card) {
        this.method = method;
        this.card = card;
    }
}
