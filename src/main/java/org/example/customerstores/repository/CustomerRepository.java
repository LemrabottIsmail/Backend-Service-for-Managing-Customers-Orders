package org.example.customerstores.repository;

import org.example.customerstores.model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    @Query("SELECT c FROM Customer c WHERE c.firstName = ?1 AND c.lastName = ?2")
    Optional<Customer> getCustomerByFirstLastName(String firstName, String lastName);
}
