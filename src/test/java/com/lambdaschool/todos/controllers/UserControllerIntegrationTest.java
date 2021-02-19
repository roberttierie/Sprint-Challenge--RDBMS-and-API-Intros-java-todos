package com.lambdaschool.todos.controllers;

import com.lambdaschool.todos.TodosApplication;
import com.lambdaschool.todos.TodosApplicationTests;
import com.lambdaschool.todos.services.UserService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = TodosApplicationTests.class)
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserControllerIntegrationTest {
    @Autowired
    WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Autowired
    UserService userService;

    @Before
    public void setUp() throws Exception {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void listAllUsers() throws Exception {
        mockMvc.perform(get("/users/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("admin")))
                .andExpect(content().string(containsString("misskitty")));
    }

    @Test
    public void getUserById() throws Exception {
        mockMvc.perform(get("/users/user/{userid}",
                1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("admin@lambdaschool.local")));
    }

    @Test
    public void addNewUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users/user")
                .content("{\"username\": \"testUser\", \"password\": \"password\", \"primaryemail\": \"test@email.com\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void z_deleteUserById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/users/user/{id}",
                1))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

//    Stretch Goal
//    @Test
//    public void getUserNameCountTodos() throws Exception
//    {
//        mockMvc.perform(get("/users/users/todos"))
//            .andDo(print())
//            .andExpect(status().isOk())
//            .andExpect(content().string(containsString("{\"usernamerpt\":\"admin\",\"counttodos\":2}")));
//    }
}