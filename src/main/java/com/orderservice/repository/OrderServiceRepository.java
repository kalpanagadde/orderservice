package com.orderservice.repository;

import com.orderservice.entity.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderServiceRepository extends CrudRepository<Order, Integer> {}
