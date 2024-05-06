package org.example.customerstores.mapper.Impl;

import org.example.customerstores.mapper.Mapper;
import org.example.customerstores.model.Customer;
import org.example.customerstores.model.dto.CustomerDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper implements Mapper<Customer, CustomerDto> {

    private final ModelMapper modelMapper;

    public CustomerMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public CustomerDto toDto(Customer customer) {
        return modelMapper.map(customer, CustomerDto.class);
    }

    @Override
    public Customer toModel(CustomerDto customerDto) {
        return modelMapper.map(customerDto, Customer.class);
    }
}
