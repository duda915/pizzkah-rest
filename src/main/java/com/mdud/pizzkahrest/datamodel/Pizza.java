package com.mdud.pizzkahrest.datamodel;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "pizza")
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String ingredients;
    private BigDecimal price;

    @Column(name = "available")
    private Boolean isAvailable;

    public Pizza() {}

    public Pizza(String name, String ingredients, BigDecimal price, Boolean isAvailable) {
        this.name = name;
        this.ingredients = ingredients;
        this.price = price;
        this.isAvailable = isAvailable;
    }

}