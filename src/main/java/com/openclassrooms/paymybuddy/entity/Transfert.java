package com.openclassrooms.paymybuddy.entity;

import javax.persistence.*;

@Entity
@Table
public class Transfert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer transfert_id;
    @Column
    private String description;
    @Column
    private Integer montant;
    @Column
    private String Iban;
    @Column
    private String date;
    @ManyToOne
    private UserAccount userAccount;

    public Transfert() {

    }

    public Transfert( String description, Integer montant, String iban, String date) {
        this.description = description;
        this.montant = montant;
        Iban = iban;
        this.date = date;
    }

    public Transfert(String description, Integer montant, String date) {
        this.description = description;
        this.montant = montant;
        this.date = date;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
