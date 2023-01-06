package com.openclassrooms.paymybuddy.service;

import com.openclassrooms.paymybuddy.entity.UserAccount;
import com.openclassrooms.paymybuddy.exceptions.MailAlreadyExistException;
import com.openclassrooms.paymybuddy.repository.UserAccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
public class UserAccountService {
    @Autowired
    private UserAccountRepository userAccountRepository;

    public UserAccount saveUserAccount(@NotNull UserAccount userAccountReceive) throws MailAlreadyExistException {
        UserAccount userAccountFind = userAccountRepository.findByEMail(userAccountReceive.geteMail());
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if (userAccountFind != null) {
            log.debug("le mail est déja utilisé");
            throw new MailAlreadyExistException("Error : ce Mail est déjà utilisé comme Id renseignez un autre mail");
        } else {
            String passwordEncode=bCryptPasswordEncoder.encode(userAccountReceive.getPassword());
            userAccountReceive.setPassword(passwordEncode);
            return userAccountRepository.save(userAccountReceive);
        }

    }

    public UserAccount saveContact(int id, UserAccount contactToAdd) {
        UserAccount user = getUserAccountById(id);
        List<UserAccount> contacts = user.getContacts();
        contacts.add(contactToAdd);
        user.setContacts(contacts);
        return userAccountRepository.save(user);

    }

    public UserAccount removeContact(int userId,int contactId){
       log.info( "delete contact in service");
        UserAccount user= getUserAccountById(userId);
        UserAccount userContactToRemove=getUserAccountById(contactId);
        List<UserAccount> contacts=user.getContacts();
        contacts.remove(userContactToRemove);
        return  userAccountRepository.save(user);
    }

    public UserAccount findByEmail(String email) {
        return userAccountRepository.findByEMail(email);
    }

    public UserAccount getUserAccountById(int id) {
        return userAccountRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public UserAccount deleteUserAccount(int id) {
        userAccountRepository.deleteById(id);
        return null;
    }

    public UserAccount updateUserAccount(@NotNull UserAccount userAccountReceive) {
        UserAccount userAccountToUpdate = getUserAccountById(userAccountReceive.getUserAccount_id());

        userAccountToUpdate.setName(userAccountReceive.getName());
        userAccountToUpdate.setSolde(userAccountReceive.getSolde());
        userAccountToUpdate.setPassword(userAccountReceive.getPassword());
        userAccountToUpdate.seteMail(userAccountReceive.geteMail());

        return userAccountRepository.save(userAccountToUpdate);
    }

    public void updateUserAccountBalanceAfterTransfertOrTransaction(@NotNull UserAccount userAccountReceive) {
        UserAccount userAccountToUpdate = getUserAccountById(userAccountReceive.getUserAccount_id());

        userAccountToUpdate.setSolde(userAccountReceive.getSolde());

        userAccountRepository.save(userAccountToUpdate);
    }

    public List<UserAccount> findAllUserAccounts() {
        return userAccountRepository.findAll();
    }

    public String addContact(String emailReceive, int id) {
        UserAccount userToAddAsContact = findByEmail(emailReceive);
        UserAccount user = getUserAccountById(id);
        List<UserAccount> contacts= user.getContacts();
        contacts.add(userToAddAsContact);
        userAccountRepository.save(user);

        return emailReceive;
    }
}
