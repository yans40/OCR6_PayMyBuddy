package com.openclassrooms.paymybuddy.entity;

import javax.persistence.*;

@Entity
@Table(name = "bank_acompte")
public class BankAcompte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer acompte_Id;
    @Column
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
