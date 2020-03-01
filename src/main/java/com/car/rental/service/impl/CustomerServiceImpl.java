package com.car.rental.service.impl;

import com.car.rental.model.dto.CustomerDto;
import com.car.rental.repository.CustomerDAO;
import com.car.rental.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerDAO customerDAO;

    public CustomerServiceImpl(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @Override
    public CustomerDto findById(Long id) {
        return customerDAO.findById(id);
    }

    @Override
    public List<CustomerDto> findAll() {
        return customerDAO.findAll();
    }

    @Override
    public void save(CustomerDto customer) {
        customerDAO.save(customer);
    }
}
