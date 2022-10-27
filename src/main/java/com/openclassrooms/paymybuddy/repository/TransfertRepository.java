package com.openclassrooms.paymybuddy.repository;

import com.openclassrooms.paymybuddy.entity.Transfert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransfertRepository extends JpaRepository<Transfert,Integer> {

}
