package com.splendore.domain.restaurant;

import com.splendore.domain.restaurant.enums.ReservationStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Tables
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClientRestaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private String lastName;

    private Integer tableNumber;
    private Float price;
    private Integer quantity;

    private String email;
    private String phone;
    private LocalDateTime reservationDateTime;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;
    private String specialRequest;
    private Float totalOrderPrice;
}
