package com.openclassrooms.paymybuddy.service;

import com.openclassrooms.paymybuddy.entity.Transfert;
import com.openclassrooms.paymybuddy.repository.TransfertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransfertService {

    @Autowired
    private TransfertRepository transfertRepository;

    public Transfert saveTransfert(Transfert transfert) {
        return transfertRepository.save(transfert);
    }

    public Transfert getTransactionById(int id) {

        return transfertRepository.findById(id).get();
    }

    public Transfert deleteTransfert(int id) {
        transfertRepository.deleteById(id);
        return null;
    }
}
