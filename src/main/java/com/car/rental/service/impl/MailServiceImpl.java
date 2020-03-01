package com.car.rental.service.impl;

import com.car.rental.model.dto.BorrowedDateDto;
import com.car.rental.model.dto.CarDto;
import com.car.rental.model.dto.CustomerDto;
import com.car.rental.service.MailService;
import com.google.common.collect.Lists;
import it.ozimov.springboot.mail.model.Email;
import it.ozimov.springboot.mail.model.defaultimpl.DefaultEmail;
import it.ozimov.springboot.mail.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private EmailService emailService;

    @Value("${mail.fromMail.addr}")
    private String fromMailAddress;

    @Override
    public void sendMail(CustomerDto customer, BorrowedDateDto borrowedDate, CarDto car) {
        try {
            final Email email = DefaultEmail.builder().from(new InternetAddress(fromMailAddress))
                    .to(Lists.newArrayList(new InternetAddress(customer.getLogin())))
                    .subject("Resume of your orders in Car Rental")
                    .body("Dear, " + customer.getFullName() + "\n\nThank You for borrowing our car."
                            + "\nHere is resume of your order." + "\nChosen car: " + car.getName() + ", "
                            + car.getPrice() + " EUR/day" + "\nBorrowed date range: " + borrowedDate.getStartDate()
                            + " - " + borrowedDate.getEndDate() + "\nTotal price: " + customer.getTotalPrice() + " EUR")
                    .encoding("UTF-8").build();
            emailService.send(email);
        } catch (AddressException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void sendMailTest() {
        try {
            final Email email = DefaultEmail.builder()
                    .from(new InternetAddress("962014008@qq.com"))
                    .to(Lists.newArrayList(new InternetAddress("962014008@qq.com")))
                    .subject("Resume of your orders in Car Rental")
                    .body("Dear, ")
                    .encoding("UTF-8")
                    .build();
            emailService.send(email);
        } catch (AddressException e) {
            System.out.println(e.getMessage());
        }
    }
}
