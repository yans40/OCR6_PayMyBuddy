package com.openclassrooms.paymybuddy.service;

import com.openclassrooms.paymybuddy.entity.BankAcompte;
import com.openclassrooms.paymybuddy.repository.BankAcompteRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BankAcompteService {

    @Autowired
    private BankAcompteRepository bankAcompteRepository;

    public BankAcompte saveBankAcompte(BankAcompte bankAcompte) {

        return bankAcompteRepository.save(bankAcompte);
    }

    public BankAcompte getBankAcompteById(int id) {
        return bankAcompteRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public BankAcompte deleteBankAcompteById(int id){
        bankAcompteRepository.deleteById(id);
        return null;
    }


    public BankAcompte updateBankAcompte(@NotNull BankAcompte updateBankAcompte, int acompte_id) {
        BankAcompte bankAcompteToUpdate = getBankAcompteById(acompte_id);

        bankAcompteToUpdate.setBankName(updateBankAcompte.getBankName());
        bankAcompteToUpdate.setIban(updateBankAcompte.getIban());

        saveBankAcompte(bankAcompteToUpdate);
        return bankAcompteToUpdate;
    }

    public List<BankAcompte> findAllBankAcomptes() {

        return bankAcompteRepository.findAll();
    }
}
