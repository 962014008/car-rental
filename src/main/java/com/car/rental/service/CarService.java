package com.car.rental.service;

import com.car.rental.model.dto.CarDto;

import java.util.List;

public interface CarService {
    List<CarDto> findAll();

    List<CarDto> newCars();

    CarDto findById(Long id);

    void save(CarDto car);
}
