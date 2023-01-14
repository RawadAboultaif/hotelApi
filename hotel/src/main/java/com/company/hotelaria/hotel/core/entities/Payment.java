//package com.company.hotelaria.hotel.core.entities;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import lombok.*;
//
//import javax.persistence.*;
//
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Entity
//@Builder
//@Setter
//@Table(name = "payment")
//public class Payment {
//
//    @Column(name = "method")
//    private String method;
//
//    @Column(name = "card")
//    private String card;
//
//    @JsonIgnore
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_guest", referencedColumnName = "id")
//    private Guest guest;
//}
