package com.company.hotelaria.hotel.core.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Builder
@Setter
@Table(name = "guest")
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "guest_id_seq")
    @SequenceGenerator(name = "guest_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "social_security_number")
    private String socialSecurityNumber;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "guest",  cascade = CascadeType.ALL)
    private Set<Address> address;

//    @JsonIgnore
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "guest",  cascade = CascadeType.ALL)
//    private Set<Payment> payments;


    public void updateGuest(String name, String socialSecurityNumber, LocalDate dateOfBirth,  String email, String phone) {
        this.name = name;
        this.socialSecurityNumber = socialSecurityNumber;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phone = phone;
    }
}

