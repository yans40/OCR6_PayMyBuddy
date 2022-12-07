package com.openclassrooms.paymybuddy.service;

import com.openclassrooms.paymybuddy.entity.Transfert;
import com.openclassrooms.paymybuddy.entity.UserAccount;
import com.openclassrooms.paymybuddy.repository.TransfertRepository;
import com.openclassrooms.paymybuddy.repository.UserAccountRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.openclassrooms.paymybuddy.constants.TransfertType.CREDIT;
import static com.openclassrooms.paymybuddy.constants.TransfertType.DEBIT;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TransfertServiceTest {

    @Autowired
    private TransfertService transfertService;

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private TransfertRepository transfertRepository;
    @Autowired
    private UserAccountRepository userAccountRepository;

    @BeforeEach
    void cleanDb() {
        this.transfertRepository.deleteAll();
        this.userAccountRepository.deleteAll();
    }

    @Test
    void saveTransfertServiceTestCredit() {
        //ARRANGE
        UserAccount user = new UserAccount(6000, "celine", "test6@mail.com", "test2");

        UserAccount userSaved =userAccountService.saveUserAccount(user);

        Transfert transfert = new Transfert("07/12/2022",CREDIT,600,"12548 02998 000022",userSaved);

        Transfert transfertSaved = transfertService.saveTransfert(transfert);

        assertNotNull(transfertSaved);

    }

    @Test
    void saveTransfertServiceTestDebitInsufficientBalance() {
        //ARRANGE
        UserAccount user = new UserAccount(100, "valerie", "test7@mail.com", "test7");
        UserAccount userSaved =userAccountService.saveUserAccount(user);

        Transfert transfert = new Transfert("07/12/2022",DEBIT,600,"12548 02998 000022",userSaved);

        Transfert transfertSaved = transfertService.saveTransfert(transfert);

        assertNull(transfertSaved);

    }

    @Test
    void saveTransfertServiceTestDebitsufficientBalance() {
        //ARRANGE
        UserAccount user = new UserAccount(1000, "valerie", "test7@mail.com", "test7");
        UserAccount userSaved =userAccountService.saveUserAccount(user);

        Transfert transfert = new Transfert("07/12/2022",DEBIT,600,"12548 02998 000022",userSaved);

        Transfert transfertSaved = transfertService.saveTransfert(transfert);

        assertNotNull(transfertSaved);
    }



//    @Test
//    void updateTransfertServiceTest() {
//
//        //ARRANGE
//        Transfert transfert = new Transfert(15, "12548 02998 000022", "2022/04/10",CREDIT);
//
//        Transfert updatedTransfert = new Transfert(15, "12548 02998 000023", "2022/04/10",CREDIT);
//
//        Transfert transfertSaved = transfertService.saveTransfert(transfert);
//        //ACT
//        Transfert transfertUpdated = transfertService.updateTransfert(updatedTransfert, transfertSaved.getTransfert_id());
//        //ASSERT
//        assertEquals("12548 02998 000023", transfertUpdated.getIban());
//    }
//
//    @Test
//    void deleteTransfertServiceTest() {
//
//        //ARRANGE
//        Transfert transfert = new Transfert(15, "12548 02998 000022", "2022/04/10",CREDIT);
//
//        Transfert transfertSaved = transfertService.saveTransfert(transfert);
//
//        //ACT
//        transfertService.deleteTransfert(transfertSaved.getTransfert_id());
//        //ASSERT
//        assertEquals(0, transfertRepository.findAll().size());
//    }
//
//    @Test
//    void findAllTransfertServiceTest() {
//
//        Transfert transfert1 = new Transfert(15, "12548 02998 000022", "2022/04/10",CREDIT);
//
//        Transfert transfert2 = new Transfert(15, "12548 02998 000022", "2022/04/10",CREDIT);
//
//        transfertService.saveTransfert(transfert1);
//        transfertService.saveTransfert(transfert2);
//
//        transfertService.findAllTransfert();
//
//        assertEquals(2, transfertRepository.findAll().size());
//
//    }

}

