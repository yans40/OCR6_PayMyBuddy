package com.openclassrooms.paymybuddy.entity;

import com.openclassrooms.paymybuddy.constants.TransfertType;

import javax.persistence.*;

@Entity
@Table
public class Transfert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer transfert_id;
    @Column
    private String date;
    @Column
    private TransfertType transfertType;
    @Column
    private Integer montant;
    @Column
    private String Iban;

    @ManyToOne
    private UserAccount userAccount;

    public Transfert(String date, TransfertType transfertType, Integer montant, String iban, UserAccount userAccount) {
        this.date = date;
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

    public Transfert(Integer montant, String iban, String date, TransfertType transfertType) {

        this.montant = montant;
        this.Iban = iban;
        this.date = date;
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

    public Integer getMontant() {
        return montant;
    }

    public void setMontant(Integer montant) {
        this.montant = montant;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
