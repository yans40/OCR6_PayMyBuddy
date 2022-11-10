package com.openclassrooms.paymybuddy.service;

import com.openclassrooms.paymybuddy.entity.Transaction;
import com.openclassrooms.paymybuddy.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;


    public Transaction saveTransaction(Transaction transaction){

        return transactionRepository.save(transaction);
    }

    public Transaction getTransactionById(int id){
        return transactionRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public Transaction deleteTransaction(int id){
        transactionRepository.deleteById(id);
        return null;
    }

    public Transaction updateTransaction(Transaction updateTransaction, int id) {
        Transaction transactionToUpdate = getTransactionById(id);

        transactionToUpdate.setDate(updateTransaction.getDate());
        transactionToUpdate.setMontant(updateTransaction.getMontant());
        transactionToUpdate.setFrais(updateTransaction.getFrais());

        Transaction transactionUpdated = saveTransaction(transactionToUpdate);

        return transactionUpdated;
    }

    public List<Transaction> findAllTransactions() {

        return transactionRepository.findAll();
    }
}
