package org.example.customerstores.model.dto;

import lombok.*;
import org.example.customerstores.model.OrderStatus;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderDto {
    private Long orderId;
    private CustomerDto customer;
    private LocalDate orderDate;
    private OrderStatusDto status;
    private String comments;
    private LocalDate shippedDate;
    private ShipperDto shipper;
}
