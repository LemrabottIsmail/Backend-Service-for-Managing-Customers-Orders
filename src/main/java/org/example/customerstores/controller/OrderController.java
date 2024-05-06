package org.example.customerstores.controller;

import org.example.customerstores.model.dto.OrderDto;
import org.example.customerstores.model.dto.UnfinishedOrderDto;
import org.example.customerstores.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/orders")
public class OrderController {

    private final OrderService orderService;


    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(path = "/{id}")
    public OrderDto getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        orderService.deleteById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/customer-id/{id}")
    public List<OrderDto> findAllOrders(@PathVariable Long id) {
        return orderService.findOrdersByCustomerId(id);
    }

    @PostMapping()
    public OrderDto saveOrder(@RequestBody OrderDto orderDto) {
        return orderService.saveOrder(orderDto);
    }

    @GetMapping("/save-unfinished-orders")
    public void saveCustomerUnfinishedOrders(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        orderService.savedUnfinishedOrders(firstName, lastName);
    }

    @GetMapping("/get-unfinished-orders")
    public List<UnfinishedOrderDto> getCustomerUnfinishedOrders(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        return orderService.findUnfinishedOrders(firstName, lastName);
    }

}
