# OCR6_PayMyBuddy
![image](https://user-images.githubusercontent.com/46260168/214047374-d6f6fae0-135b-450a-b947-872a1a06d2f5.png)


# Modèle physique de données
![image](https://user-images.githubusercontent.com/46260168/216383134-ce05494f-c8d0-4f42-9567-3d70a93a0e51.png)

# SCRIPT SQL

drop database if exists payMyBuddyApp;
create database payMyBuddyApp;
use payMyBuddyApp;
CREATE TABLE user_account
(
    user_account_id INT          NOT NULL auto_increment,
    solde           DOUBLE       NOT NULL,
    name            VARCHAR(255) NOT NULL,
    e_mail          VARCHAR(255) NOT NULL,
    password        VARCHAR(255) NOT NULL,
    CONSTRAINT pk_user_account PRIMARY KEY (user_account_id)
);

CREATE TABLE user_contacts
(
    user_account_id INT NOT NULL,
    user_contact_id INT NOT NULL
);

ALTER TABLE user_account
    ADD CONSTRAINT uc_user_account_email UNIQUE (e_mail);

ALTER TABLE user_contacts
    ADD CONSTRAINT fk_usecon_on_user_account FOREIGN KEY (user_account_id) REFERENCES user_account (user_account_id);

ALTER TABLE user_contacts
    ADD CONSTRAINT fk_usecon_on_user_contact FOREIGN KEY (user_contact_id) REFERENCES user_account (user_account_id);
    
CREATE TABLE transaction
(
    transaction_id INT          NOT NULL auto_increment,
    `description`  VARCHAR(255) NULL,
    montant        DOUBLE       NOT NULL,
    frais          DOUBLE       NOT NULL,
    emetteur       INT          NOT NULL,
    beneficiaire   INT          NOT NULL,
    CONSTRAINT pk_transaction PRIMARY KEY (transaction_id)
);

ALTER TABLE transaction
    ADD CONSTRAINT FK_TRANSACTION_ON_BENEFICIAIRE FOREIGN KEY (beneficiaire) REFERENCES user_account (user_account_id);

ALTER TABLE transaction
    ADD CONSTRAINT FK_TRANSACTION_ON_EMETTEUR FOREIGN KEY (emetteur) REFERENCES user_account (user_account_id);

CREATE TABLE transfert
(
    transfert_id                 INT          NOT NULL auto_increment,
    transfert_type               INT          NOT NULL,
    montant                      DOUBLE       NOT NULL,
    iban                         VARCHAR(255) NOT NULL,
    user_account_user_account_id INT          NULL,
    CONSTRAINT pk_transfert PRIMARY KEY (transfert_id)
);

ALTER TABLE transfert
    ADD CONSTRAINT FK_TRANSFERT_ON_USERACCOUNT_USERACCOUNT FOREIGN KEY (user_account_user_account_id) REFERENCES user_account (user_account_id);

insert into user_account (e_mail, name, password, solde) values ("max@test.com", "max", "azerty", 0 );
insert into user_account (e_mail, name, password, solde) values ("jeanne@test.com", "jeanne", "qwerty", 0);
insert into user_account (e_mail, name, password, solde) values ("laurent@test.com", "laurent", "azerty", 0)
    
    

