package com.car.rental.model.dto;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "car")
@Data
public class CarDto implements Serializable {
    private static final long serialVersionUID = 7034352443015914334L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name", length = 100)
    private String name;

    @Size(min = 20, max = 300)
    @Column(name = "description", length = 300)
    private String description;

    @Digits(integer = 5, fraction = 2)
    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "quantity")
    private Long quantity;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "cars")
    private List<CustomerDto> customers = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "car")
    private List<BorrowedDateDto> borrowedDates = new ArrayList<>();

    /**
     * 注意toString不要放入customers和borrowedDates，否则会因为循环依赖报错StackOverflowError
     */
    @Override
    public String toString() {
        return "CarDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
