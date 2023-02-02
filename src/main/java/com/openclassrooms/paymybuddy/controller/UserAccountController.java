package com.openclassrooms.paymybuddy.controller;

import com.openclassrooms.paymybuddy.entity.Transaction;
import com.openclassrooms.paymybuddy.entity.UserAccount;
import com.openclassrooms.paymybuddy.exceptions.MailAlreadyExistException;
import com.openclassrooms.paymybuddy.service.UserAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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




    @PostMapping(value = "/userAccount/add")
    public String addUserAccount(UserAccount userAccount, RedirectAttributes ra) throws MailAlreadyExistException {
log.info("controller user add method ");
        try {
            userAccountService.saveUserAccount(userAccount);
            ra.addFlashAttribute("message", "Welcome you are a new PayMyBuddy User please Log in!");
            return "redirect:/";
        } catch (MailAlreadyExistException e) {
            ra.addFlashAttribute("message", "ce Mail est déjà utilisé comme Id renseignez un autre mail");
        }
        return "redirect:/";
    }


    @GetMapping("/userAccount/new")
    public String showNewForm(Model model) {
        model.addAttribute("userAccount", new UserAccount());
        return "subscription";
    }

    @GetMapping("/userAccount/{id}/search-userAccount")
    public String checkContactMailForm(@PathVariable int id, Model model) {
        log.info("get the contact form");
        UserAccount currentUser = userAccountService.getUserAccountById(id);
        model.addAttribute("userAccount", currentUser);
        return "search-userAccount";
    }

    @PostMapping("/userAccount/{id}/search-userAccount")
    public String chercherLeContact(@PathVariable int id, @RequestParam String email, Model model) {
        log.info("post the mail for checking");

        UserAccount contactToFinded = userAccountService.findByEmail(email);
        String message = "Aucun contact trouvé avec le mail suivant  ";
        UserAccount currentUser = userAccountService.getUserAccountById(id);

        if (contactToFinded != null) {
            model.addAttribute("userAccount", currentUser);
            model.addAttribute("contactTrouve", contactToFinded);
        } else {
            model.addAttribute("erreur", message + email);
        }
        return "contactFinded-infos";
    }

    @PostMapping("/userAccount/{id}/addToContacts")
    public String ajoutContact(@PathVariable int id, @RequestParam(name = "eMail") String email, Model model) throws MailAlreadyExistException {
        log.info("j'enregistre le contact ");
        UserAccount currentUser = userAccountService.getUserAccountById(id);
        UserAccount contactToFind = userAccountService.findByEmail(email);
        String confirmation = "le contact a bien été ajouté";
        userAccountService.saveContact(currentUser.getUserAccount_id(), contactToFind);
        model.addAttribute("userAccount", currentUser);
        model.addAttribute("confirmation", confirmation);

        return "contactFinded-infos";
    }


    @GetMapping("/userAccount/connection")
    public String connexion(Model model) throws Exception {
        model.addAttribute("userAccount", new UserAccount());
        return "redirect:/userAccount";
    }


    @GetMapping ("/userAccount")
    public String showUserHomePage(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        log.info(currentPrincipalName);

        UserAccount user = userAccountService.findByEmail(currentPrincipalName);


        log.info("User Home Page");
        String message = "Bienvenue ";
        List<UserAccount> contacts = user.getContacts();
        List<Transaction> transactionList = user.getTransactionsEmises();
        model.addAttribute("transactionList", transactionList);
        model.addAttribute("transaction", new Transaction());
        model.addAttribute("contacts", contacts);
        model.addAttribute("userAccount", user);
        model.addAttribute("message", message);

        return "userAccountDisplay";
    }


    @GetMapping("/userAccount/{id}/contacts")
    public String showContactsList(@PathVariable int id, Model model) {
        UserAccount user = userAccountService.getUserAccountById(id);
        List<UserAccount> contacts = user.getContacts();
        model.addAttribute("contacts", contacts);
        return "contacts";
    }

    @GetMapping("/userAccount/{id}/contacts/{contactId}")
    public String deleteContact(@PathVariable int id, @PathVariable int contactId, Model model) {
        log.info("delete contact in controller");
        UserAccount user = userAccountService.getUserAccountById(id);
        try {
            userAccountService.removeContact(id, contactId);
            model.addAttribute("userAccount", user);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "delete fail");
        }

        return "redirect:/userAccount/{id}/contacts/";
    }


    @RequestMapping(value = "/perform_logout",method = RequestMethod.POST)
    public String logout(RedirectAttributes ra){
       log.info("in log out!");
        ra.addAttribute("message", "vous êtes bien déconnecté A bientôt!");
        return "redirect:/";
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
