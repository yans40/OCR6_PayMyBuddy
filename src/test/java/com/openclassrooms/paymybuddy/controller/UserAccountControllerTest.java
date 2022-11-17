package com.openclassrooms.paymybuddy.controller;

import com.openclassrooms.paymybuddy.service.UserAccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc

class UserAccountControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserAccountService userAccountService;

    @Test
    void addUserAcompte() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/addUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"solde\": 100,\n" +
                        "    \"name\": \"leo\",\n" +
                        "    \"password\": \"agent007\",\n" +
                        "    \"email\": \"messi@email.com\"\n" +
                        "}"))
                .andExpect(status().isOk())
                .andDo(print());
    }

}