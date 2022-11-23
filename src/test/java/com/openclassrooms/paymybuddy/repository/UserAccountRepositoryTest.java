package com.openclassrooms.paymybuddy.repository;

import com.openclassrooms.paymybuddy.entity.UserAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserAccountRepositoryTest {
    @Autowired
    private UserAccountRepository userAccountRepository;

    @BeforeEach
    void cleanDb() {
        this.userAccountRepository.deleteAll();
    }

    @Test
    void saveTest(){
        UserAccount userAccount = new UserAccount();
        userAccount.setName("olivier");
        userAccount.seteMail("olivier@mail.fr");
        userAccount.setPassword("olive");
        userAccount.setSolde(23000L);

        userAccountRepository.save(userAccount);
        UserAccount userAccountSavedInDb = userAccountRepository.findById(userAccount.getUserAccount_id()).orElseThrow(NoSuchElementException::new);

        assertEquals("olivier@mail.fr", userAccountSavedInDb.geteMail());

    }


    @Test
    void findTest(){

    }

    @Test
    void deleteTest(){

    }


}
