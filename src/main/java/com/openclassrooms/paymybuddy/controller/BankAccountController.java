package com.openclassrooms.paymybuddy.controller;

import com.openclassrooms.paymybuddy.entity.BankAccount;
import com.openclassrooms.paymybuddy.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BankAccountController {
    @Autowired
    private BankAccountService bankAccountService;

    @PostMapping("/saveBankAccount")
    public BankAccount saveBankAccount(@RequestBody BankAccount bankAccount) {
        return bankAccountService.saveBankAccount(bankAccount);
    }

    @GetMapping("bankAccounts")
    public List<BankAccount> getAllBankAccounts() {
        return bankAccountService.findAllBankAccounts();
    }

    @GetMapping("/bankAccount/{id}")
    public BankAccount getBankAccountById(@PathVariable int id) {
        return bankAccountService.getBankAccountById(id);
    }

    @DeleteMapping("/deleteBankAccount/{id}")
    public BankAccount deleteBankAccount(@PathVariable int id) {
        return bankAccountService.deleteBankAccountById(id);
    }
}
