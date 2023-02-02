package com.openclassrooms.paymybuddy.entity;

import com.openclassrooms.paymybuddy.constants.TransfertType;

import javax.persistence.*;

@Entity
@Table
public class Transfert {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private Integer transfert_id;
    @Column(nullable = false)
    private TransfertType transfertType;
    @Column(nullable = false)
    private double montant;
    @Column(nullable = false)
    private String Iban;

    @ManyToOne
    private UserAccount userAccount;

    public Transfert(TransfertType transfertType, double montant, String iban, UserAccount userAccount) {

        this.transfertType = transfertType;
        this.montant = montant;
        this.Iban = iban;
        this.userAccount = userAccount;
    }

    public TransfertType getTransfertType() {
        return transfertType;
    }

    public void setTransfertType(TransfertType transfertType) {
        this.transfertType = transfertType;
    }

    public Transfert() {

    }

    public Transfert(double montant, String iban, TransfertType transfertType) {

        this.montant = montant;
        this.Iban = iban;
        this.transfertType = transfertType;
    }


    public String getIban() {
        return Iban;
    }

    public void setIban(String iban) {
        Iban = iban;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public Integer getTransfert_id() {
        return transfert_id;
    }

    public void setTransfert_id(Integer transfert_id) {
        this.transfert_id = transfert_id;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

}
