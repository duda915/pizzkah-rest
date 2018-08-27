package com.mdud.pizzkahrest.datamodel.controller;

import com.mdud.pizzkahrest.datamodel.entity.PizzaOrder;
import com.mdud.pizzkahrest.datamodel.repository.PizzaOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/order")
public class PizzaOrderRestController {

    @Autowired
    private PizzaOrderRepository pizzaOrderRepository;

    @GetMapping
    public List<PizzaOrder> getPendingOrders() {
        List<PizzaOrder> pizzaOrders = new ArrayList<>();

        pizzaOrderRepository.findAllByDoneFalse().forEach(pizzaOrder -> {
            pizzaOrders.add(pizzaOrder);
        });

        return pizzaOrders;
    }
}
