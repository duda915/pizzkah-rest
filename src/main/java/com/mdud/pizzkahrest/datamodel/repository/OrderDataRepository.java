package com.mdud.pizzkahrest.datamodel.repository;

import com.mdud.pizzkahrest.datamodel.entity.OrderData;
import com.mdud.pizzkahrest.datamodel.entity.PizzaOrder;
import org.springframework.data.repository.CrudRepository;

public interface OrderDataRepository extends CrudRepository<OrderData, Long> {
    Iterable<OrderData> findAllByPizzaOrder(PizzaOrder pizzaOrder);
}
