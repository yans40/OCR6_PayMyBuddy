package com.openclassrooms.paymybuddy.service;

import com.openclassrooms.paymybuddy.entity.Transaction;
import com.openclassrooms.paymybuddy.entity.UserAccount;
import com.openclassrooms.paymybuddy.repository.TransactionRepository;
import com.openclassrooms.paymybuddy.repository.UserAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class transactionServiceTest {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    UserAccountRepository userAccountRepository;

    @Autowired
    TransactionService transactionService;

    @Autowired
    UserAccountService userAccountService;//TODO mocker pour éviter la dépendance de transactionService à userAccountService

    @BeforeEach
    void cleanDb() {
        this.transactionRepository.deleteAll();
        this.userAccountRepository.deleteAll();
    }


    @Test
    void saveTransactionServiceTestInsufficientBalance() {
        //ARRANGE

        UserAccount userAccountEmetteur = new UserAccount(500L, "jean", "test1@mail.com", "test1");
        UserAccount userAccountBeneficiaire = new UserAccount(600L, "virginie", "test2@mail.com", "test2");

        userAccountService.saveUserAccount(userAccountEmetteur);
        userAccountService.saveUserAccount(userAccountBeneficiaire);

        Transaction transaction = new Transaction("resto de fin d'année", 2000, "04/12/2022", 2, userAccountEmetteur, userAccountBeneficiaire);

        Transaction savedTransaction = transactionService.saveTransaction(transaction);

        assertNull(savedTransaction);
    }


    @Test
    void saveTransactionServiceTestSufficientBalance() {
        //ARRANGE

        UserAccount userAccountEmetteur = new UserAccount(5000L, "jean", "test1@mail.com", "test1");
        UserAccount userAccountBeneficiaire = new UserAccount(600L, "virginie", "test2@mail.com", "test2");

        userAccountService.saveUserAccount(userAccountEmetteur);
        userAccountService.saveUserAccount(userAccountBeneficiaire);

        Transaction transaction = new Transaction("resto de fin d'année", 2000, "04/12/2022", 2, userAccountEmetteur, userAccountBeneficiaire);

        Transaction savedTransaction = transactionService.saveTransaction(transaction);

        assertNotNull(savedTransaction);
        assertEquals("resto de fin d'année", savedTransaction.getDescription());
        assertEquals(2600,userAccountBeneficiaire.getSolde());
    }


    @Test
    void deleteTransactionServiceTest() {

        UserAccount userAccountEmetteur = new UserAccount(5000L, "jean", "test1@mail.com", "test1");
        UserAccount userAccountBeneficiaire = new UserAccount(600L, "virginie", "test2@mail.com", "test2");

        userAccountService.saveUserAccount(userAccountEmetteur);
        userAccountService.saveUserAccount(userAccountBeneficiaire);

        Transaction transaction = new Transaction("course anniversaire", 2000, "04/12/2022", 2, userAccountEmetteur, userAccountBeneficiaire);

        Transaction savedTransaction = transactionService.saveTransaction(transaction);

        transactionService.deleteTransaction(savedTransaction.getTransaction_Id());

        assertEquals(0, transactionRepository.findAll().size());
    }

    @Test
    void findAllTransactionServiceTest() {
        UserAccount userAccountEmetteur = new UserAccount(5000L, "jean", "test1@mail.com", "test1");
        UserAccount userAccountBeneficiaire = new UserAccount(600L, "virginie", "test2@mail.com", "test2");

        userAccountService.saveUserAccount(userAccountEmetteur);
        userAccountService.saveUserAccount(userAccountBeneficiaire);

        Transaction transaction = new Transaction("resto de fin d'année", 2000, "04/12/2022", 2, userAccountEmetteur, userAccountBeneficiaire);

        Transaction savedTransaction = transactionService.saveTransaction(transaction);

        transactionService.saveTransaction(savedTransaction);

        transactionService.findAllTransactions();

        assertEquals(1, transactionRepository.findAll().size());
    }
}
