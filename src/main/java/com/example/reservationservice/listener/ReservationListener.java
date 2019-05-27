package com.example.reservationservice.listener;

import com.example.reservationservice.binder.ReservationBinding;
import com.example.reservationservice.response.Reservation;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
public class ReservationListener {

    @StreamListener(ReservationBinding.RESERVATION_IN)
    public void processReservationListener(Reservation rn) {
        System.out.println("CONSUMER EVENT: " + rn.getReservationName());
    }

}
