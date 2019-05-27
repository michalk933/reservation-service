package com.example.reservationservice.binder;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface ReservationBinding {

    String RESERVATION_IN = "reservationsin";

    @Input(RESERVATION_IN)
    SubscribableChannel reservationIn();

}
