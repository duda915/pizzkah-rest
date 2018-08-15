package com.mdud.pizzkahrest.utility;

import com.mdud.pizzkahrest.datamodel.Pizza;
import com.mdud.pizzkahrest.repositories.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    private PizzaRepository pizzaRepository;

    @Autowired
    public DatabaseInitializer(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        //Pizza repo init
        pizzaRepository.deleteAll();
        Arrays.asList("Cheese pizza", "Ham pizza", "Onion pizza", "Paprika pizza").forEach(name -> {
            Pizza pizza = pizzaRepository.save(
                    new Pizza(name, "Cheese", new BigDecimal(26.33), true)
            );
        });


    }
}
