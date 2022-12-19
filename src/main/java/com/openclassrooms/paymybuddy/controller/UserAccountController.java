package com.openclassrooms.paymybuddy.controller;

import com.openclassrooms.paymybuddy.entity.UserAccount;
import com.openclassrooms.paymybuddy.service.UserAccountService;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
public class UserAccountController {
    @Autowired
    private UserAccountService userAccountService;


    @PostMapping("/userAccount/add")
    public String addUserAccount(UserAccount userAccount, RedirectAttributes ra) {
        userAccountService.saveUserAccount(userAccount);
        ra.addFlashAttribute("message", "Welcome you are a new PayMyBuddy User please Log in!");
        return "redirect:/";
    }

    @GetMapping("/userAccount/new")
    public String showNewForm(Model model) {
        model.addAttribute("userAccount", new UserAccount());
        return "user_form";
    }

    @GetMapping("/addContact")
    public String showNewContactForm(Model model) {
        log.info("get the contact form");
        try{  String message = "Register a new Contact!";
            model.addAttribute("message",message);

        }catch (RuntimeException e){
            System.out.println("on a un runtime exception!");
        }

        return "contact";
    }

//    @PostMapping("/userAccount/addContact")
//    public UserAccount addContact(@RequestParam("id") int id, @RequestBody UserAccount userAccountToAddAsContact) {
//        return userAccountService.saveContact(id, userAccountToAddAsContact);
//    }

    @PostMapping("/userAccount/saveContact/{email}{id}")
    public String saveContact(@PathVariable String email, @PathVariable int id, Model model) {
         userAccountService.addContact(email, id);
         return "redirect:/userAccount";
    }


    @GetMapping("userAccount/connection")
    public String connexion(Model model) {
        model.addAttribute("userAccount", new UserAccount());
        return "login";
    }

    @GetMapping("/userAccount/{id}")
    public String getUserById(Model model, @PathVariable int id) {
        UserAccount user = userAccountService.getUserAccountById(id);
        model.addAttribute("userAccount", user);
        return "userAccount";
    }

//    @GetMapping("/userAccountByMail")
//    public String getUserByEmail(@RequestParam(required = false) String email, Model model) {
//        log.info("récupération du getUser ok ");
//        UserAccount userByMail = userAccountService.findByEmail(email);
//        model.addAttribute("userAccount", userByMail);
//        return "userAccount";
//
//    }


    @GetMapping("/userAccounts")
    public List<UserAccount> getUsersList() {
        return userAccountService.findAllUserAccounts();
    }

    @DeleteMapping("/userAccount/{id}")
    public UserAccount deleteUser(@PathVariable int id) {
        return userAccountService.deleteUserAccount(id);
    }

    @PutMapping("/userAccount/{id}")
    public UserAccount updateById(@RequestBody UserAccount userAccount, @PathVariable int id) {
        userAccount.setUserAccount_id(id);
        return userAccountService.updateUserAccount(userAccount);
    }

}
