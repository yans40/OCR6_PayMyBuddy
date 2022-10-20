package com.openclassrooms.paymybuddy.model;

import javax.persistence.*;

@Entity
@Table
public class Transfert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column
    private String description;
    @Column
    private Integer montant;
    @Column
    private String date;

// TODO CHECK THE TYPE OF OPERATION   private Boolean operation;
}
