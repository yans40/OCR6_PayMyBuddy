package com.openclassrooms.paymybuddy.repository;

import com.openclassrooms.paymybuddy.entity.UserAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;
import java.util.Optional;

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
    void saveTest() {
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
    void findTest() {
        UserAccount userAccount = new UserAccount();
        userAccount.setName("jeremy");
        userAccount.seteMail("je@mail.fr");
        userAccount.setPassword("jeje");
        userAccount.setSolde(35000L);

        UserAccount userAccountSaved = userAccountRepository.save(userAccount);

        Optional<UserAccount> findedUserAccount = userAccountRepository.findById(userAccountSaved.getUserAccount_id());

        assertEquals("je@mail.fr", findedUserAccount.get().geteMail());
    }

    @Test
    void deleteTest() {

        UserAccount userAccount = new UserAccount();
        userAccount.setName("gerard");
        userAccount.seteMail("gerard@mail.fr");
        userAccount.setPassword("gerard1");
        userAccount.setSolde(45000L);

        userAccountRepository.delete(userAccount);

        assertEquals(0, userAccountRepository.findAll().size());
    }
}
