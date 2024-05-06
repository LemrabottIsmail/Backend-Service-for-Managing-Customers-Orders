package org.example.customerstores.model.dto;

import lombok.*;

@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderStatusDto {
    private Long orderStatusId;
    private String name;
}
