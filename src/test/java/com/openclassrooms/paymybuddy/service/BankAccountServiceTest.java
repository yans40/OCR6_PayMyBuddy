package com.openclassrooms.paymybuddy.service;

import com.openclassrooms.paymybuddy.entity.BankAccount;
import com.openclassrooms.paymybuddy.repository.BankAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BankAccountServiceTest {

    @Autowired
    BankAccountService bankAccountService;

    @Autowired
    BankAccountRepository bankAccountRepository;

    @BeforeEach
    void cleanDb(){

        this.bankAccountRepository.deleteAll();
    }
    @Test
    void saveBankAccountServiceTest(){
        BankAccount bankAcompte = new BankAccount("bnp","123456789");

        BankAccount bankAcompteSaved = bankAccountService.saveBankAccount(bankAcompte);

        BankAccount savedBankAcompte = bankAccountService.getBankAccountById(bankAcompteSaved.getAcompte_id());

        assertEquals("bnp",savedBankAcompte.getBankName());
    }

    @Test
    void updateBankAccountServiceTest(){
        //ARRANGE
        BankAccount bankAcompte = new BankAccount("banquepostale","4000 5000 1111111111 36");

        BankAccount updateBankAcompte = new BankAccount("boursorama","20000 24444 00000000000 54");

        BankAccount bankAcomptesaved = bankAccountService.saveBankAccount(bankAcompte);
        //ACT
        BankAccount bankAcompteUpdated = bankAccountService.updateBankAccount(updateBankAcompte,bankAcomptesaved.getAcompte_id());
        //ASSERT
        assertEquals("boursorama",bankAcompteUpdated.getBankName());
    }

    @Test
    void deleteBankAccountServiceTest(){
        //ARRANGE
        BankAccount bankAcompte = new BankAccount("banquepostale","4000 5000 1111111111 36");

        BankAccount bankAcomptesaved= bankAccountService.saveBankAccount(bankAcompte);

        //ACT
        bankAccountService.deleteBankAccountById(bankAcomptesaved.getAcompte_id());
        //ASSERT
        assertEquals(0, bankAccountRepository.findAll().size());
    }

    @Test

    void findAllBankAccountServiceTest(){

        BankAccount bankAccount1= new BankAccount("banquepostale","4000 5000 1111111111 36");

        BankAccount bankAccount2 = new BankAccount("boursorama","20000 24444 00000000000 54");

        bankAccountService.saveBankAccount(bankAccount1);
        bankAccountService.saveBankAccount(bankAccount2);

        bankAccountService.findAllBankAccounts();

        assertEquals(2, bankAccountRepository.findAll().size());


    }
}
