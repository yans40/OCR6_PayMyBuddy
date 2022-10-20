package com.openclassrooms.paymybuddy.model;

import javax.persistence.*;

@Entity
@Table
public class BankAcompte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer acompte_Id;

    private String Iban;

    public Integer getAcompte_Id() {
        return acompte_Id;
    }

    public void setAcompte_Id(Integer acompte_Id) {
        this.acompte_Id = acompte_Id;
    }

    public String getIban() {
        return Iban;
    }

    public void setIban(String iban) {
        Iban = iban;
    }
}
