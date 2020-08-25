package com.orderservice.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "Order_Service")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue
    private Integer orderId;
    @NotNull
    private Integer customerId;
    @NotBlank(message = "Customer Name should not be blank")
    private String customerName;
    @CreationTimestamp
    private LocalDateTime orderDate;
    @NotBlank
    private String shippingAddress;
    private Double totalAmount;
    @NotNull
    private Integer productCode;

    @Min(value = 1, message = "Quantity should be greater than 1")
    private int quantity;

}
