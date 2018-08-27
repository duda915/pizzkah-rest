package com.mdud.pizzkahrest.datamodel.controller;

import com.mdud.pizzkahrest.datamodel.entity.Pizza;
import com.mdud.pizzkahrest.datamodel.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/pizza")
public class PizzaRestController {

    @Autowired
    private PizzaRepository pizzaRepository;

    @GetMapping
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

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Pizza addPizza(@RequestBody() Pizza pizza) {
        Pizza newPizza = pizzaRepository.save(new Pizza(pizza));
        return newPizza;
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Pizza updatePizza(@RequestParam(value = "id") Long pizzaId, @RequestBody Pizza pizza) {
        Pizza updatePizza = pizzaRepository.findById(pizzaId).get();

        updatePizza.setIngredients(pizza.getIngredients());
        updatePizza.setAvailable(pizza.getAvailable());
        updatePizza.setName(pizza.getName());
        updatePizza.setPrice(pizza.getPrice());

        updatePizza = pizzaRepository.save(updatePizza);
        return updatePizza;
    }
}
