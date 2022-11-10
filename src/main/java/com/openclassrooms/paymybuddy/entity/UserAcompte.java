package com.openclassrooms.paymybuddy.entity;

import javax.persistence.*;


@Entity
@Table(name = "user_acompte")
public class UserAcompte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int userAcompte_id;
    @Column
    private Long solde;
    @Column
    private String name;
    @Column
    private String eMail;
    @Column
    private String password;


    public UserAcompte() {
    }

    public UserAcompte(Long solde, String name, String eMail, String password) {
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

    public int getUserAcompte_id() {
        return userAcompte_id;
    }

    public void setUserAcompte_id(int userAcompte_id) {
        this.userAcompte_id = userAcompte_id;
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

