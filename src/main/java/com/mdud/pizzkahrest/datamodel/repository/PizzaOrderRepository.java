package com.mdud.pizzkahrest.datamodel.repository;

import com.mdud.pizzkahrest.datamodel.entity.PizzaOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PizzaOrderRepository extends CrudRepository<PizzaOrder, Long> {
    Optional<PizzaOrder> findAllByDoneTrue();
}
