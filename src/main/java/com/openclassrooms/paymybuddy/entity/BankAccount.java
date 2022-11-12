package com.openclassrooms.paymybuddy.entity;

import javax.persistence.*;

@Entity
@Table(name = "bank_account")
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int acompte_id;

    @Column
    private String bankName;

    @Column
    private String iban;

    public BankAccount(String bankName, String iban) {
        this.bankName = bankName;
        this.iban = iban;
    }

    public BankAccount() {

    }

    public int getAcompte_id() {
        return acompte_id;
    }

    public void setAcompte_id(Integer acompte_id) {
        this.acompte_id = acompte_id;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }
}
