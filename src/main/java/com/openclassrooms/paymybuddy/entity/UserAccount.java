package com.openclassrooms.paymybuddy.entity;

import javax.persistence.*;


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

