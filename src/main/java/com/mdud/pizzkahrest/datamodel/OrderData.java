package com.mdud.pizzkahrest.datamodel;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "order_data")
@Data
public class OrderData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private PizzaOrder pizzaOrder;

    @ManyToOne
    @JoinColumn(name = "pizza_id")
    private Pizza pizza;

    public OrderData() {}

    public OrderData(PizzaOrder pizzaOrder, Pizza pizza) {
        this.pizzaOrder = pizzaOrder;
        this.pizza = pizza;
    }
}
