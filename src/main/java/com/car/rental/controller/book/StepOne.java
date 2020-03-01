package com.car.rental.controller.book;

import com.car.rental.model.dto.BorrowedDateDto;
import com.car.rental.model.dto.CarDto;
import com.car.rental.model.dto.CustomerDto;
import com.car.rental.model.vo.AvailableCarsResultVo;
import com.car.rental.service.BorrowedDateService;
import com.car.rental.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Calendar;
import java.util.List;

@Controller
@SessionAttributes({"customer", "borrowed_date", "car"})
public class StepOne {

    @Autowired
    private CarService carService;

    @Autowired
    private BorrowedDateService borrowedDateService;

    @RequestMapping(value = "/stepOne{car_id}", method = RequestMethod.GET)
    public String checkDates(Model model, @RequestParam(value = "car_id") Long carId,
                             @RequestParam(value = "start_date", defaultValue = "2000-01-01", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Calendar startDate,
                             @RequestParam(value = "end_date", defaultValue = "3000-01-01", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Calendar endDate) {
        CarDto car = carService.findById(carId);
        List<AvailableCarsResultVo> availableCar = borrowedDateService.checkAvailableCarById(startDate, endDate, carId);
        model.addAttribute("car", car);
        model.addAttribute("availableCar", availableCar);
        return "stepOne";
    }

    @RequestMapping(value = "/stepOne", method = RequestMethod.POST)
    public String createNewCustomer(Model model, CustomerDto customer, BorrowedDateDto borrowedDate,
                                    RedirectAttributes redirectAttributes, @RequestParam(value = "car_id") Long carId,
                                    @RequestParam(value = "start_date", defaultValue = "2000-01-01", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Calendar startDate,
                                    @RequestParam(value = "end_date", defaultValue = "3000-01-01", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Calendar endDate) {
        CarDto car = carService.findById(carId);
        customer.setRole("ROLE_USER");
        customer.getCars().add(car);
        customer.setBorrowedCars(customer.getBorrowedCars() + 1);
        customer.setTotalPrice(customer.getTotalPrice().add(car.getPrice()));
        borrowedDate.setCar(car);
        borrowedDate.setStartDate(startDate);
        borrowedDate.setEndDate(endDate);
        redirectAttributes.addAttribute("car_id", carId);
        model.addAttribute("borrowed_date", borrowedDate);
        model.addAttribute("customer", customer);
        return "redirect:/stepTwo";
    }
}
