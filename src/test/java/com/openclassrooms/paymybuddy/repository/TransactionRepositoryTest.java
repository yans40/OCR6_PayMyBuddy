package com.openclassrooms.paymybuddy.repository;

import com.openclassrooms.paymybuddy.entity.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TransactionRepositoryTest {

    @Autowired
    private TransactionRepository transactionRepository;

    @BeforeEach
    void cleanDb() {
        this.transactionRepository.deleteAll();
    }

    @Test
    void saveTest(){
        Transaction transaction = new Transaction();
        transaction.setMontant(200);
        transaction.setDate("22/11/2022");
        transaction.setFrais(20);

        transactionRepository.save(transaction);
        Transaction transactionSavedInDb = transactionRepository.findById(transaction.getTransaction_Id()).orElseThrow(NoSuchElementException::new);

        assertEquals(200, transactionSavedInDb.getMontant());

    }


    @Test
    void findTest(){

        Transaction transaction = new Transaction("12/11/2022", 45, 5);
        Transaction transactionsaved = transactionRepository.save(transaction);

        Optional<Transaction> transactionToFind = transactionRepository.findById(transactionsaved.getTransaction_Id());

        assertEquals(45, transactionToFind.get().getMontant());

    }

    @Test
    void deleteTest(){
        Transaction transaction = new Transaction("12/11/2022", 45, 5);
        Transaction transactionsaved = transactionRepository.save(transaction);

        transactionRepository.delete(transactionsaved);

        assertEquals(0,transactionRepository.findAll().size());

    }
}
