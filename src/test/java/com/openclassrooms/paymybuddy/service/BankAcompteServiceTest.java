package com.openclassrooms.paymybuddy.service;

import com.openclassrooms.paymybuddy.entity.BankAcompte;
import com.openclassrooms.paymybuddy.repository.BankAcompteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BankAcompteServiceTest {

    @Autowired
    BankAcompteService bankAcompteService;

    @Autowired
    BankAcompteRepository bankAcompteRepository;

    @BeforeEach
    void cleanDb(){

        this.bankAcompteRepository.deleteAll();
    }
    @Test
    void saveBankAcompteServiceTest(){
        BankAcompte bankAcompte = new BankAcompte("bnp","123456789");

        BankAcompte bankAcompteSaved = bankAcompteService.saveBankAcompte(bankAcompte);

        BankAcompte savedBankAcompte = bankAcompteService.getBankAcompteById(bankAcompteSaved.getAcompte_id());

        assertEquals("bnp",savedBankAcompte.getBankName());
    }

    @Test
    void updateBankAcompteServiceTest(){
        //ARRANGE
        BankAcompte bankAcompte = new BankAcompte("banquepostale","4000 5000 1111111111 36");

        BankAcompte updateBankAcompte = new BankAcompte("boursorama","20000 24444 00000000000 54");

        BankAcompte bankAcomptesaved = bankAcompteService.saveBankAcompte(bankAcompte);
        //ACT
        BankAcompte bankAcompteUpdated = bankAcompteService.updateBankAcompte(updateBankAcompte,bankAcomptesaved.getAcompte_id());
        //ASSERT
        assertEquals("boursorama",bankAcompteUpdated.getBankName());
    }

    @Test
    void deleteBankAcompteServiceTest(){
        //ARRANGE
        BankAcompte bankAcompte = new BankAcompte("banquepostale","4000 5000 1111111111 36");

        BankAcompte bankAcomptesaved= bankAcompteService.saveBankAcompte(bankAcompte);

        //ACT
        bankAcompteService.deleteBankAcompteById(bankAcomptesaved.getAcompte_id());
        //ASSERT
        assertEquals(0,bankAcompteRepository.findAll().size());
    }

    @Test

    void findAllBankAcompteServiceTest(){

        BankAcompte bankAcompte1= new BankAcompte("banquepostale","4000 5000 1111111111 36");

        BankAcompte bankAcompte2 = new BankAcompte("boursorama","20000 24444 00000000000 54");

        bankAcompteService.saveBankAcompte(bankAcompte1);
        bankAcompteService.saveBankAcompte(bankAcompte2);

        bankAcompteService.findAllBankAcomptes();

        assertEquals(2,bankAcompteRepository.findAll().size());


    }
}
