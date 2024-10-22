package com.splendore.services.restaurant;

import com.splendore.domain.restaurant.order.Order;
import com.splendore.domain.restaurant.order.OrderRequestDTO;
import com.splendore.exceptions.NotFoundException;
import com.splendore.repositories.restaurant.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(UUID id) {
        return orderRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Not found order with id: " + id)
        );
    }

    public Order saveOrder(OrderRequestDTO orderRequestDTO) {
        Order order = new Order();
        order.setClient(orderRequestDTO.client());
        order.setTable(orderRequestDTO.table());
        order.setTotalPrice(orderRequestDTO.totalPrice());
        order.setStatus(orderRequestDTO.status());
        order.setOrderItems(orderRequestDTO.orderItems());
        return orderRepository.save(order);
    }

    public Order updateOrder(UUID id, OrderRequestDTO orderRequestDTO) {
        Order order = getOrderById(id);
        order.setClient(orderRequestDTO.client());
        order.setTable(orderRequestDTO.table());
        order.setTotalPrice(orderRequestDTO.totalPrice());
        order.setStatus(orderRequestDTO.status());
        order.setOrderItems(orderRequestDTO.orderItems());
        return orderRepository.save(order);
    }

    public void deleteOrder(UUID id) {
        orderRepository.deleteById(id);
    }
}
