package com.openclassrooms.paymybuddy.controller;

import com.openclassrooms.paymybuddy.entity.Transaction;
import com.openclassrooms.paymybuddy.entity.UserAccount;
import com.openclassrooms.paymybuddy.exceptions.InsufficientFundsException;
import com.openclassrooms.paymybuddy.exceptions.NotAContactException;
import com.openclassrooms.paymybuddy.service.TransactionService;
import com.openclassrooms.paymybuddy.service.UserAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserAccountService userAccountService;


    @PostMapping("/userAccount/{id}/transaction/add")
    public String addTransaction(@PathVariable int id, @ModelAttribute Transaction transaction, Model model, RedirectAttributes ra) {
        log.info("transaction sauvegardée");
        UserAccount user = userAccountService.getUserAccountById(id);
        transaction.setEmetteur(user);

        try {
            Transaction transactionSaved = transactionService.saveTransaction(transaction);
            model.addAttribute("transaction", transactionSaved);
            return "redirect:/userAccount/{id}";

        } catch (InsufficientFundsException e) {
            log.info("exception du solde");
            model.addAttribute("errorMessage", " Error: les fonds sont Insuffisants");
            return "error";
        } catch (NotAContactException e) {
            log.info("exception du contact");
            model.addAttribute("errorMessage", "il n'est pas dans vos contacts");//pas pertinent car le user est guidé directement par l'appli
            return "error";
        } catch (Exception e) {
            throw new RuntimeException(e);
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
