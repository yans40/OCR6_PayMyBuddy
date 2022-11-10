package com.openclassrooms.paymybuddy.controller;

import com.openclassrooms.paymybuddy.entity.Transaction;
import com.openclassrooms.paymybuddy.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/addTransaction")
    public Transaction addTransaction(@RequestBody Transaction transaction) {
        return transactionService.saveTransaction(transaction);
    }

    @GetMapping("/transactions")
    public List<Transaction> getAllTransactions() {
        return transactionService.findAllTransactions();
    }

    @GetMapping("/transaction/{id}")
    public Transaction getUserById(@PathVariable int id) {
        return transactionService.getTransactionById(id);
    }

    @DeleteMapping("/deleteTransaction/{id}")
    public Transaction deleteUser(@PathVariable int id) {
        return transactionService.deleteTransaction(id);
    }

}
