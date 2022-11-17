package com.openclassrooms.paymybuddy.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "user_account")
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int userAccount_id;
    @Column
    private Long solde;
    @Column
    private String name;
    @Column
    private String eMail;
    @Column
    private String password;
    @OneToMany(mappedBy = "emetteur")
    List<Transaction> transactionsEmises = new ArrayList<>();
    @OneToMany(mappedBy = "beneficiaire")
    List<Transaction> transactionsRecues = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "UserContacts",
            joinColumns = @JoinColumn(name = "user_account_id"),
            inverseJoinColumns = @JoinColumn(name = "user_contact_id"))
    private List<UserAccount> contacts = new ArrayList<>();

    @OneToMany(mappedBy = "userAccount")
    List<Transfert> transferts = new ArrayList<>();

    public List<Transaction> getTransactionsEmises() {
        return transactionsEmises;
    }

    public void setTransactionsEmises(List<Transaction> transactionsEmises) {
        this.transactionsEmises = transactionsEmises;
    }

    public List<Transaction> getTransactionsRecues() {
        return transactionsRecues;
    }

    public void setTransactionsRecues(List<Transaction> transactionsRecues) {
        this.transactionsRecues = transactionsRecues;
    }

    public List<UserAccount> getContacts() {
        return contacts;
    }

    public void setContacts(List<UserAccount> contacts) {
        this.contacts = contacts;
    }

    public List<Transfert> getTransferts() {
        return transferts;
    }

    public void setTransferts(List<Transfert> transferts) {
        this.transferts = transferts;
    }

    public UserAccount() {
    }

    public UserAccount(Long solde, String name, String eMail, String password) {
        this.solde = solde;
        this.name = name;
        this.eMail = eMail;
        this.password = password;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserAccount_id() {
        return userAccount_id;
    }

    public void setUserAccount_id(int userAccount_id) {
        this.userAccount_id = userAccount_id;
    }

    public Long getSolde() {
        return solde;
    }

    public void setSolde(Long solde) {
        this.solde = solde;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

