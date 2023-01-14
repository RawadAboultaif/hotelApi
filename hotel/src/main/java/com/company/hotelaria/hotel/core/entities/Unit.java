package com.company.hotelaria.hotel.core.entities;

import com.company.hotelaria.hotel.enums.UnitEnum;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Builder
@Setter
@Table(name = "unit")
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "unit_id_seq")
    @SequenceGenerator(name = "unit_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "limit_guest")
    private Integer limitGuest;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private UnitEnum status;

    public void updateStatus(UnitEnum status) {
        this.status = status;
    }

    public void updateUnit(String name, Double price, Integer limitGuest) {
        this.name = name;
        this.price = price;
        this.limitGuest = limitGuest;
    }
}
