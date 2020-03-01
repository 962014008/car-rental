package com.car.rental.repository;

import com.car.rental.model.dto.CustomerDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerDAO extends JpaRepository<CustomerDto, Long> {

    @Override
    List<CustomerDto> findAll();

    CustomerDto findById(Long id);
}
