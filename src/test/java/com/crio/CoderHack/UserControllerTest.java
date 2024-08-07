package com.crio.CoderHack;

import com.crio.CoderHack.entities.User;
import com.crio.CoderHack.Repository.UserRepository;
import com.crio.CoderHack.Service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();
    }

    @Test
    public void testCreateUser() throws Exception {
        User user = new User();
        user.setUserId("user1");
        user.setUsername("john_doe");
        user.setScore(0);
        user.setBadges(new HashSet<>());

        ResultActions resultActions = mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.userId", is("user1")))
                .andExpect(jsonPath("$.username", is("john_doe")))
                .andExpect(jsonPath("$.score", is(0)))
                .andExpect(jsonPath("$.badges").isEmpty());

        String responseContent = resultActions.andReturn().getResponse().getContentAsString();
        User createdUser = objectMapper.readValue(responseContent, User.class);

        assert createdUser != null;
    }

    @Test
    public void testGetAllUsers() throws Exception {
        User user = new User();
        user.setUserId("user1");
        user.setUsername("john_doe");
        user.setScore(0);
        user.setBadges(new HashSet<>());
        userRepository.save(user);

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].userId", is("user1")))
                .andExpect(jsonPath("$[0].username", is("john_doe")))
                .andExpect(jsonPath("$[0].score", is(0)))
                .andExpect(jsonPath("$[0].badges").isEmpty());
    }

    @Test
    public void testGetUserById() throws Exception {
        User user = new User();
        user.setUserId("user1");
        user.setUsername("john_doe");
        user.setScore(0);
        user.setBadges(new HashSet<>());
        userRepository.save(user);

        mockMvc.perform(get("/users/{userId}", "user1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId", is("user1")))
                .andExpect(jsonPath("$.username", is("john_doe")))
                .andExpect(jsonPath("$.score", is(0)))
                .andExpect(jsonPath("$.badges").isEmpty());
    }

    @Test
    public void testUpdateUserScore() throws Exception {
        User user = new User();
        user.setUserId("user1");
        user.setUsername("john_doe");
        user.setScore(0);
        user.setBadges(new HashSet<>());
        userRepository.save(user);

        Map<String, Integer> scoreMap = new HashMap<>();
        scoreMap.put("score", 45);

        mockMvc.perform(put("/users/{userId}", "user1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(scoreMap)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId", is("user1")))
                .andExpect(jsonPath("$.username", is("john_doe")))
                .andExpect(jsonPath("$.score", is(45)))
                .andExpect(jsonPath("$.badges[0]", is("Code Champ")));
    }

    @Test
    public void testDeleteUser() throws Exception {
        User user = new User();
        user.setUserId("user1");
        user.setUsername("john_doe");
        user.setScore(0);
        user.setBadges(new HashSet<>());
        userRepository.save(user);

        mockMvc.perform(delete("/users/{userId}", "user1"))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/users/{userId}", "user1"))
                .andExpect(status().isNotFound());
    }
}
