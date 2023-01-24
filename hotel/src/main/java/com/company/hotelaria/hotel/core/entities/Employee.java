package com.company.hotelaria.hotel.core.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Builder
@Setter
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_id_seq")
    @SequenceGenerator(name = "employee_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "role")
    private String role;

    @Column(name = "remuneration")
    private Double remuneration;

    @Column(name = "workschedule")
    private String workschedule;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "social_security_number")
    private String socialSecurityNumber;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", cascade = CascadeType.ALL)
    private Set<Address> address;

    public void updateEmployee(String name, String role, Double remuneration, String workschedule, String email,String phone, String socialSecurityNumber) {
        this.name = name;
        this.role = role;
        this.remuneration = remuneration;
        this.workschedule = workschedule;
        this.email = email;
        this.phone = phone;
        this.socialSecurityNumber = socialSecurityNumber;
    }

}
