package com.openclassrooms.paymybuddy.controller;

import com.openclassrooms.paymybuddy.entity.UserAccount;
import com.openclassrooms.paymybuddy.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserAccountController {
    @Autowired
    private UserAccountService userAccountService;


    @PostMapping("/addUser")
    public UserAccount addUserAccount(@RequestBody UserAccount userAccount) {
        return userAccountService.saveUserAccount(userAccount);
    }

    @PostMapping("/addContact/{id}")
    public UserAccount addContact(@PathVariable int id,@RequestBody UserAccount userAccountToAddAsContact){
        return userAccountService.saveContact(id,userAccountToAddAsContact);

    }

    @GetMapping("/userAccount/{id}")
    public UserAccount getUserById(@PathVariable int id) {
        return userAccountService.getUserAccountById(id);
    }

    @GetMapping("/userAccounts")
    public List<UserAccount> getUsersList() {
        return userAccountService.findAllUserAccounts();
    }

    @DeleteMapping("/deleteUser/{id}")
    public UserAccount deleteUser(@PathVariable int id) {
        return userAccountService.deleteUserAccount(id);
    }

    @PutMapping("/updateUserAccount/{id}")
    public UserAccount updateById(@RequestBody UserAccount userAccount, @PathVariable int id) {
        userAccount.setUserAccount_id(id);
        return userAccountService.updateUserAccount(userAccount);
    }
}
