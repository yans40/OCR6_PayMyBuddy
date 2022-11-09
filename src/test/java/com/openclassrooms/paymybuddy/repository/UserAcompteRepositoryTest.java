package com.openclassrooms.paymybuddy.repository;

import com.openclassrooms.paymybuddy.entity.UserAcompte;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserAcompteRepositoryTest {
    @Autowired
    private UserAcompteRepository userAcompteRepository;

    @Test
    void saveMethodTest(){
        UserAcompte userAcompte = new UserAcompte();
        userAcompte.setName("alexandre");
        userAcompte.seteMail("alex@mail.fr");
        userAcompte.setPassword("legrand");
        userAcompte.setSolde(15000L);

        userAcompteRepository.save(userAcompte);
        UserAcompte userAcompteSavedInDb = userAcompteRepository.findById(userAcompte.getId()).orElseThrow(NoSuchElementException::new);

        assertEquals("alex@mail.fr", userAcompteSavedInDb.geteMail());

    }


}
