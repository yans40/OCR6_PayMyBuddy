package com.openclassrooms.paymybuddy.controller;

import com.openclassrooms.paymybuddy.entity.UserAcompte;
import com.openclassrooms.paymybuddy.service.UserAcompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserAcompteController {
    @Autowired
    private UserAcompteService userAcompteService;

    @PostMapping("/addUser")
    public UserAcompte addUserAcompte(@RequestBody UserAcompte userAcompte){
        return userAcompteService.saveUserAcompte(userAcompte);
    }

    @DeleteMapping("/deleteUser/{id}")
    public UserAcompte deleteUser(@PathVariable int id){
       return userAcompteService.deleteUserAcompte(id);
    }

    @GetMapping("/userAcompte/{id}")
    public UserAcompte getUserById(@PathVariable int id){
        return userAcompteService.getUserAcompte(id);
    }
}
