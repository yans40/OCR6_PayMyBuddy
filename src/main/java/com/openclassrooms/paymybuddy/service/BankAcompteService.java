package com.openclassrooms.paymybuddy.service;

import com.openclassrooms.paymybuddy.entity.BankAcompte;
import com.openclassrooms.paymybuddy.repository.BankAcompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class BankAcompteService {

    @Autowired
    private BankAcompteRepository bankAcompteRepository;
    public BankAcompte addBankAcompte(BankAcompte bankAcompte) {
        return bankAcompteRepository.save(bankAcompte);
    }

    public BankAcompte getBankAcompteById(int id) {
        return bankAcompteRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public BankAcompte deleteBankAcompteById(int id){
        bankAcompteRepository.deleteById(id);
        return null;
    }
}
