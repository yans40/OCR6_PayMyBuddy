package com.openclassrooms.paymybuddy.repository;

import com.openclassrooms.paymybuddy.entity.UserAcompte;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserAcompteRepositoryTest {
    @Autowired
    private UserAcompteRepository userAcompteRepository;

    @Test
    void saveMethod(){
        UserAcompte userAcompte = new UserAcompte();

        userAcompte.setName("jules");
        userAcompte.seteMail("jules@mail.fr");
        userAcompte.setPassword("jules");
        userAcompte.setSolde(2000l);

        UserAcompte userAcompteToTest = userAcompteRepository.save(userAcompte);

        assertEquals("jules",userAcompteToTest.getName());

    }

}
