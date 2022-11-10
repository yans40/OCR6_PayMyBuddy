package com.openclassrooms.paymybuddy.controller;

import com.openclassrooms.paymybuddy.entity.BankAcompte;
import com.openclassrooms.paymybuddy.service.BankAcompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BankAcompteController {
    @Autowired
    private BankAcompteService bankAcompteService;

    @PostMapping("/saveBankAcompte")
    public BankAcompte saveBankAcompte(@RequestBody BankAcompte bankAcompte){
        return bankAcompteService.saveBankAcompte(bankAcompte);
    }

    @GetMapping("/bankAcompte/{id}")
    public BankAcompte getBankAcompteById(@PathVariable int id) {
        return bankAcompteService.getBankAcompteById(id);
    }

    @DeleteMapping("/deleteBankAcompte/{id}")
    public BankAcompte deleteBankAcompte(@PathVariable int id) {
        return bankAcompteService.deleteBankAcompteById(id);
    }
}
