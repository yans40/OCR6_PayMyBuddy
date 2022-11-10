package com.openclassrooms.paymybuddy.entity;

import javax.persistence.*;

@Entity
@Table
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column
    private int transaction_Id;
    @Column
    private Integer montant;
    @Column
    private String date;
    @Column
    private Integer frais;


    public Transaction(String date, Integer montant, Integer frais) {
        this.date=date;
        this.montant=montant;
        this.frais=frais;
    }

    public Transaction () {
    }

    public int getTransaction_Id() {
        return transaction_Id;
    }

    public void setTransaction_Id(int transaction_Id) {
        this.transaction_Id = transaction_Id;
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

    public Integer getFrais() {
        return frais;
    }

    public void setFrais(Integer frais) {
        this.frais = frais;
    }
}
