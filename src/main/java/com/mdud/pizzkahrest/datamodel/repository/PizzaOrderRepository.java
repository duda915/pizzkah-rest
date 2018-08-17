package com.mdud.pizzkahrest.datamodel.repository;

import com.mdud.pizzkahrest.datamodel.entity.PizzaOrder;
import org.springframework.data.repository.CrudRepository;

public interface PizzaOrderRepository extends CrudRepository<PizzaOrder, Long> {
    
}
