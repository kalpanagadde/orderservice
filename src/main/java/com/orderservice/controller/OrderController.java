package com.orderservice.controller;

import com.orderservice.entity.Order;
import com.orderservice.exception.InsufficientQuantityException;
import com.orderservice.service.OrderService;
import com.orderservice.service.OrderServiceFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    OrderService orderService;

    @Autowired
    OrderServiceFeignClient feignClient;

    @PostMapping
    public ResponseEntity<Object> place(@Valid @RequestBody Order order) throws InsufficientQuantityException {
        logger.info("Placing order");
        orderService.place(order);
        logger.info("Order placed successfully");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Order placed successfully");
    }

    @GetMapping("/{id}")
    public Order get(@PathVariable int id) {
        logger.info("Fetching order with id {} ",id);
        return orderService.get(id);
    }
}
