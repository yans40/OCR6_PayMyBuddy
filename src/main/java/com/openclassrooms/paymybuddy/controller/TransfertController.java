package com.openclassrooms.paymybuddy.controller;

import com.openclassrooms.paymybuddy.entity.Transfert;
import com.openclassrooms.paymybuddy.service.TransfertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransfertController {

    @Autowired
    private TransfertService transfertService;

    @PostMapping("/addTransfert")
    public Transfert addTransfert(@RequestBody Transfert transfert) {
        return transfertService.saveTransfert(transfert);
    }

    @GetMapping("/transfert/{id}")
    public Transfert getTransfertById(@PathVariable int id) {
        return transfertService.getTransactionById(id);
    }

    @DeleteMapping("/deleteTransfert/{id}")
    public Transfert deleteTransfert(@PathVariable int id) {
        return transfertService.deleteTransfert(id);
    }
}
