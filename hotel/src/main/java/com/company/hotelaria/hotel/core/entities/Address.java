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
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_id_seq")
    @SequenceGenerator(name = "address_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "street_name")
    private String streetName;

    @Column(name = "number")
    private String number;

    @Column(name = "complement")
    private String complement;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zipcode")
    private String zipcode;

    @Column(name = "country")
    private String country;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_employee", referencedColumnName = "id")
    private Employee employee;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_guest", referencedColumnName = "id")
    private Guest guest;

    public void updateGuest(Guest guest) {
        this.guest = guest;
    }

    public void updateEmplyee(Employee employee) {
        this.employee = employee;
    }

    public void updateAddress(String streetName, String number, String complement, String city, String state, String zipcode, String country) {
        this.streetName = streetName;
        this.number = number;
        this.complement = complement;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.country = country;
    }
}
