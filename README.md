# OCR6_PayMyBuddy
![image](https://user-images.githubusercontent.com/46260168/214047374-d6f6fae0-135b-450a-b947-872a1a06d2f5.png)


# Modèle physique de données
![image](https://user-images.githubusercontent.com/46260168/214054853-8350249e-2d57-4462-a282-a9d6869509b9.png)

# SCRIPT SQL

create table if not exists transaction
(
    transaction_id int auto_increment
        primary key,
    date           varchar(255) null,
    description    varchar(255) null,
    frais          double       null,
    montant        int          null,
    beneficiaire   int          null,
    emetteur       int          null
);

create table if not exists transfert
(
    transfert_id                 int auto_increment
        primary key,
    iban                         varchar(255) null,
    date                         varchar(255) null,
    montant                      int          null,
    transfert_type               int          null,
    user_account_user_account_id int          null
);


create table if not exists user_account
(
    user_account_id int auto_increment
        primary key,
    e_mail          varchar(255) null,
    name            varchar(255) null,
    password        varchar(255) null,
    solde           bigint       null
);

create table if not exists user_contacts
(
    user_account_id int not null,
    user_contact_id int not null
);

