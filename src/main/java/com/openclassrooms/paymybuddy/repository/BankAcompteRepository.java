package com.openclassrooms.paymybuddy.repository;

import com.openclassrooms.paymybuddy.entity.BankAcompte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAcompteRepository extends JpaRepository<BankAcompte,Integer> {
}
