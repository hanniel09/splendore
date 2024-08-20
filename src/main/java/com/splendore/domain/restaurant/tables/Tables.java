package com.splendore.domain.restaurant.tables;

import com.splendore.domain.restaurant.order.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Tables {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer tableNumber;
    private Integer capacity;
    private Boolean isOccupied = false;

    @OneToMany(mappedBy = "tables")
    private List<Order> orders;
}
