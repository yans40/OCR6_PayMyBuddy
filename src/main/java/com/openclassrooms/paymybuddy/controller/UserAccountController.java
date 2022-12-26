package com.openclassrooms.paymybuddy.controller;

import com.openclassrooms.paymybuddy.entity.Transaction;
import com.openclassrooms.paymybuddy.entity.UserAccount;
import com.openclassrooms.paymybuddy.service.TransactionService;
import com.openclassrooms.paymybuddy.service.TransfertService;
import com.openclassrooms.paymybuddy.service.UserAccountService;
import lombok.extern.slf4j.Slf4j;
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

    @Autowired
    private TransactionService transactionService;

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

    @GetMapping("/search-userAccount")
    public String checkContactMailForm(){
        log.info("get the contact form");
        return "search-userAccount";
    }

    @PostMapping("/search-userAccount")
    public String chercherLeContact(@RequestParam String email, Model model) {
        log.info("post the mail for checking");
        UserAccount contactToFinded = userAccountService.findByEmail(email);
        String message = "Aucun contact trouvé avec le mail suivant  ";

        if(contactToFinded !=null){
            model.addAttribute("contactTrouve", contactToFinded);
        } else {
            model.addAttribute("erreur",message + email);
        }
        return "contactFinded-infos";
    }

    @PostMapping("/addToContacts")
    public String ajoutContact(@RequestParam(name = "eMail") String email,Model model){
        log.info("j'enregistre le contact ");
        UserAccount userJean = userAccountService.getUserAccountById(3);
        UserAccount contactToFinded = userAccountService.findByEmail(email);
        List<UserAccount> contacts= userJean.getContacts();
        contacts.add(contactToFinded);
        userAccountService.saveContact(userJean.getUserAccount_id(),contactToFinded);
        String confirmation = "le contact a bien été ajouté!";
        model.addAttribute("confirmation", confirmation);

      return "contactFinded-infos";
    }


    @GetMapping("userAccount/connection")
    public String connexion(Model model) {
        model.addAttribute("userAccount", new UserAccount());
        return "login";
    }

    @GetMapping("/userAccount/{id}")
    public String getUserById(Model model, @PathVariable int id) {
        log.info("je suis sur la page du User");
        UserAccount user = userAccountService.getUserAccountById(id);
        String message = "Welcome M./Mme ";
        List<UserAccount> contacts = user.getContacts();
        List<Transaction> transactionList = user.getTransactionsEmises();
        model.addAttribute("transactionList", transactionList);
        model.addAttribute("transaction",new Transaction());
        model.addAttribute("contacts",contacts);
        model.addAttribute("userAccount", user);
        model.addAttribute("message", message);
        return "userAccount";
    }


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
