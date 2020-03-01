package com.car.rental.service;

import com.car.rental.model.dto.BorrowedDateDto;
import com.car.rental.model.vo.AvailableCarsResultVo;

import java.util.Calendar;
import java.util.List;

public interface BorrowedDateService {

    List<AvailableCarsResultVo> checkAvailableCarById(Calendar startDate, Calendar endDate, Long id);

    List<AvailableCarsResultVo> checkAvailableCars(Calendar startDate, Calendar endDate);

    List<BorrowedDateDto> findAll();

    BorrowedDateDto findByCustomerId(Long id);

    BorrowedDateDto findByCarId(Long id);

    void save(BorrowedDateDto borrowedDate);

    long countDays(BorrowedDateDto borrowedDate);
}
