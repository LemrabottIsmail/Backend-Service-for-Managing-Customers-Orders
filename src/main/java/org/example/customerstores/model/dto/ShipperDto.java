package org.example.customerstores.model.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class ShipperDto {
    private Long shipperId;
    private String name;
}
