package com.openclassrooms.paymybuddy.controller;

import com.openclassrooms.paymybuddy.entity.UserAcompte;
import com.openclassrooms.paymybuddy.service.UserAcompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserAcompteController {
    @Autowired
    private UserAcompteService userAcompteService;

    @PostMapping("/addUser")
    public UserAcompte addUserAcompte(@RequestBody UserAcompte userAcompte){
        return userAcompteService.saveUserAcompte(userAcompte);
    }

    @GetMapping("/userAcompte/{id}")
    public UserAcompte getUserById(@PathVariable int id){
        return userAcompteService.getUserAcompteById(id);
    }

    @GetMapping("/userAcomptes")
    public List<UserAcompte> getUsersList(){
        return userAcompteService.findAllUserAcomptes();
    }
    @DeleteMapping("/deleteUser/{id}")
    public UserAcompte deleteUser(@PathVariable int id){
       return userAcompteService.deleteUserAcompte(id);
    }

    @PutMapping("/updateUserAcompte/{id}")
    public UserAcompte updateById(@RequestBody UserAcompte userAcompte, @PathVariable int id){

        return userAcompteService.updateUserAcompte(userAcompte,id);
    }
}
