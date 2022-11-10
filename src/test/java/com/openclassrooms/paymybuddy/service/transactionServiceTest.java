package com.openclassrooms.paymybuddy.service;

import com.openclassrooms.paymybuddy.entity.Transaction;
import com.openclassrooms.paymybuddy.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class transactionServiceTest {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    TransactionService transactionService;

    @BeforeEach
    void cleanDb() {
        this.transactionRepository.deleteAll();
    }

    @Test
    void saveTransactionServiceTest() {
        Transaction transaction = new Transaction("12/11/2022", 45, 5);
        Transaction transactionsaved = transactionService.saveTransaction(transaction);

        Transaction savedTransaction = transactionService.getTransactionById(transactionsaved.getTransaction_Id());

        assertEquals(45, savedTransaction.getMontant());
    }


    @Test
    void updateTransactionServiceTest() {

        Transaction transaction = new Transaction("12/11/2022", 45, 5);

        Transaction updateTransaction = new Transaction("14/11/2022", 65, 7);


        Transaction transactionsaved = transactionService.saveTransaction(transaction);

        Transaction transactionUpdated = transactionService.updateTransaction(updateTransaction, transaction.getTransaction_Id());

        assertEquals(65, transactionUpdated.getMontant());
    }

    @Test
    void  deleteTransactionServiceTest(){
        Transaction transaction = new Transaction("12/11/2022", 45, 5);
        Transaction transactionsaved = transactionService.saveTransaction(transaction);

        transactionService.deleteTransaction(transactionsaved.getTransaction_Id());

        assertEquals(0,transactionRepository.findAll().size());
    }

    @Test
    void findAllTransactionServiceTest(){
        Transaction transaction1 = new Transaction("12/11/2022", 45, 5);

        Transaction transaction2 = new Transaction("14/11/2022", 65, 7);

        transactionService.saveTransaction(transaction1);
        transactionService.saveTransaction(transaction2);

        transactionService.findAllTransactions();

        assertEquals(2,transactionRepository.findAll().size());
    }
}
