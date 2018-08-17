package com.mdud.pizzkahrest.utility;

import com.mdud.pizzkahrest.datamodel.entity.Manager;
import com.mdud.pizzkahrest.datamodel.entity.OrderData;
import com.mdud.pizzkahrest.datamodel.entity.Pizza;
import com.mdud.pizzkahrest.datamodel.entity.PizzaOrder;
import com.mdud.pizzkahrest.datamodel.repository.ManagerRepository;
import com.mdud.pizzkahrest.datamodel.repository.PizzaOrderRepository;
import com.mdud.pizzkahrest.datamodel.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    private PizzaRepository pizzaRepository;
    private PizzaOrderRepository pizzaOrderRepository;
    private ManagerRepository managerRepository;

    @Autowired
    public DatabaseInitializer(PizzaRepository pizzaRepository, PizzaOrderRepository pizzaOrderRepository,
                               ManagerRepository managerRepository) {
        this.pizzaRepository = pizzaRepository;
        this.pizzaOrderRepository = pizzaOrderRepository;
        this.managerRepository = managerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        //Pizza repo init

        managerRepository.deleteAll();
        pizzaOrderRepository.deleteAll();
        pizzaRepository.deleteAll();

        PizzaOrder pizzaOrder = new PizzaOrder(new Date(), "Walter", "Smith",
                "999999999", "Warsaw", false);

        Arrays.asList("Cheese pizza", "Ham pizza", "Onion pizza", "Paprika pizza").forEach(name -> {
            Pizza pizza = pizzaRepository.save(
                    new Pizza(name, "Cheese", new BigDecimal(26.33), true)
            );
        });
        Pizza pizza = pizzaRepository.findDistinctByName("Cheese pizza").get();
        pizzaOrder.getOrderDataList().add(new OrderData(pizzaOrder, pizza));
        pizza = pizzaRepository.findDistinctByName("Ham pizza").get();
        pizzaOrder.getOrderDataList().add(new OrderData(pizzaOrder, pizza));
        pizza = pizzaRepository.findDistinctByName("Onion pizza").get();
        pizzaOrder.getOrderDataList().add(new OrderData(pizzaOrder, pizza));
        pizza = pizzaRepository.findDistinctByName("Paprika pizza").get();
        pizzaOrder.getOrderDataList().add(new OrderData(pizzaOrder, pizza));
        pizzaOrderRepository.save(pizzaOrder);

        Manager manager = managerRepository.save(new Manager("username", "password",
                "all"));
    }
}
