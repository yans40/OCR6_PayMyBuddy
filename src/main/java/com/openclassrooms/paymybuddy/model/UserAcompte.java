package com.openclassrooms.paymybuddy.model;

import javax.persistence.*;


@Entity
@Table
public class UserAcompte {
    @Id
    @Column
    private Long id;
    @Column
    private Long solde;
    @Column
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

