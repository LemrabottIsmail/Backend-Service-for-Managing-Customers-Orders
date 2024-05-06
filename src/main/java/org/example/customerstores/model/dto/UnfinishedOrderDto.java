package org.example.customerstores.model.dto;

import lombok.*;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UnfinishedOrderDto {
    Long orderId;
    Long customerId;
    long pastDays;
    LocalDate orderDate;
}
