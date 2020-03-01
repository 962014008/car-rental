package com.car.rental.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class AvailableCarsResultVo implements Serializable {
    private static final long serialVersionUID = 5579917950441768883L;

    public AvailableCarsResultVo(Long id, Long carId, String carName, String carDescription, BigDecimal carPrice, Long quantity) {
        this.id = id;
        this.carId = carId;
        this.carName = carName;
        this.carDescription = carDescription;
        this.carPrice = carPrice;
        this.quantity = quantity;
    }

    private Long id;
    private Long carId;
    private String carName;
    private String carDescription;
    private BigDecimal carPrice;
    private Long quantity;

    @Override
    public String toString() {
        return "AvailableCarsResultVo{" +
                "id=" + id +
                ", carId=" + carId +
                ", carName='" + carName + '\'' +
                ", carDescription='" + carDescription + '\'' +
                ", carPrice=" + carPrice +
                ", quantity=" + quantity +
                '}';
    }
}