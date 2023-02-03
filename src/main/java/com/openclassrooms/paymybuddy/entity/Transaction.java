package com.openclassrooms.paymybuddy.entity;

import lombok.Data;

import javax.persistence.*;


@Entity
@Data
@Table
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private int transaction_Id;
    @Column
    private String description;

    @Column(nullable = false)
    private double montant;
    @Column(nullable = false)
    private double frais;

    @ManyToOne
    @JoinColumn(name = "emetteur",nullable = false)
    private UserAccount emetteur;

    @ManyToOne
    @JoinColumn(name = "beneficiaire",nullable = false)
    private UserAccount beneficiaire;

    public Transaction(String description, double montant, UserAccount emetteur, UserAccount beneficiaire) {
        this.description = description;
        this.montant = montant;
        this.emetteur = emetteur;
        this.beneficiaire = beneficiaire;
    }


    public Transaction() {
    }


    public UserAccount getEmetteur() {
        return emetteur;
    }

    public void setEmetteur(UserAccount emetteur) {
        this.emetteur = emetteur;
    }

    public UserAccount getBeneficiaire() {
        return beneficiaire;
    }

    public void setBeneficiaire(UserAccount beneficiaire) {
        this.beneficiaire = beneficiaire;
    }

    public Integer getTransaction_Id() {
        return transaction_Id;
    }

    public void setTransaction_Id(int transaction_Id) {
        this.transaction_Id = transaction_Id;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public double getFrais() {
        return frais;
    }

    public void setFrais(double frais) {
        this.frais = frais;
    }
}
