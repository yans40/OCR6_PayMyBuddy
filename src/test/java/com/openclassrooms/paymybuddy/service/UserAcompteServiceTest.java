package com.openclassrooms.paymybuddy.service;

import com.openclassrooms.paymybuddy.entity.UserAcompte;
import com.openclassrooms.paymybuddy.repository.UserAcompteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserAcompteServiceTest {

    @Autowired

    private UserAcompteService userAcompteService;


    @Test
    void saveServiceMethodTest() {


        UserAcompte userAcompteToUpdate = new UserAcompte(1, 5000L, "alexandre", "alexlegrand@mail.com", "legrandalex");

        userAcompteService.updateUserAcompte(userAcompteToUpdate, 1);
        UserAcompte updatedUserAcompte = userAcompteService.getUserAcompteById(1);

        assertEquals("legrandalex",updatedUserAcompte.getPassword());
    }
}
