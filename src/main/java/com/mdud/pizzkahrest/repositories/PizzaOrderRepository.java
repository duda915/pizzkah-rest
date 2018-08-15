package com.mdud.pizzkahrest.repositories;

import com.mdud.pizzkahrest.datamodel.PizzaOrder;
import org.springframework.data.repository.CrudRepository;

public interface PizzaOrderRepository extends CrudRepository<PizzaOrder, Long> {
    
}
