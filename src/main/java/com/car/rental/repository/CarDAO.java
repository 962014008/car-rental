package com.car.rental.repository;

import com.car.rental.model.dto.CarDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarDAO extends JpaRepository<CarDto, Long> {

    @Query("from CarDto as c where c.id not in (select b.car.id from BorrowedDateDto as b)")
    List<CarDto> newCars();

    @Override
    List<CarDto> findAll();

    CarDto findById(Long id);
}
