package com.openclassrooms.paymybuddy.repository;

import com.openclassrooms.paymybuddy.constants.TransfertType;
import com.openclassrooms.paymybuddy.entity.Transfert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static com.openclassrooms.paymybuddy.constants.TransfertType.CREDIT;
import static com.openclassrooms.paymybuddy.constants.TransfertType.DEBIT;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TransfertRepositoryTest {
//    @Autowired
//    private TransfertRepository transfertRepository;
//
//    @BeforeEach
//    void cleanDb() {
//        this.transfertRepository.deleteAll();
//    }
//
//    @Test
//    void saveMethod(){
//        Transfert transfert = new Transfert(450,"12548 02998 255","01/12/2022", CREDIT);
//
//        Transfert transfertSaved = transfertRepository.save(transfert);
//
//        assertEquals("12548 02998 255", transfertSaved.getIban());
//    }
//
//
//    @Test
//    void findTest(){
//        Transfert transfert = new Transfert(51,"12548 02998 255","15/11/2022", CREDIT);
//        Transfert transfertSaved = transfertRepository.save(transfert);
//
//        Optional<Transfert> transfertToFind =transfertRepository.findById(transfertSaved.getTransfert_id());
//
//
//        assertEquals(51,transfertToFind.get().getMontant());
//    }
//
//    @Test
//    void deleteTest(){
//        Transfert transfert = new Transfert(200,"12548 02998 255","15/11/2022", DEBIT);
//        Transfert transfertSaved = transfertRepository.save(transfert);
//
//        transfertRepository.delete(transfertSaved);
//
//        assertEquals(0,transfertRepository.findAll().size());
//
//    }
}
