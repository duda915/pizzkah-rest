package com.mdud.pizzkahrest.repositories;

import com.mdud.pizzkahrest.datamodel.Pizza;
import org.springframework.data.repository.CrudRepository;


public interface PizzaRepository extends CrudRepository<Pizza, Long> {
}
