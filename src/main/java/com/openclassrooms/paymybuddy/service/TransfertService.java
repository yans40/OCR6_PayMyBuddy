package com.openclassrooms.paymybuddy.service;

import com.openclassrooms.paymybuddy.entity.Transfert;
import com.openclassrooms.paymybuddy.repository.TransfertRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class TransfertService {

    @Autowired
    private TransfertRepository transfertRepository;

    public Transfert saveTransfert(Transfert transfert) {
        return transfertRepository.save(transfert);
    }

    public Transfert getTransfertById(int id) {

        return transfertRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public Transfert deleteTransfert(int id) {
        transfertRepository.deleteById(id);
        return null;
    }

    public Transfert updateTransfert(@NotNull Transfert updatedTransfert, Integer id) {

        Transfert transfertToUpdate = getTransfertById(id);

        transfertToUpdate.setDescription(updatedTransfert.getDescription());
        transfertToUpdate.setMontant(updatedTransfert.getMontant());
        transfertToUpdate.setDate(updatedTransfert.getDate());

        Transfert transfertUpdated= saveTransfert(transfertToUpdate);
        return transfertUpdated;
    }
}
