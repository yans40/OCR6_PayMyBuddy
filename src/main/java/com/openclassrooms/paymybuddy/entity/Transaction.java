package com.openclassrooms.paymybuddy.entity;

import javax.persistence.*;

@Entity
@Table
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int transaction_Id;
    @Column
    private String description;
    @Column
    private Integer montant;
    @Column
    private String date;
    @Column
    private Integer frais;

    @ManyToOne
    @JoinColumn(name = "emetteur")
    private UserAccount emetteur;

    @ManyToOne
    @JoinColumn(name = "beneficiaire")
    private UserAccount beneficiaire;

    public Transaction(String description, Integer montant, String date, Integer frais, UserAccount emetteur, UserAccount beneficiaire) {
        this.description = description;
        this.montant = montant;
        this.date = date;
        this.frais = frais;
        this.emetteur = emetteur;
        this.beneficiaire = beneficiaire;
    }

    public Transaction( String date,Integer montant, Integer frais) {

        this.montant = montant;
        this.date = date;
        this.frais = frais;
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

    public Integer getMontant() {
        return montant;
    }

    public void setMontant(Integer montant) {
        this.montant = montant;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
