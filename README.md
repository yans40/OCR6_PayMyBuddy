# OCR6_PayMyBuddy
![image](https://user-images.githubusercontent.com/46260168/214047374-d6f6fae0-135b-450a-b947-872a1a06d2f5.png)


# Modèle physique de données
![image](https://user-images.githubusercontent.com/46260168/214054853-8350249e-2d57-4462-a282-a9d6869509b9.png)

#SCRIPT SQL

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
)
    engine = MyISAM;

create index FK73wl19m4t821gca3wllggwhk5
    on transaction (emetteur);

create index FKqa81s61benxx0mtda90f9ri7m
    on transaction (beneficiaire);

create table if not exists transfert
(
    transfert_id                 int auto_increment
        primary key,
    iban                         varchar(255) null,
    date                         varchar(255) null,
    montant                      int          null,
    transfert_type               int          null,
    user_account_user_account_id int          null
)
    engine = MyISAM;

create index FKtb654u1uxq4ip4kx9btexybkj
    on transfert (user_account_user_account_id);

create table if not exists user_account
(
    user_account_id int auto_increment
        primary key,
    e_mail          varchar(255) null,
    name            varchar(255) null,
    password        varchar(255) null,
    solde           bigint       null
)
    engine = MyISAM;

create table if not exists user_contacts
(
    user_account_id int not null,
    user_contact_id int not null
)
    engine = MyISAM;

create index FK6y03d5re7axqup94dd095yjgn
    on user_contacts (user_contact_id);

create index FK7ck29uay6jwpg2glnq00ayuy8
    on user_contacts (user_account_id);
