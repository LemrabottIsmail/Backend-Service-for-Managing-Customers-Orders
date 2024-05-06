package org.example.customerstores.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "orders")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;
    private LocalDate orderDate;
    @OneToOne
    @JoinColumn(name = "status", referencedColumnName = "orderStatusId")
    private OrderStatus status;
    private String comments;
    private LocalDate shippedDate;
    @OneToOne
    @JoinColumn(name = "shipper_id")
    private Shipper shipper;

}
