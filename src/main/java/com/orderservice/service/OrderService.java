package com.orderservice.service;

import com.orderservice.common.OrderItem;
import com.orderservice.entity.Order;
import com.orderservice.exception.InsufficientQuantityException;
import com.orderservice.exception.OrderNotFoundException;
import com.orderservice.repository.OrderServiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class OrderService {

    private final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    OrderServiceRepository repository;

    @Autowired
    OrderServiceFeignClient feignClient;

    public Order saveOrder(Order order) {

        Order savedOrder = repository.save(order);
        return savedOrder;
    }

    public Order get(int id) {
        Optional<Order> order;
        order = repository.findById(id);
        if (order.isPresent())
            return order.get();
        else {
            logger.info("Requested order info is not available for the id {}", id);
            throw new OrderNotFoundException("Requested order info is not available.");
        }
    }


    public boolean place(Order order) throws InsufficientQuantityException {

        logger.info("Making an API call to order item service");
        OrderItem orderItems = feignClient.getOrderItems(order.getProductCode());
        int availableQuantity = orderItems.getQuantity();
        int requestedQuantity = order.getQuantity();
        if (availableQuantity >= requestedQuantity) {
            order.setTotalAmount(orderItems.getPrice() * requestedQuantity);
            int newQuantity = availableQuantity - requestedQuantity;
            orderItems.setQuantity(newQuantity);
            feignClient.updateOrderItems(orderItems);
            saveOrder(order);
            return true;
        } else {
            logger.info("Requested quantity is not available");
            throw new InsufficientQuantityException("Requested order items are not available. Available items = " + availableQuantity);
        }
    }
}
