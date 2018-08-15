package com.mdud.pizzkahrest.repositories;

import com.mdud.pizzkahrest.datamodel.Pizza;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface PizzaRepository extends CrudRepository<Pizza, Long> {
    Optional<Pizza> findDistinctByName(String name);
}
