package org.example.customerstores.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "unfinished_orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UnfinishedOrder {
    @Id
    Long orderId;
    Long customerId;
    long pastDays;
    LocalDate orderDate;
}
