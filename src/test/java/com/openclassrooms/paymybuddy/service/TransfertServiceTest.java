package com.openclassrooms.paymybuddy.service;

import com.openclassrooms.paymybuddy.entity.Transfert;
import com.openclassrooms.paymybuddy.repository.TransfertRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TransfertServiceTest {

    @Autowired
    private TransfertService transfertService;

    @Autowired
    private TransfertRepository transfertRepository;

    @BeforeEach
    void cleanDb() {
        this.transfertRepository.deleteAll();
    }

    @Test
    void saveTransfertServiceTest() {
        //ARRANGE
        Transfert transfertToSave = new Transfert("paiement resto", 30, "30/12/2022");

        //ACT
        Transfert transfertsaved = transfertService.saveTransfert(transfertToSave);

        Transfert savedTransfert = transfertService.getTransfertById(transfertsaved.getTransfert_id());
        //ASSERT
        assertEquals("paiement resto", savedTransfert.getDescription());
    }

    @Test
    void updateTransfertServiceTest() {

        //ARRANGE
        Transfert transfert = new Transfert("cadeau d'anniversaire Lucie", 15, "2022/04/10");

        Transfert updatedTransfert = new Transfert("cadeau anniversaire Lucie et Ludivine", 30, "2022/04/10");

        Transfert transfertSaved = transfertService.saveTransfert(transfert);
        //ACT
        Transfert transfertUpdated = transfertService.updateTransfert(updatedTransfert, transfertSaved.getTransfert_id());
        //ASSERT
        assertEquals("cadeau anniversaire Lucie et Ludivine", transfertUpdated.getDescription());
    }

    @Test
    void deleteTransfertServiceTest() {

        //ARRANGE
        Transfert transfert = new Transfert("cadeau d'anniversaire Lucie", 15, "2022/04/10");

        Transfert transfertSaved = transfertService.saveTransfert(transfert);

        //ACT
        transfertService.deleteTransfert(transfertSaved.getTransfert_id());
        //ASSERT
        assertEquals(0, transfertRepository.findAll().size());
    }

    @Test
    void findAllTransfertServiceTest() {

        Transfert transfert1 = new Transfert("cadeau d'anniversaire Lucie", 15, "2022/04/10");

        Transfert transfert2 = new Transfert("cadeau anniversaire celine", 30, "2022/04/10");

        transfertService.saveTransfert(transfert1);
        transfertService.saveTransfert(transfert2);

        transfertService.findAllTransfert();

        assertEquals(2, transfertRepository.findAll().size());

    }

}

