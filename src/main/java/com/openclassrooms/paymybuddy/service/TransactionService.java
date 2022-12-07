package com.openclassrooms.paymybuddy.service;

import com.openclassrooms.paymybuddy.entity.Transaction;
import com.openclassrooms.paymybuddy.entity.UserAccount;
import com.openclassrooms.paymybuddy.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

import static org.springframework.util.ClassUtils.isPresent;
@Slf4j
@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;


    public Transaction saveTransaction(@NotNull Transaction transaction) {

        double soldeEmetteurAVerifier = transaction.getEmetteur().getSolde();
        double fraisDeTransaction = calculFraisDeTransaction(transaction);
        double montantTransactionTtc = transaction.getMontant() + fraisDeTransaction;
        transaction.setFrais(fraisDeTransaction);

        if (soldeEmetteurAVerifier < montantTransactionTtc || !isContact(transaction.getEmetteur(), transaction.getBeneficiaire())) {
            log.info("les conditions ne sont pas réunies pour réaliser la transaction");
            return null;
        } else {
            soldeEmetteurAVerifier -= montantTransactionTtc;
            log.info("transaction effectuée vers le compte bénéficiaire");
            System.out.println("une transaction de: " + transaction.getMontant() + " a été réalisée vers " + transaction.getBeneficiaire().getName() + " et les frais sont de: " + fraisDeTransaction + " €");
            System.out.println("le nouveau solde du compte est: " + soldeEmetteurAVerifier + " €");
            return transactionRepository.save(transaction);
        }

    }

    public Transaction getTransactionById(int id) {
        return transactionRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public Transaction deleteTransaction(int id) {
        transactionRepository.deleteById(id);
        return null;
    }

    public Transaction updateTransaction(@NotNull Transaction updateTransaction, int id) {
        Transaction transactionToUpdate = getTransactionById(id);

        transactionToUpdate.setDate(updateTransaction.getDate());
        transactionToUpdate.setMontant(updateTransaction.getMontant());
        transactionToUpdate.setFrais((int) updateTransaction.getFrais());

        return saveTransaction(transactionToUpdate);
    }

    public List<Transaction> findAllTransactions() {
        return transactionRepository.findAll();
    }

    public double calculFraisDeTransaction(@NotNull Transaction transaction) {
        return transaction.getMontant() * 0.05;
    }

    boolean isContact(@NotNull UserAccount emetteur, UserAccount beneficiaire) {
        List<UserAccount> contacts = emetteur.getContacts();
        boolean result = false;
        for (UserAccount contact : contacts) {
            if (contact.geteMail().equals(beneficiaire.geteMail())) {
                return true;
            }
        }
        return result;
    }
}
