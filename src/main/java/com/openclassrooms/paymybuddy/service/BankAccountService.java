package com.openclassrooms.paymybuddy.service;

import com.openclassrooms.paymybuddy.entity.BankAccount;
import com.openclassrooms.paymybuddy.repository.BankAccountRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    public BankAccount saveBankAccount(BankAccount bankAcompte) {

        return bankAccountRepository.save(bankAcompte);
    }

    public BankAccount getBankAccountById(int id) {
        return bankAccountRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public BankAccount deleteBankAccountById(int id){
        bankAccountRepository.deleteById(id);
        return null;
    }


    public BankAccount updateBankAccount(@NotNull BankAccount updateBankAcompte, int acompte_id) {
        BankAccount bankAcompteToUpdate = getBankAccountById(acompte_id);

        bankAcompteToUpdate.setBankName(updateBankAcompte.getBankName());
        bankAcompteToUpdate.setIban(updateBankAcompte.getIban());

        saveBankAccount(bankAcompteToUpdate);
        return bankAcompteToUpdate;
    }

    public List<BankAccount> findAllBankAccounts() {

        return bankAccountRepository.findAll();
    }
}
