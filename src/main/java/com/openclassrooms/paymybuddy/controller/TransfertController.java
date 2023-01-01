package com.openclassrooms.paymybuddy.controller;

import com.openclassrooms.paymybuddy.entity.Transfert;
import com.openclassrooms.paymybuddy.entity.UserAccount;
import com.openclassrooms.paymybuddy.service.TransfertService;
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
public class TransfertController {

    @Autowired
    private TransfertService transfertService;

    @Autowired
    private UserAccountService userAccountService;

    @PostMapping("userAccount/{userId}/transfert/add")
    public String addTransfert(@PathVariable int userId, Transfert transfert, Model model, RedirectAttributes ra) {
        log.info("j'enregistre les attributs du transfert");
        UserAccount currentUser = userAccountService.getUserAccountById(userId);
        transfert.setUserAccount(currentUser);
        transfertService.saveTransfert(transfert);
        model.addAttribute("transfert", transfert);
        return "redirect:/userAccount/{userId}";
    }

    @GetMapping("userAccount/{userId}/transfert/new")
    public String showTransfertForm(@PathVariable int userId, Model model) {
        log.info("je recupère le formulaire de transfert");
        String message = "Register a new transfert!";
        Transfert transfert = new Transfert();
        UserAccount currentUser = userAccountService.getUserAccountById(userId);
        transfert.setUserAccount(currentUser);
        model.addAttribute("transfert", transfert);
        model.addAttribute("message", message);

        return "transfert";
    }

    @GetMapping("/transferts")
    public List<Transfert> getAllTransferts() {
        return transfertService.findAllTransfert();
    }

    @GetMapping("/transfert/{id}")
    public Transfert getTransfertById(@PathVariable int id) {
        return transfertService.getTransfertById(id);
    }

    @DeleteMapping("/transfert/{id}")
    public Transfert deleteTransfert(@PathVariable int id) {
        return transfertService.deleteTransfert(id);
    }
}
