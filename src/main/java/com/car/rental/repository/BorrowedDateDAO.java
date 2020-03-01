package com.car.rental.repository;

import com.car.rental.model.dto.BorrowedDateDto;
import com.car.rental.model.vo.AvailableCarsResultVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.List;

@Repository
public interface BorrowedDateDAO extends JpaRepository<BorrowedDateDto, Long> {
    BorrowedDateDto findByCustomerId(Long id);

    BorrowedDateDto findByCarId(Long id);

    @Override
    List<BorrowedDateDto> findAll();
    
    @Query("select NEW com.car.rental.model.vo.AvailableCarsResultVo " +
            "(b.id, b.car.id, b.car.name, b.car.description, b.car.price, b.car.quantity) " +
            "from BorrowedDateDto as b " +
            "where :quantity > 0 " +
            "and :startDate not between b.startDate and b.endDate " +
            "and :endDate not between b.startDate and b.endDate " +
            "and b.car.id NOT IN (select DISTINCT bd.car.id " +
            "from BorrowedDateDto bd " +
            "where :startDate between bd.startDate and bd.endDate " +
            "OR :endDate between bd.startDate and bd.endDate) " +
            "group by b.car.id")
    List<AvailableCarsResultVo> checkAvailableCars(@Param("startDate") Calendar startDate,
                                                   @Param("endDate") Calendar endDate);

    @Query("select NEW com.car.rental.model.vo.AvailableCarsResultVo " +
            "(b.id, b.car.id, b.car.name, b.car.description, b.car.price, b.car.quantity) " +
            "from BorrowedDateDto as b " +
            "where :quantity > 0 " +
            "and :startDate not between b.startDate and b.endDate " +
            "and :endDate not between b.startDate and b.endDate " +
            "and b.car.id = :carId " +
            "and b.car.id NOT IN (select DISTINCT bd.car.id " +
            "from BorrowedDateDto bd " +
            "where :startDate between bd.startDate and bd.endDate " +
            "OR :endDate between bd.startDate and bd.endDate)")
    List<AvailableCarsResultVo> checkAvailableCarById(@Param("startDate") Calendar startDate,
                                                      @Param("endDate") Calendar endDate,
                                                      @Param("carId") Long id);
}
