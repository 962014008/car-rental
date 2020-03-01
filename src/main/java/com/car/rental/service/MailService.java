package com.car.rental.service;

import com.car.rental.model.dto.BorrowedDateDto;
import com.car.rental.model.dto.CarDto;
import com.car.rental.model.dto.CustomerDto;

public interface MailService {

    void sendMailTest();

    void sendMail(CustomerDto customer, BorrowedDateDto borrowedDate, CarDto car);
}
