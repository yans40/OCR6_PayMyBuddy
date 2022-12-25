package com.openclassrooms.paymybuddy.controller;

import com.openclassrooms.paymybuddy.entity.Transaction;
import com.openclassrooms.paymybuddy.entity.UserAccount;
import com.openclassrooms.paymybuddy.service.TransactionService;
import com.openclassrooms.paymybuddy.service.UserAccountService;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Slf4j
@Controller
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserAccountService userAccountService;


    @PostMapping("/transaction/add")
    public String addTransaction(@ModelAttribute Transaction transaction, Model model) {
        log.info("transaction saving");
        UserAccount user=userAccountService.getUserAccountById(1);
        UserAccount userReceiver =userAccountService.getUserAccountById(2);
        transaction.setEmetteur(user);
        transaction.setBeneficiaire(userReceiver);
        transaction.setDescription("test Noel");
        try{
          Transaction transactionSaved= transactionService.saveTransaction(transaction);
            model.addAttribute("transaction",transactionSaved);
            return "success";

        }catch(EntityNotFoundException e){
            log.info("j'ai une erreur");
            model.addAttribute("errorMessage","Error: "+e.getMessage());
            return "error";
        }
    }


    @GetMapping("/transactions")
    public String getAllTransactions(Model model) {
        List<Transaction> transactionsList = transactionService.findAllTransactions();
        model.addAttribute("transactionsList", transactionsList);
        return "userAccount";
    }

    @GetMapping("/transaction/{id}")
    public Transaction getUserById(@PathVariable int id) {
        return transactionService.getTransactionById(id);
    }

    @DeleteMapping("/deleteTransaction/{id}")
    public Transaction deleteUser(@PathVariable int id) {
        return transactionService.deleteTransaction(id);
    }

}
