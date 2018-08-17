package com.mdud.pizzkahrest.datamodel.controller;

import com.mdud.pizzkahrest.datamodel.entity.Pizza;
import com.mdud.pizzkahrest.datamodel.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping("/api/pizza")
public class PizzaRestController {

    @Autowired
    private PizzaRepository pizzaRepository;

    @GetMapping()
    public List<Pizza> getPizzaList(@RequestParam(required = false, value = "available", defaultValue = "true")
                                                Boolean onlyAvailable) {
        List<Pizza> pizzaList = new ArrayList<>();
        pizzaRepository.findAll().forEach(pizza -> {
            if(onlyAvailable && pizza.getAvailable())
                pizzaList.add(pizza);
            else if(!onlyAvailable)
                pizzaList.add(pizza);
        });
        return pizzaList;
    }
}
