package com.openclassrooms.paymybuddy.service;

import com.openclassrooms.paymybuddy.entity.UserAccount;
import com.openclassrooms.paymybuddy.exceptions.MailAlreadyExistException;
import com.openclassrooms.paymybuddy.repository.UserAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserAccountServiceTest {

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private UserAccountRepository userAccountRepository;

    @BeforeEach
    void cleanDb() {
        this.userAccountRepository.deleteAll();
    }

    @Test
    void saveUserAccountServiceMethodTest() throws MailAlreadyExistException {
        //ARRANGE

        UserAccount userAccountToSave = new UserAccount(5000L, "mathis", "math@math.co", "lepasse");

        UserAccount userAccountWithSameMail = new UserAccount(3000L, "jeremy", "math@math.co", "leword");


        userAccountService.saveUserAccount(userAccountToSave);

        assertEquals(1, userAccountRepository.findAll().size());

        //ACT
        userAccountService.saveUserAccount(userAccountWithSameMail);

        //ASSERT
        assertEquals(1, userAccountRepository.findAll().size());

    }

//    @Test
//    void updateUserAccountServiceMethodTest() {
//
//        //ARRANGE
//        UserAccount userAccount = new UserAccount(5000L, "alexandre", "alexlegrand@mail.com", "lepasse");
//
//        UserAccount updatedUser = new UserAccount(600L, "alexandre", "jeannewmail@mail.com", "newmotdepasse");
//
//        UserAccount userAccountSaved = userAccountService.saveUserAccount(userAccount);
//        //ACT
//        UserAccount userAccountUpdated = userAccountService.updateUserAccount(updatedUser, userAccountSaved.getUserAccount_id());
//        //ASSERT
//        assertEquals("jeannewmail@mail.com", userAccountService.getUserAccountById(userAccountUpdated.getUserAccount_id()).geteMail());
//
//    }

//    @Test
//    void deleteUserAccountServiceMethodTest() {
//        //ARRANGE
//        UserAccount userAccountToSave = new UserAccount(5000L, "alexandre", "alexlegrand@mail.com", "lepasse");
//
//        UserAccount userAccountSaved = userAccountService.saveUserAccount(userAccountToSave);
//        //ACT
//        userAccountService.deleteUserAccount(userAccountSaved.getUserAccount_id());
//        //ASSERT
//        assertEquals(0, userAccountRepository.findAll().size());
//    }
//
    @Test
    void findAllUserAccountServiceTest() throws MailAlreadyExistException {
        //ARRANGE
        UserAccount userAccount1 = new UserAccount(5000L, "alexandre", "alexlegrand@mail.com", "lepasse");
        UserAccount userAccount2 = new UserAccount(600L, "jean", "jeannewmail@mail.com", "newmotdepasse");
        UserAccount userAccount3 = new UserAccount(600L, "martine", "martine@mail.com", "passe");

        //ACT
        userAccountService.saveUserAccount(userAccount1);
        userAccountService.saveUserAccount(userAccount2);
        userAccountService.saveUserAccount(userAccount3);
        userAccountService.findAllUserAccounts();
        //ASSERT
        assertEquals(3, userAccountRepository.findAll().size());
    }

}
