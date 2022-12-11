package com.openclassrooms.paymybuddy.service;

import com.openclassrooms.paymybuddy.entity.Transfert;
import com.openclassrooms.paymybuddy.entity.UserAccount;
import com.openclassrooms.paymybuddy.repository.TransfertRepository;
import com.openclassrooms.paymybuddy.repository.UserAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.openclassrooms.paymybuddy.constants.TransfertType.CREDIT;
import static com.openclassrooms.paymybuddy.constants.TransfertType.DEBIT;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class TransfertServiceTest {

    @Autowired
    UserAccountRepository userAccountRepository;

    @Autowired
    TransfertRepository transfertRepository;
    @Autowired
    UserAccountService userAccountService;


    @Autowired
    TransfertService transfertService;

    @BeforeEach
    void cleanDb() {
        this.userAccountRepository.deleteAll();
    }

    @Test
    void transfertServiceTest() {

        UserAccount userAccount = new UserAccount(600, "jean", "jean@mail.com", "motpasse");
        userAccountService.saveUserAccount(userAccount);
        Transfert transfert = new Transfert();
        transfert.setMontant(60);
        transfert.setTransfertType(CREDIT);
        transfert.setIban("11223344");
        transfert.setDate("09/12/2022");
        transfert.setUserAccount(userAccount);

        transfertService.saveTransfert(transfert);
        assertNotNull(transfert);
    }

    @Test
    void transfertServiceDebitTest() {

        UserAccount userAccount = new UserAccount(600, "jean", "jean@mail.com", "motpasse");
        userAccountService.saveUserAccount(userAccount);
        Transfert transfert = new Transfert();
        transfert.setMontant(60);
        transfert.setTransfertType(DEBIT);
        transfert.setIban("11223344");
        transfert.setDate("09/12/2022");
        transfert.setUserAccount(userAccount);

        Transfert savedTransfert = transfertService.saveTransfert(transfert);
        assertNotNull(savedTransfert);
    }

    @Test
    void transfertServiceDebitWithInsufficientBalanceTest() {

        UserAccount userAccount = new UserAccount(60, "jean", "jean@mail.com", "motpasse");
        userAccountService.saveUserAccount(userAccount);
        Transfert transfert = new Transfert();
        transfert.setMontant(100);
        transfert.setTransfertType(DEBIT);
        transfert.setIban("11223344");
        transfert.setDate("09/12/2022");
        transfert.setUserAccount(userAccount);

        Transfert savedTransfert = transfertService.saveTransfert(transfert);
        assertNull(savedTransfert);// transfert null car solde insuffisant
    }
}
