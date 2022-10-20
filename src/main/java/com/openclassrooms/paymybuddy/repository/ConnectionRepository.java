package com.openclassrooms.paymybuddy.repository;

import com.openclassrooms.paymybuddy.model.Connection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConnectionRepository extends CrudRepository<Connection,Integer>{

}
