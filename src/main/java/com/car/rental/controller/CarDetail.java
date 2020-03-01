package com.car.rental.controller;

import com.car.rental.model.dto.CarDto;
import com.car.rental.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CarDetail {

    @Autowired
    private CarService carService;

    @RequestMapping(value = "/carDetail{car_id}", method = RequestMethod.GET)
    public String carDetail(Model model, @RequestParam(value = "car_id") Long carId) {
        CarDto car = carService.findById(carId);
        model.addAttribute("car", car);
        return "carDetail";
    }
}