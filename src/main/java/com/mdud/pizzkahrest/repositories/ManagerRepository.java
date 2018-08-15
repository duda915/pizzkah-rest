package com.mdud.pizzkahrest.repositories;

import com.mdud.pizzkahrest.datamodel.Manager;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends CrudRepository<Manager, Long> {
}
