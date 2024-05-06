package org.example.customerstores.repository;

import org.example.customerstores.model.UnfinishedOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UnfinishedOrderRepository extends CrudRepository<UnfinishedOrder, Long> {

    @Query("SELECT o FROM UnfinishedOrder o WHERE o.customerId = ?1")
    Iterable<UnfinishedOrder> getCustomerUnfinishedOrders(Long customerId);
}
