package org.example.customerstores.mapper.Impl;

import org.example.customerstores.mapper.Mapper;
import org.example.customerstores.model.Order;
import org.example.customerstores.model.dto.OrderDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper implements Mapper<Order, OrderDto> {

    private final ModelMapper modelMapper;

    public OrderMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public OrderDto toDto(Order order) {
        return modelMapper.map(order, OrderDto.class);
    }

    @Override
    public Order toModel(OrderDto orderDto) {
        return modelMapper.map(orderDto, Order.class);
    }
}
