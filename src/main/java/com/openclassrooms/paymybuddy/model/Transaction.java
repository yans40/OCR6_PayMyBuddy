package com.openclassrooms.paymybuddy.model;

import javax.persistence.*;

@Entity
@Table
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column
    private Integer transaction_Id;
    @Column
    private Integer montant;
    @Column
    private String date;
    @Column
    private Integer frais;

}
