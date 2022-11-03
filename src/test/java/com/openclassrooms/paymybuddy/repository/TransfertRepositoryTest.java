package com.openclassrooms.paymybuddy.repository;

import com.openclassrooms.paymybuddy.entity.Transfert;
import com.openclassrooms.paymybuddy.entity.UserAcompte;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TransfertRepositoryTest {
    @Autowired
    private TransfertRepository transfertRepository;

    @Test
    void saveMethod(){
        Transfert transfert = new Transfert();

        transfert.setDescription("jules");
        transfert.setMontant(3000);
        transfert.setDate("12/11/2022");
    }
}
