package com.car.rental.service.impl;

import com.car.rental.model.dto.CarDto;
import com.car.rental.repository.CarDAO;
import com.car.rental.service.CarService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    private CarDAO carDAO;

    public CarServiceImpl(CarDAO carDAO) {
        this.carDAO = carDAO;
    }

    @Override
    public void save(CarDto car) {
        carDAO.save(car);
    }

    @Override
    public List<CarDto> newCars() {
        return carDAO.newCars();
    }

    @Override
    public List<CarDto> findAll() {
        return carDAO.findAll();
    }

    @Override
    public CarDto findById(Long id) {
        return carDAO.findById(id);
    }
}
