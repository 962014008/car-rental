package com.car.rental.controller.book;

import com.car.rental.model.dto.BorrowedDateDto;
import com.car.rental.model.dto.CustomerDto;
import com.car.rental.service.BorrowedDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;

@Controller
@SessionAttributes({"customer", "borrowed_date", "car"})
public class StepThree {

    @Autowired
    private BorrowedDateService borrowedDateService;

    @RequestMapping(value = "stepThree{car_id}", method = RequestMethod.GET)
    public String showSessionCar() {
        return "stepThree";
    }

    @RequestMapping(value = "stepThree", method = RequestMethod.POST)
    public String realizePayment(Model model, String cardNumber, String cvv, RedirectAttributes redirectAttributes, @RequestParam(value = "car_id") Long carId) {
        BorrowedDateDto borrowedDate = (BorrowedDateDto) model.asMap().get("borrowed_date");
        long days = borrowedDateService.countDays(borrowedDate);
        CustomerDto customer = (CustomerDto) model.asMap().get("customer");
        customer.setTotalPrice(customer.getTotalPrice().multiply(new BigDecimal(days)));
        customer.setCardNumber(BigDecimal.valueOf(Double.parseDouble(cardNumber)));
        customer.setCvv(BigDecimal.valueOf(Double.parseDouble(cvv)));
        redirectAttributes.addAttribute("car_id", carId);
        return "redirect:/stepFinal";
    }
}
