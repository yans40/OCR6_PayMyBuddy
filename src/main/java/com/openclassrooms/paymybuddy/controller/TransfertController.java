package com.openclassrooms.paymybuddy.controller;

import com.openclassrooms.paymybuddy.entity.Transfert;
import com.openclassrooms.paymybuddy.service.TransfertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransfertController {

    @Autowired
    private TransfertService transfertService;

    @PostMapping("/transfert")
    public Transfert addTransfert(@RequestBody Transfert transfert) {
        return transfertService.saveTransfert(transfert);
    }
    @GetMapping ("/transferts")
    public List<Transfert> getAllTransferts(){
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
