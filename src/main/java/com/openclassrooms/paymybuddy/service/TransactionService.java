package com.openclassrooms.paymybuddy.service;

import com.openclassrooms.paymybuddy.entity.Transaction;
import com.openclassrooms.paymybuddy.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;


    public Transaction saveTransaction(Transaction transaction){

        return transactionRepository.save(transaction);
    }

    public Transaction getTransactionById(int id){

        return transactionRepository.findById(id).get();
    }

    public Transaction deleteTransaction(int id){
        transactionRepository.deleteById(id);
        return null;
    }
}
