package com.car.rental.controller.book;

import com.car.rental.model.dto.CustomerDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@SessionAttributes({"customer", "borrowed_date", "car"})
public class StepTwo {

    @RequestMapping(value = "stepTwo{car_id}", method = RequestMethod.GET)
    public String showSessionCar() {
        return "stepTwo";
    }

    @RequestMapping(value = "stepTwo", method = RequestMethod.POST)
    public String completeCustomer(Model model, String fullName, String login, String password, RedirectAttributes redirectAttributes, @RequestParam(value = "car_id") Long carId) {
        CustomerDto customer = (CustomerDto) model.asMap().get("customer");
        customer.setFullName(fullName);
        customer.setLogin(login);
        customer.setPassword(password);
        model.addAttribute("customer", customer);
        redirectAttributes.addAttribute("car_id", carId);
        return "redirect:/stepThree";
    }
}
