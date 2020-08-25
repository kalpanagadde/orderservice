package com.orderservice.service;

import com.orderservice.common.OrderItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "order", url = "http://localhost:8080")
public interface OrderServiceFeignClient {
    @RequestMapping(method = RequestMethod.GET, value = "/orderitem/{id}")
    OrderItem getOrderItems(@PathVariable("id") int id);

    @RequestMapping(method = RequestMethod.POST, value = "/orderitem")
    OrderItem updateOrderItems(@RequestBody OrderItem orderItems);
}
