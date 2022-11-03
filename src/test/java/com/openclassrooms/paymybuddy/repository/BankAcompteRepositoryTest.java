package com.openclassrooms.paymybuddy.repository;

import com.openclassrooms.paymybuddy.entity.BankAcompte;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BankAcompteRepositoryTest {


        @Autowired
        private BankAcompteRepository bankAcompteRepository;

        @Test
        void saveMethod(){
            BankAcompte bankAcompte = new BankAcompte();

            bankAcompte.setIban("1244 3000 5678910 57");

            BankAcompte bankAcompteToTest = bankAcompteRepository.save(bankAcompte);

            assertEquals("1244 3000 5678910 57",bankAcompteToTest.getIban());

        }

}
