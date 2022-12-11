package com.openclassrooms.paymybuddy.service;

import com.openclassrooms.paymybuddy.entity.Transfert;
import com.openclassrooms.paymybuddy.entity.UserAccount;
import com.openclassrooms.paymybuddy.repository.TransfertRepository;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

import static com.openclassrooms.paymybuddy.constants.TransfertType.CREDIT;
import static com.openclassrooms.paymybuddy.constants.TransfertType.DEBIT;

@Slf4j
@Service
public class TransfertService {

    @Autowired
    private TransfertRepository transfertRepository;

    @Autowired
    private UserAccountService userAccountService;

    public Transfert saveTransfert(@NotNull Transfert transfert) {
        int userId = transfert.getUserAccount().getUserAccount_id();// on récupère le userId puis...
        UserAccount user = transfert.getUserAccount();
        double currentUserSolde = userAccountService.getUserAccountById(user.getUserAccount_id()).getSolde();//on recherche son solde actuel dans la table user



        if (transfert.getTransfertType().equals(CREDIT)) {// on teste le cas CREDIT
            double nouveauSolde = currentUserSolde + transfert.getMontant();//on ajoute le montant du transfert sur le solde actuel
            log.info("transfert reçu!");
            System.out.println(nouveauSolde);

            user.setSolde(nouveauSolde);//on set le solde dans le solde du user...
            userAccountService.updateUserAccountBalanceAfterTransfertOrTransaction(user);// on le persiste en appelant la methode update userAccountService

            System.out.println(user.getSolde());
            return transfertRepository.save(transfert);

        } else if (transfert.getTransfertType().equals(DEBIT)) {//on teste le cas DEBIT
            if (currentUserSolde >= transfert.getMontant()) {// dans le cas spécifique du DEBIT on vérifie si le solde nous permet de faire le débit
                double nouveauSolde = currentUserSolde - transfert.getMontant();
                log.info("débit effectué !");
                System.out.println(nouveauSolde);

                user.setSolde(nouveauSolde);
                userAccountService.updateUserAccountBalanceAfterTransfertOrTransaction(user);
                System.out.println(user.getSolde());
                transfertRepository.save(transfert);
            } else {
                log.info("vérifié le solde de votre compte!");
                return null;
            }

        }

        return transfert;
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
        transfertToUpdate.setMontant(updatedTransfert.getMontant());
        transfertToUpdate.setDate(updatedTransfert.getDate());
        transfertToUpdate.setIban(updatedTransfert.getIban());
        transfertToUpdate.setTransfertType(updatedTransfert.getTransfertType());

        return saveTransfert(transfertToUpdate);
    }

    public List<Transfert> findAllTransfert() {

        return transfertRepository.findAll();
    }
}
