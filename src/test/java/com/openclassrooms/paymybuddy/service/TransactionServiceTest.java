package com.openclassrooms.paymybuddy.service;

import com.openclassrooms.paymybuddy.entity.Transaction;
import com.openclassrooms.paymybuddy.entity.UserAccount;
import com.openclassrooms.paymybuddy.exceptions.MailAlreadyExistException;
import com.openclassrooms.paymybuddy.repository.TransactionRepository;
import com.openclassrooms.paymybuddy.repository.UserAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TransactionServiceTest {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    UserAccountRepository userAccountRepository;

    @Autowired
    TransactionService transactionService;

    @Autowired
    UserAccountService userAccountService;

    @BeforeEach
    void cleanDb() {
        this.transactionRepository.deleteAll();
        this.userAccountRepository.deleteAll();
    }


    @Test
    @Transactional
    void saveTransactionServiceTestInsufficientBalance() throws Exception {

        UserAccount userAccountEmetteur = new UserAccount(500L, "jean", "test1@mail.com", "test1");
        UserAccount userAccountBeneficiaire = new UserAccount(600L, "virginie", "test2@mail.com", "test2");

        userAccountService.saveUserAccount(userAccountEmetteur);
        userAccountService.saveUserAccount(userAccountBeneficiaire);

        Transaction transaction = new Transaction("resto de fin d'année", 2000, "04/12/2022", userAccountEmetteur, userAccountBeneficiaire);

        Transaction savedTransaction = transactionService.saveTransaction(transaction);

        assertNull(savedTransaction);
    }

    @Test
    @Transactional
    void saveTransactionServiceTestSufficientBalance() throws Exception {

        UserAccount userAccountEmetteur = new UserAccount(5000L, "jean", "test1@mail.com", "test1");
        UserAccount userAccountBeneficiaire = new UserAccount(600L, "virginie", "test2@mail.com", "test2");

        userAccountService.saveUserAccount(userAccountEmetteur);
        userAccountService.saveUserAccount(userAccountBeneficiaire);

        List<UserAccount> emetteurContactList = userAccountEmetteur.getContacts();
        emetteurContactList.add(userAccountBeneficiaire);

        Transaction transaction = new Transaction("resto de fin d'année", 2000, "04/12/2022", userAccountEmetteur, userAccountBeneficiaire);

        Transaction savedTransaction = transactionService.saveTransaction(transaction);

        assertNotNull(savedTransaction);
    }

    @Test
    @Transactional
    void saveTransactionServiceTestSufficient() throws Exception {


        UserAccount userAccountEmetteur = new UserAccount(5000L, "jean", "test1@mail.com", "test1");
        UserAccount userAccountBeneficiaire = new UserAccount(600L, "virginie", "test2@mail.com", "test2");

        userAccountService.saveUserAccount(userAccountEmetteur);
        userAccountService.saveUserAccount(userAccountBeneficiaire);

        List<UserAccount> emetteurContactList = userAccountEmetteur.getContacts();
        emetteurContactList.add(userAccountBeneficiaire);

        Transaction transaction = new Transaction("resto", 150, "12/12/2022", userAccountEmetteur, userAccountBeneficiaire);

        Transaction savedTransaction = transactionService.saveTransaction(transaction);

        assertNotNull(savedTransaction);

    }

    @Test
    @Transactional
    void saveTransactionServiceWithUserInContactList() throws Exception {
        //ARRANGE
        UserAccount userAccountEmetteur = new UserAccount(5000L, "jean", "test1@mail.com", "test1");
        UserAccount userAccountBeneficiaire = new UserAccount(600L, "virginie", "test2@mail.com", "test2");

        UserAccount emetteurInDb = userAccountService.saveUserAccount(userAccountEmetteur);
        UserAccount beneficiaireInDb = userAccountService.saveUserAccount(userAccountBeneficiaire);

        List<UserAccount> emetteurContactList = emetteurInDb.getContacts();
        emetteurContactList.add(beneficiaireInDb);

        Transaction transaction = new Transaction("resto de fin d'année", 2500, "04/12/2022", userAccountEmetteur, userAccountBeneficiaire);

        Transaction savedTransaction = transactionService.saveTransaction(transaction);

        assertNotNull(savedTransaction);
        assertEquals(2500, transaction.getMontant());

    }

    @Test
    @Transactional
    void saveTransactionServiceWithUserOutContactList() throws Exception {

        //ARRANGE
        UserAccount userAccountEmetteur = new UserAccount(5000L, "jean", "test1@mail.com", "test1");
        UserAccount userAccountBeneficiaire = new UserAccount(600L, "virginie", "test2@mail.com", "test2");

        userAccountService.saveUserAccount(userAccountEmetteur);
        userAccountService.saveUserAccount(userAccountBeneficiaire);

        Transaction transaction = new Transaction("resto de fin d'année", 2500, "04/12/2022", userAccountEmetteur, userAccountBeneficiaire);

        Transaction savedTransaction = transactionService.saveTransaction(transaction);
        assertNull(savedTransaction);
    }


//    @Test
//    void deleteTransactionServiceTest() {
//
//        UserAccount userAccountEmetteur = new UserAccount(5000L, "jean", "test1@mail.com", "test1");
//        UserAccount userAccountBeneficiaire = new UserAccount(600L, "virginie", "test2@mail.com", "test2");
//
//        userAccountService.saveUserAccount(userAccountEmetteur);
//        userAccountService.saveUserAccount(userAccountBeneficiaire);
//
//        Transaction transaction = new Transaction("course anniversaire", 2000, "04/12/2022", userAccountEmetteur, userAccountBeneficiaire);
//
//        Transaction savedTransaction = transactionService.saveTransaction(transaction);
//
//        transactionService.deleteTransaction(savedTransaction.getTransaction_Id());
//
//        assertEquals(0, transactionRepository.findAll().size());
//    }
//
//    @Test
//    void findAllTransactionServiceTest() {
//        UserAccount userAccountEmetteur = new UserAccount(5000L, "jean", "test1@mail.com", "test1");
//        UserAccount userAccountBeneficiaire = new UserAccount(600L, "virginie", "test2@mail.com", "test2");
//
//        userAccountService.saveUserAccount(userAccountEmetteur);
//        userAccountService.saveUserAccount(userAccountBeneficiaire);
//
//        Transaction transaction = new Transaction("resto de fin d'année", 2000, "04/12/2022", userAccountEmetteur, userAccountBeneficiaire);
//
//        Transaction savedTransaction = transactionService.saveTransaction(transaction);
//
//        transactionService.saveTransaction(savedTransaction);
//
//        transactionService.findAllTransactions();
//
//        assertEquals(1, transactionRepository.findAll().size());
//    }
}
