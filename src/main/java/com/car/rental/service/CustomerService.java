package com.car.rental.service;

import com.car.rental.model.dto.CustomerDto;

import java.util.List;

public interface CustomerService {

    List<CustomerDto> findAll();

    CustomerDto findById(Long id);

    void save(CustomerDto customer);
}
