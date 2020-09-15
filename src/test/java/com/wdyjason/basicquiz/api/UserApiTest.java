package com.wdyjason.basicquiz.api;

import com.wdyjason.basicquiz.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();
    }

    @Test
    public void shouldCreateUserSuccessfully() throws Exception {
        String postUser = "{\"name\": \"test\", \"avatar\":\"url\", \"description\":\"des\"}";
        mockMvc.perform(post("/users").content(postUser).contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("1"))
                .andExpect(status().isCreated());
    }
}