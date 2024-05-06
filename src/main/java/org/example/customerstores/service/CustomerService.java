package org.example.customerstores.service;

import lombok.extern.slf4j.Slf4j;
import org.example.customerstores.exception.NotFoundException;
import org.example.customerstores.mapper.Mapper;
import org.example.customerstores.model.Customer;
import org.example.customerstores.model.dto.CustomerDto;
import org.example.customerstores.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Slf4j
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final Mapper<Customer, CustomerDto> customerMapper;

    public CustomerService(CustomerRepository customerRepository, Mapper<Customer, CustomerDto> customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public CustomerDto findDtoById(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found Customer Id " + id));
        return customerMapper.toDto(customer);
    }

    public CustomerDto saveCustomer(CustomerDto customerDto) {
        Customer customer = customerMapper.toModel(customerDto);
        return customerMapper.toDto(customerRepository.save(customer));
    }

    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }

    public CustomerDto findCustomerByFirstLastName(String firstName, String lastName) {
        return customerMapper.toDto(
                customerRepository.getCustomerByFirstLastName(firstName, lastName)
                        .orElseThrow(() -> new NotFoundException("Did not find customer with first and last names" + firstName + " " + lastName))
        );
    }

}
