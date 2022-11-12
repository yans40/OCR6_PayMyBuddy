package com.openclassrooms.paymybuddy.controller;

import com.openclassrooms.paymybuddy.entity.UserAccount;
import com.openclassrooms.paymybuddy.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserAcompteController {
    @Autowired
    private UserAccountService userAccountService;

    @PostMapping("/addUser")
    public UserAccount addUserAcompte(@RequestBody UserAccount userAccount){
        return userAccountService.saveUserAccount(userAccount);
    }

    @GetMapping("/userAcompte/{id}")
    public UserAccount getUserById(@PathVariable int id){
        return userAccountService.getUserAccountById(id);
    }

    @GetMapping("/userAcomptes")
    public List<UserAccount> getUsersList(){
        return userAccountService.findAllUserAccounts();
    }
    @DeleteMapping("/deleteUser/{id}")
    public UserAccount deleteUser(@PathVariable int id){
       return userAccountService.deleteUserAccount(id);
    }

    @PutMapping("/updateUserAcompte/{id}")
    public UserAccount updateById(@RequestBody UserAccount userAccount, @PathVariable int id){

        return userAccountService.updateUserAccount(userAccount,id);
    }
}
