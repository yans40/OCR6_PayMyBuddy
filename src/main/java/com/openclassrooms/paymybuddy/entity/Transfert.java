package com.openclassrooms.paymybuddy.entity;

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

}
