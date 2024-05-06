package org.example.customerstores.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "shippers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Shipper {

    @Id
    private Long shipperId;
    private String name;
}
