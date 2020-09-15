package com.wdyjason.basicquiz.api;

import com.wdyjason.basicquiz.domain.Education;
import com.wdyjason.basicquiz.domain.User;
import com.wdyjason.basicquiz.repository.EducationRepository;
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

    @Autowired
    private EducationRepository educationRepository;

    private Long savedUserId;
    private Long savedEduId;

    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();
        educationRepository.deleteAll();
        savedUserId = userRepository.save(User.builder().name("test").avatar("url").description("des").build()).getId();
        savedEduId = educationRepository.save(Education.builder()
                .userId(savedUserId)
                .title("title")
                .year(2020L)
                .description("des")
                .build()).getId();
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
        mockMvc.perform(get("/users/{id}", savedUserId))
                .andExpect(jsonPath("$.id", is(savedUserId.intValue())))
                .andExpect(jsonPath("$.name", is("test")))
                .andExpect(jsonPath("$.avatar", is("url")))
                .andExpect(jsonPath("$.description", is("des")))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldCreateEducationSuccess() throws Exception {
        String postEdu = "{\"userId\":1, \"year\":2020, \"title\":\"title\", \"description\":\"des\"}";
        mockMvc.perform(post("/users/{id}/educations", 1).content(postEdu).contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.userId", is(1)))
                .andExpect(jsonPath("$.title", is("title")))
                .andExpect(jsonPath("$.year", is(2020)))
                .andExpect(jsonPath("$.description", is("des")))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldGetEducationsSuccess() throws Exception {
        mockMvc.perform(get("/users/{id}/educations", savedUserId))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].userId", is(savedUserId.intValue())))
                .andExpect(jsonPath("$[0].title", is("title")))
                .andExpect(jsonPath("$[0].year", is(2020)))
                .andExpect(jsonPath("$[0].description", is("des")))
                .andExpect(status().isOk());
    }
}