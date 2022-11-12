package com.openclassrooms.paymybuddy.service;

import com.openclassrooms.paymybuddy.entity.UserAccount;
import com.openclassrooms.paymybuddy.repository.UserAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    void saveUserAccountServiceMethodTest() {
        //ARRANGE
        UserAccount userAccountToSave = new UserAccount(5000L, "alexandre", "alexlegrand@mail.com", "lepasse");
        UserAccount userAccountToSave1 = new UserAccount(15000L, "jean", "jean@mail.com", "monmotdepasse");
        UserAccount userAccountToSave2 = new UserAccount(3000L, "brigitte", "brigitte@mail.com", "lepassword");

        //ACT
        UserAccount userAccountSaved = userAccountService.saveUserAccount(userAccountToSave);
        UserAccount userAccountSaved1 = userAccountService.saveUserAccount(userAccountToSave1);
        UserAccount userAccountSaved2 = userAccountService.saveUserAccount(userAccountToSave2);

        UserAccount savedUserAccount = userAccountService.getUserAccountById(userAccountSaved.getUserAccount_id());
        UserAccount savedUserAccount1 = userAccountService.getUserAccountById(userAccountSaved1.getUserAccount_id());
        //ASSERT
        assertEquals("lepasse", savedUserAccount.getPassword());
        assertEquals("jean", savedUserAccount1.getName());
        assertEquals("brigitte@mail.com", userAccountSaved2.geteMail());

    }

    @Test
    void updateUserAccountServiceMethodTest() {

        //ARRANGE
        UserAccount userAccount = new UserAccount(5000L, "alexandre", "alexlegrand@mail.com", "lepasse");

        UserAccount updatedUser = new UserAccount(600L, "alexandre", "jeannewmail@mail.com", "newmotdepasse");

        UserAccount userAccountSaved = userAccountService.saveUserAccount(userAccount);
        //ACT
        UserAccount userAccountUpdated = userAccountService.updateUserAccount(updatedUser, userAccountSaved.getUserAccount_id());
        //ASSERT
        assertEquals("jeannewmail@mail.com", userAccountService.getUserAccountById(userAccountUpdated.getUserAccount_id()).geteMail());

    }

    @Test
    void deleteUserAccountServiceMethodTest() {
        //ARRANGE
        UserAccount userAccountToSave = new UserAccount(5000L, "alexandre", "alexlegrand@mail.com", "lepasse");

        UserAccount userAccountSaved = userAccountService.saveUserAccount(userAccountToSave);
        //ACT
        userAccountService.deleteUserAccount(userAccountSaved.getUserAccount_id());
        //ASSERT
        assertEquals(0, userAccountRepository.findAll().size());
    }

    @Test
    void findAllUserAccountServiceTest(){
        //ARRANGE
        UserAccount userAccount1 = new UserAccount(5000L, "alexandre", "alexlegrand@mail.com", "lepasse");

        UserAccount userAccount2 = new UserAccount(600L, "alexandre", "jeannewmail@mail.com", "newmotdepasse");
        //ACT
        userAccountService.saveUserAccount(userAccount1);
        userAccountService.saveUserAccount(userAccount2);
        userAccountService.findAllUserAccounts();
        //ASSERT
        assertEquals(2, userAccountRepository.findAll().size());
    }

}
