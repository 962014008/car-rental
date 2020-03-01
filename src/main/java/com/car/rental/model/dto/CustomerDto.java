package com.car.rental.model.dto;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
@Data
public class CustomerDto implements Serializable {
    private static final long serialVersionUID = -5499172417961772372L;

    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 2, max = 50)
    @Column(name = "login", length = 50)
    private String login;

    @Size(min = 5, max = 60)
    @Column(name = "password", length = 60)
    private String password;

    @Size(min = 2, max = 50)
    @Column(name = "full_name", length = 50)
    private String fullName;

    @Size(min = 5, max = 50)
    @Column(name = "role", length = 50)
    private String role;

    @Digits(integer = 10, fraction = 2)
    @Column(name = "total_price")
    private BigDecimal totalPrice = new BigDecimal(0);

    @Digits(integer = 16, fraction = 0)
    @Column(name = "card_number")
    private BigDecimal cardNumber;

    @Digits(integer = 3, fraction = 0)
    @Column(name = "cvv")
    private BigDecimal cvv;

    @Column(name = "is_paid")
    private boolean isPaid = false;

    @Column(name = "borrowed_cars")
    private int borrowedCars;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<CarDto> cars = new ArrayList<>();

    @Override
    public String toString() {
        return "CustomerDto{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", role='" + role + '\'' +
                ", totalPrice=" + totalPrice +
                ", cardNumber=" + cardNumber +
                ", cvv=" + cvv +
                ", isPaid=" + isPaid +
                ", borrowedCars=" + borrowedCars +
                ", cars=" + cars +
                '}';
    }
}