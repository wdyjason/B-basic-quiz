package com.wdyjason.basicquiz.api;

import com.wdyjason.basicquiz.domain.User;
import com.wdyjason.basicquiz.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    private Long savedId;
    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();
        savedId = userRepository.save(User.builder().name("test").avatar("url").description("des").build()).getId();
    }

    @Test
    public void shouldCreateUserSuccessfully() throws Exception {
        String postUser = "{\"name\": \"test\", \"avatar\":\"url\", \"description\":\"des\"}";
        mockMvc.perform(post("/users").content(postUser).contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("2"))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldGetAUserSuccessfully() throws Exception {
        mockMvc.perform(get("/users/{id}", savedId))
                .andExpect(jsonPath("$.id", is(savedId.intValue())))
                .andExpect(jsonPath("$.name", is("test")))
                .andExpect(jsonPath("$.avatar", is("url")))
                .andExpect(jsonPath("$.description", is("des")))
                .andExpect(status().isOk());
    }
}