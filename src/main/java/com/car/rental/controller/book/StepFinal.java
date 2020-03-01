package com.car.rental.controller.book;

import com.car.rental.model.dto.BorrowedDateDto;
import com.car.rental.model.dto.CarDto;
import com.car.rental.model.dto.CustomerDto;
import com.car.rental.model.vo.AvailableCarsResultVo;
import com.car.rental.service.BorrowedDateService;
import com.car.rental.service.CustomerService;
import com.car.rental.service.MailService;
import it.ozimov.springboot.mail.configuration.EnableEmailTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;

@Controller
@EnableEmailTools
@SessionAttributes({"customer", "borrowed_date", "car"})
public class StepFinal {

    @Autowired
    private BorrowedDateService borrowedDateService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private MailService mailService;

    @RequestMapping(value = "stepFinal{car_id}", method = RequestMethod.GET)
    public String showCustomerResume() {
        return "stepFinal";
    }

    @RequestMapping(value = "stepFinal", method = RequestMethod.POST)
    @Transactional(rollbackFor = Exception.class)
    public String completeAll(Model model, SessionStatus status) throws Exception {
        BorrowedDateDto borrowedDate = (BorrowedDateDto) model.asMap().get("borrowed_date");
        CustomerDto customer = (CustomerDto) model.asMap().get("customer");
        CarDto car = (CarDto) model.asMap().get("car");

        // TODO: Administrator 2020/3/1 16:00 应该引入分布式锁，来严格保证数据一致性，这里暂且不实现
        // 重新查询当前库存数量，保证库存大于等于1
        try {
            List<AvailableCarsResultVo> availableCar = borrowedDateService.checkAvailableCarById(borrowedDate.getStartDate(), borrowedDate.getEndDate(), car.getId());
            if (availableCar.isEmpty()) {
                throw new Exception("quantity of the car is not enough.");
            }
        } catch (Exception e) {
            throw e;
        }
        customer.setPaid(true);
        borrowedDateService.save(borrowedDate);
        customerService.save(customer);
        mailService.sendMail(customer, borrowedDate, car);
        status.setComplete();
        // TODO: Administrator 2020/3/1 16:12 TODO 后续应该改成跳转到订单总览页面
        return "redirect:/";
    }
}
