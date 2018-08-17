package com.mdud.pizzkahrest.datamodel.repository;

import com.mdud.pizzkahrest.datamodel.entity.Pizza;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface PizzaRepository extends CrudRepository<Pizza, Long> {
    Optional<Pizza> findDistinctByName(String name);
}
