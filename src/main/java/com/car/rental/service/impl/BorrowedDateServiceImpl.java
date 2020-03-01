package com.car.rental.service.impl;

import com.car.rental.model.dto.BorrowedDateDto;
import com.car.rental.model.vo.AvailableCarsResultVo;
import com.car.rental.repository.BorrowedDateDAO;
import com.car.rental.service.BorrowedDateService;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class BorrowedDateServiceImpl implements BorrowedDateService {

    private BorrowedDateDAO borrowedDateDAO;

    public BorrowedDateServiceImpl(BorrowedDateDAO borrowedDateDAO) {
        this.borrowedDateDAO = borrowedDateDAO;
    }

    @Override
    public BorrowedDateDto findByCustomerId(Long id) {
        return borrowedDateDAO.findByCustomerId(id);
    }

    @Override
    public BorrowedDateDto findByCarId(Long id) {
        return borrowedDateDAO.findByCarId(id);
    }

    @Override
    public List<BorrowedDateDto> findAll() {
        return borrowedDateDAO.findAll();
    }

    @Override
    public void save(BorrowedDateDto borrowedDate) {
        borrowedDateDAO.save(borrowedDate);
    }

    @Override
    public List<AvailableCarsResultVo> checkAvailableCars(Calendar startDate, Calendar endDate) {
        return borrowedDateDAO.checkAvailableCars(startDate, endDate);
    }

    @Override
    public List<AvailableCarsResultVo> checkAvailableCarById(Calendar startDate, Calendar endDate, Long id) {
        return borrowedDateDAO.checkAvailableCarById(startDate, endDate, id);
    }

    @Override
    public long countDays(BorrowedDateDto borrowedDate) {
        long days;
        Calendar start = borrowedDate.getStartDate();
        Calendar end = borrowedDate.getEndDate();
        days = daysBetween(start, end);
        return days;
    }

    private long daysBetween(Calendar startDate, Calendar endDate) {
        endDate.add(Calendar.DATE, 1);
        long end = endDate.getTimeInMillis();
        long start = startDate.getTimeInMillis();
        return TimeUnit.MILLISECONDS.toDays(Math.abs(end - start));
    }
}
