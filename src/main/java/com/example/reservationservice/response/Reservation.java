package com.example.reservationservice.response;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Reservation {

    @Id
    @GeneratedValue
    private Long id;

    private String reservationName;

}
