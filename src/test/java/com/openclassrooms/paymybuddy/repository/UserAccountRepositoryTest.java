package com.openclassrooms.paymybuddy.repository;

import com.openclassrooms.paymybuddy.entity.UserAccount;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserAccountRepositoryTest {
    @Autowired
    private UserAccountRepository userAccountRepository;

    @Test
    void saveMethodTest(){
        UserAccount userAccount = new UserAccount();
        userAccount.setName("alexandre");
        userAccount.seteMail("alex@mail.fr");
        userAccount.setPassword("legrand");
        userAccount.setSolde(15000L);

        userAccountRepository.save(userAccount);
        UserAccount userAccountSavedInDb = userAccountRepository.findById(userAccount.getUserAccount_id()).orElseThrow(NoSuchElementException::new);

        assertEquals("alex@mail.fr", userAccountSavedInDb.geteMail());

    }


}
