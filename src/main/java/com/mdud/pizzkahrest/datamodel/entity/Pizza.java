package com.mdud.pizzkahrest.datamodel.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "pizza")
public class Pizza  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String ingredients;
    private BigDecimal price;

    @Column(name = "available")
    private Boolean available;

    public Pizza() {}

    public Pizza(String name, String ingredients, BigDecimal price, Boolean isAvailable) {
        this.name = name;
        this.ingredients = ingredients;
        this.price = price;
        this.available = isAvailable;
    }

    public Pizza(Pizza pizza) {
        this.name = pizza.name;
        this.ingredients = pizza.ingredients;
        this.price = pizza.price;
        this.available = pizza.available;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
