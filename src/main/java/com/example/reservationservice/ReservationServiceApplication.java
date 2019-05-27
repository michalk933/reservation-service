package com.example.reservationservice;

import com.example.reservationservice.binder.ReservationBinding;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.annotation.IntegrationComponentScan;


@EnableDiscoveryClient
@SpringBootApplication
@EnableBinding(ReservationBinding.class)
@IntegrationComponentScan
public class ReservationServiceApplication {


    public static void main(String[] args) {
        SpringApplication.run(ReservationServiceApplication.class, args);
    }

}