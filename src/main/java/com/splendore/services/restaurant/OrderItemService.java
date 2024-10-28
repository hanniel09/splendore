package com.splendore.services.restaurant;

import com.splendore.domain.restaurant.order.OrderItem;
import com.splendore.domain.restaurant.order.OrderItemRequestDTO;
import com.splendore.exceptions.NotFoundException;
import com.splendore.repositories.restaurant.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    public List<OrderItem> findAllOrderItems() {
        return orderItemRepository.findAll();
    }

    public OrderItem findOrderItemById(Long id) {
        return orderItemRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found order item with: " + id));
    }

    public OrderItem saveOrderItem(OrderItemRequestDTO orderItemRequestDTO) {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(orderItemRequestDTO.order());
        orderItem.setItemName(orderItemRequestDTO.itemName());
        orderItem.setQuantity(orderItemRequestDTO.quantity());
        orderItem.setPrice(orderItemRequestDTO.price());
        orderItem.setTableNumber(orderItemRequestDTO.tableNumber());
        return orderItemRepository.save(orderItem);
    }

    public OrderItem updateOrderItem(Long id, OrderItemRequestDTO orderItemRequestDTO) {
        OrderItem orderItem = findOrderItemById(id);
        orderItem.setOrder(orderItemRequestDTO.order());
        orderItem.setItemName(orderItemRequestDTO.itemName());
        orderItem.setQuantity(orderItemRequestDTO.quantity());
        orderItem.setPrice(orderItemRequestDTO.price());
        orderItem.setTableNumber(orderItemRequestDTO.tableNumber());
        return orderItemRepository.save(orderItem);
    }

    public void deleteOrderItem(Long id) {
        orderItemRepository.deleteById(id);
    }
}
