package com.openclassrooms.paymybuddy.controller;

import com.openclassrooms.paymybuddy.entity.Transfert;
import com.openclassrooms.paymybuddy.entity.UserAccount;
import com.openclassrooms.paymybuddy.service.TransfertService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
public class TransfertController {

    @Autowired
    private TransfertService transfertService;

    @PostMapping("/transfert/add")
    public String addTransfert(@RequestBody Transfert transfert, Model model) {
        transfertService.saveTransfert(transfert);
        model.addAttribute("transfert", new Transfert());
        return "redirect:/userAcconut";
    }

    @GetMapping("/transfert/new")
    public String showTransfertForm(Model model) {
        log.info("get the transfert form");
        String message = "Register a new transfert!";
        model.addAttribute("transfert",new Transfert());
        model.addAttribute("message",message);
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
