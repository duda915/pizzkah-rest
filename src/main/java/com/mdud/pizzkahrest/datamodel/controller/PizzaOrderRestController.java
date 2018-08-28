package com.mdud.pizzkahrest.datamodel.controller;

import com.mdud.pizzkahrest.datamodel.entity.OrderData;
import com.mdud.pizzkahrest.datamodel.entity.PizzaOrder;
import com.mdud.pizzkahrest.datamodel.repository.OrderDataRepository;
import com.mdud.pizzkahrest.datamodel.repository.PizzaOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/order")
public class PizzaOrderRestController {

    @Autowired
    private PizzaOrderRepository pizzaOrderRepository;

    @Autowired
    private OrderDataRepository orderDataRepository;


    @GetMapping
    public List<PizzaOrder> getPendingOrders() {
        List<PizzaOrder> pizzaOrders = new ArrayList<>();

        pizzaOrderRepository.findAllByDoneFalse().forEach(pizzaOrder -> {

            pizzaOrder.calculateTotalPrice();
            pizzaOrders.add(pizzaOrder);

        });

        return pizzaOrders;
    }

    @GetMapping("/completed")
    public List<PizzaOrder> getCompletedOrders() {
        List<PizzaOrder> pizzaOrders = new ArrayList<>();

        pizzaOrderRepository.findAllByDoneTrue().forEach(pizzaOrder -> {

            pizzaOrder.calculateTotalPrice();
            pizzaOrders.add(pizzaOrder);
        });

        return pizzaOrders;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public PizzaOrder addOrder(@RequestBody() PizzaOrder pizzaOrder) {
        List<OrderData> orderDataList = pizzaOrder.getOrderDataList();
        pizzaOrder.setOrderDataList(null);
        PizzaOrder newOrder = pizzaOrderRepository.save(pizzaOrder);

        for(OrderData od : orderDataList) {
            od.setPizzaOrder(newOrder);
            orderDataRepository.save(od);
        }

        newOrder = pizzaOrderRepository.findById(newOrder.getId()).get();

        return newOrder;
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public PizzaOrder markOrderAsDone(@RequestBody() Long orderId) {
        PizzaOrder pizzaOrder = pizzaOrderRepository.findById(orderId).get();
        pizzaOrder.setDone(true);
        pizzaOrder = pizzaOrderRepository.save(pizzaOrder);
        return pizzaOrder;
    }
}
