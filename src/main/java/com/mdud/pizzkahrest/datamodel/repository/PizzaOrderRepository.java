package com.mdud.pizzkahrest.datamodel.repository;

import com.mdud.pizzkahrest.datamodel.entity.PizzaOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PizzaOrderRepository extends CrudRepository<PizzaOrder, Long> {
    Iterable<PizzaOrder> findAllByDoneTrue();
    Iterable<PizzaOrder> findAllByDoneFalse();
}
