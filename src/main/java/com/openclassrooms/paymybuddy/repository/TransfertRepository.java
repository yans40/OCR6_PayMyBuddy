package com.openclassrooms.paymybuddy.repository;

import com.openclassrooms.paymybuddy.model.Transfert;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransfertRepository extends CrudRepository<Transfert,Integer> {

}
