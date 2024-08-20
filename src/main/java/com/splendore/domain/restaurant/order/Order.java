package com.splendore.domain.restaurant.order;

import com.splendore.domain.restaurant.order.enums.OrderStatus;
import com.splendore.domain.restaurant.tables.Tables;
import com.splendore.domain.restaurant.clientRestaurant.ClientRestaurant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientRestaurant client;

    @ManyToOne
    @JoinColumn(name = "tables_id")
    private Tables table;

    private LocalDateTime orderDateTime;
    private Float totalPrice;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<OrderItem> orderItems;
}
