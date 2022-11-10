package com.openclassrooms.paymybuddy.service;

import com.openclassrooms.paymybuddy.entity.UserAcompte;
import com.openclassrooms.paymybuddy.repository.UserAcompteRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserAcompteServiceTest {

    @Autowired
    private UserAcompteService userAcompteService;

    @Autowired
    private UserAcompteRepository userAcompteRepository;

    @BeforeEach
    void cleanDb() {
        this.userAcompteRepository.deleteAll();
    }

    @Test
    void saveServiceMethodTest() {
        //ARRANGE
        UserAcompte userAcompteToSave = new UserAcompte(5000L, "alexandre", "alexlegrand@mail.com", "lepasse");
        UserAcompte userAcompteToSave1 = new UserAcompte(15000L, "jean", "jean@mail.com", "monmotdepasse");
        UserAcompte userAcompteToSave2 = new UserAcompte(3000L, "brigitte", "brigitte@mail.com", "lepassword");

        //ACT
        UserAcompte userAcompteSaved = userAcompteService.saveUserAcompte(userAcompteToSave);
        UserAcompte userAcompteSaved1 = userAcompteService.saveUserAcompte(userAcompteToSave1);
        UserAcompte userAcompteSaved2 = userAcompteService.saveUserAcompte(userAcompteToSave2);

        UserAcompte savedUserAcompte = userAcompteService.getUserAcompteById(userAcompteSaved.getId());
        UserAcompte savedUserAcompte1 = userAcompteService.getUserAcompteById(userAcompteSaved1.getId());
        //ASSERT
        assertEquals("lepasse", savedUserAcompte.getPassword());
        assertEquals("jean", savedUserAcompte1.getName());
        assertEquals("brigitte@mail.com", userAcompteSaved2.geteMail());

    }

    @Test
    void updateServiceMethodTest() {

        //ARRANGE
        UserAcompte userAcompte = new UserAcompte(5000L, "alexandre", "alexlegrand@mail.com", "lepasse");

        UserAcompte updatedUser = new UserAcompte(600L, "alexandre", "jeannewmail@mail.com", "newmotdepasse");

        UserAcompte userAcompteSaved = userAcompteService.saveUserAcompte(userAcompte);
        //ACT
        UserAcompte userAcompteUpdated = userAcompteService.updateUserAcompte(updatedUser, userAcompteSaved.getId());
        //ASSERT
        assertEquals("jeannewmail@mail.com", userAcompteService.getUserAcompteById(userAcompteUpdated.getId()).geteMail());

    }

    @Test
    void deleteServiceMethodTest() {
        //ARRANGE
        UserAcompte userAcompteToSave = new UserAcompte(5000L, "alexandre", "alexlegrand@mail.com", "lepasse");

        UserAcompte userAcompteSaved = userAcompteService.saveUserAcompte(userAcompteToSave);
        //ACT
        userAcompteService.deleteUserAcompte(userAcompteSaved.getId());
        //ASSERT
        assertEquals(0, userAcompteRepository.findAll().size());
    }

    @Test
    void findAllUserAcompteServiceTest(){
        UserAcompte userAcompte1 = new UserAcompte(5000L, "alexandre", "alexlegrand@mail.com", "lepasse");

        UserAcompte userAcompte2 = new UserAcompte(600L, "alexandre", "jeannewmail@mail.com", "newmotdepasse");

        userAcompteService.saveUserAcompte(userAcompte1);
        userAcompteService.saveUserAcompte(userAcompte2);
        userAcompteService.findAllUserAcomptes();

        assertEquals(2,userAcompteRepository.findAll().size());
    }

}
