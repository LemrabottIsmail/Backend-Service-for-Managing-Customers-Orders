package org.example.customerstores.service;

import org.example.customerstores.exception.NotFoundException;
import org.example.customerstores.mapper.Impl.UnfinishedOrderMapper;
import org.example.customerstores.mapper.Mapper;
import org.example.customerstores.model.Order;
import org.example.customerstores.model.UnfinishedOrder;
import org.example.customerstores.model.dto.OrderDto;
import org.example.customerstores.model.dto.UnfinishedOrderDto;
import org.example.customerstores.repository.OrderRepository;
import org.example.customerstores.repository.UnfinishedOrderRepository;
import org.example.customerstores.utils.utils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    private final Mapper<Order, OrderDto> orderMapper;

    private final UnfinishedOrderRepository unfinishedOrderRepository;

    private final UnfinishedOrderMapper unfinishedOrderMapper;

    private final CustomerService customerService;



    public OrderService(OrderRepository orderRepository, Mapper<Order, OrderDto> orderMapper, UnfinishedOrderRepository unfinishedOrderRepository, UnfinishedOrderMapper unfinishedOrderMapper, CustomerService customerService) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.unfinishedOrderRepository = unfinishedOrderRepository;
        this.unfinishedOrderMapper = unfinishedOrderMapper;
        this.customerService = customerService;
    }

    public OrderDto getOrderById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found Order with id " + id));
        return orderMapper.toDto(order);
    }

    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }

    public OrderDto saveOrder(OrderDto orderDto) {
        Order order = orderMapper.toModel(orderDto);
        return orderMapper.toDto(orderRepository.save(order));
    }

    public List<OrderDto> findOrdersByCustomerId(Long id){
        Iterator<Order> orders = orderRepository.getOrdersByCustomerId(id).iterator();
        List<Order> ordersList = StreamSupport.stream(Spliterators.spliteratorUnknownSize(orders, Spliterator.ORDERED), false).toList();
        return ordersList.stream().map(orderMapper::toDto).collect(Collectors.toList());
    }

    public List<UnfinishedOrderDto> findUnfinishedOrders(String firstName, String lastName) {
        Long customerId = customerService.findCustomerByFirstLastName(firstName, lastName).getCustomerId();
        Iterator<UnfinishedOrder> unfinishedOrders = unfinishedOrderRepository.getCustomerUnfinishedOrders(customerId).iterator();
        List<UnfinishedOrder> unfinishedOrdersList = StreamSupport.stream(Spliterators.spliteratorUnknownSize(unfinishedOrders, Spliterator.ORDERED), false).toList();
        return unfinishedOrdersList.stream().map(unfinishedOrderMapper::toDto).collect(Collectors.toList());
    }

    public void savedUnfinishedOrders(String firstName, String lastName) {
        Long customerId = customerService.findCustomerByFirstLastName(firstName, lastName).getCustomerId();
        // Getting all customer orders that are "Processed"
        Iterator<Order> orders = orderRepository.getOrdersByStatusAndCustomerId(customerId).iterator();
        List<Order> unfinishedOrdersList = StreamSupport.stream(Spliterators.spliteratorUnknownSize(orders, Spliterator.ORDERED), false).toList();
        // Save them into The unfinished_orders table
        Iterable<UnfinishedOrder> unfinishedOrders = unfinishedOrdersList
                .stream().map(
                        order -> UnfinishedOrder
                                .builder()
                                .orderId(order.getOrderId())
                                .customerId(order.getCustomer().getCustomerId())
                                .pastDays(utils.calculateDifferenceInDays(LocalDate.now(), order.getOrderDate()))
                                .orderDate(order.getOrderDate()).build())
                .collect(Collectors.toList());
        Iterable<UnfinishedOrder> ords = unfinishedOrderRepository.saveAll(unfinishedOrders);
    }

}
