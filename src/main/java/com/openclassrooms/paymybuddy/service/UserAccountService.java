package com.openclassrooms.paymybuddy.service;

import com.openclassrooms.paymybuddy.entity.UserAccount;
import com.openclassrooms.paymybuddy.repository.UserAccountRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserAccountService {
    @Autowired
    private UserAccountRepository userAccountRepository;


    public UserAccount saveUserAccount(UserAccount userAccountReceive) {

        return userAccountRepository.save(userAccountReceive);
    }

    public UserAccount getUserAccountById(int id) {
        return userAccountRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public UserAccount deleteUserAccount(int id) {
        userAccountRepository.deleteById(id);
        return null;
    }

    public UserAccount updateUserAccount(@NotNull UserAccount userAccountReceive, int id) {

        UserAccount userAccountToUpdate = getUserAccountById(id);

        userAccountToUpdate.setName(userAccountReceive.getName());
        userAccountToUpdate.setSolde(userAccountReceive.getSolde());
        userAccountToUpdate.setPassword(userAccountReceive.getPassword());
        userAccountToUpdate.seteMail(userAccountReceive.geteMail());

        return userAccountRepository.save(userAccountToUpdate);
    }

    public List<UserAccount> findAllUserAccounts() {
        return userAccountRepository.findAll();
    }
}
