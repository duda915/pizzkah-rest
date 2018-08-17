package com.mdud.pizzkahrest.datamodel.repository;

import com.mdud.pizzkahrest.datamodel.entity.Manager;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends CrudRepository<Manager, Long> {
}
