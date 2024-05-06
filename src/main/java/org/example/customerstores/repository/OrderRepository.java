package org.example.customerstores.repository;

import org.example.customerstores.model.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
    @Query("SELECT o FROM Order o WHERE o.customer.customerId = ?1 ")
    Iterable<Order> getOrdersByCustomerId(Long id);

    @Query("SELECT o FROM Order o WHERE o.status.orderStatusId = 1 AND o.customer.customerId = ?1 ")
    Iterable<Order> getOrdersByStatusAndCustomerId(Long id);

}
