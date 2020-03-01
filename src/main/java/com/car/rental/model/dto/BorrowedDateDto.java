package com.car.rental.model.dto;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

@Entity
@Table(name = "borrowed_date")
@Data
public class BorrowedDateDto implements Serializable {
    private static final long serialVersionUID = -1713505055304086201L;

    @Id
    @Column(name = "borrowed_date_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "start_date")
    private Calendar startDate;

    @Column(name = "end_date")
    private Calendar endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    private CarDto car;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private CustomerDto customer;

    @Override
    public String toString() {
        return "BorrowedDateDto{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", car=" + car +
                ", customer=" + customer +
                '}';
    }
}
