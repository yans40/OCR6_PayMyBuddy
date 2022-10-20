package com.openclassrooms.paymybuddy.repository;

import com.openclassrooms.paymybuddy.model.BankAcompte;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAcompteRepository extends CrudRepository<BankAcompte,Integer> {
}
