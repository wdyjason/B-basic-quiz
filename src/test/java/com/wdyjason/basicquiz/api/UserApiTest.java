package com.wdyjason.basicquiz.api;

import com.wdyjason.basicquiz.domain.Education;
import com.wdyjason.basicquiz.domain.User;
import com.wdyjason.basicquiz.exception.UserNotFoundException;
import com.wdyjason.basicquiz.repository.EducationRepository;
import com.wdyjason.basicquiz.repository.UserRepository;
import com.wdyjason.basicquiz.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserApi.class)
@AutoConfigureJsonTesters
class UserApiTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private JacksonTester<User> userJson;

    @Autowired
    private JacksonTester<Education> eduJson;

    @AfterEach
    public void afterEach() {
        Mockito.reset(userService);
    }

    public User genUser() {
        return User.builder()
                .id(1L)
                .name("test")
                .age(18L)
                .avatar("url")
                .description("des")
                .build();
    }

    public Education genEdu() {
        return Education.builder()
                .userId(1L)
                .year(2020L)
                .title("title")
                .description("des")
                .build();
    }

    @Nested
    class UserAbout {

        @Nested
        class happyPath {
           @Test
           public void should_create_user_successful() throws Exception {
               User toSaveUser = genUser();
               when(userService.saveUser(toSaveUser)).thenReturn(1L);
               mockMvc.perform(post("/users").content(userJson.write(toSaveUser).getJson()).contentType(MediaType.APPLICATION_JSON))
                       .andExpect(content().string("1"))
                       .andExpect(status().isCreated());
           }

           @Test
           public void should_get_a_user_successful() throws Exception {
               User toReturn = genUser();
               when(userService.findOneUser(1L)).thenReturn(toReturn);
               mockMvc.perform(get("/users/{id}", 1L))
                       .andExpect(jsonPath("$.id", is(1)))
                       .andExpect(jsonPath("$.name", is("test")))
                       .andExpect(jsonPath("$.age", is(18)))
                       .andExpect(jsonPath("$.avatar", is("url")))
                       .andExpect(jsonPath("$.description", is("des")))
                       .andExpect(status().isOk());
           }
        }

        @Nested
        class sadPath {

           @Test
           public void should_create_user_Failed() throws Exception {
               User toSaveUser = new User();
               mockMvc.perform(post("/users").content(userJson.write(toSaveUser).getJson()).contentType(MediaType.APPLICATION_JSON))
                       .andExpect(status().isInternalServerError());
           }

           @Test
           public void should_get_a_user_Failed() throws Exception {
               when(userService.findOneUser(4L)).thenThrow(new UserNotFoundException());
               mockMvc.perform(get("/users/{id}", 4L))
                       .andExpect(status().isNotFound());
           }
        }
    }

    @Nested
    class EducationAbout {

        @Nested
        class happyPath {

            @Test
            public void should_create_education_success() throws Exception {
                Education toSaveEdu = genEdu();
                when(userService.saveEducation(1L, toSaveEdu)).thenReturn(toSaveEdu);
                mockMvc.perform(post("/users/{id}/educations", 1L).content(eduJson.write(toSaveEdu).getJson()).contentType(MediaType.APPLICATION_JSON))
                        .andExpect(jsonPath("$.userId", is(1)))
                        .andExpect(jsonPath("$.title", is("title")))
                        .andExpect(jsonPath("$.year", is(2020)))
                        .andExpect(jsonPath("$.description", is("des")))
                        .andExpect(status().isCreated());
            }

            @Test
            public void should_get_educations_success() throws Exception {
                Education toReturn = genEdu();
                when(userService.findUserEducations(1L)).thenReturn(Collections.singletonList(toReturn));
                mockMvc.perform(get("/users/{id}/educations", 1L))
                        .andExpect(jsonPath("$", hasSize(1)))
                        .andExpect(jsonPath("$[0].userId", is(1)))
                        .andExpect(jsonPath("$[0].title", is("title")))
                        .andExpect(jsonPath("$[0].year", is(2020)))
                        .andExpect(jsonPath("$[0].description", is("des")))
                        .andExpect(status().isOk());
            }
        }

        @Nested
        class sadPath {

            @Test
            public void should_create_education_failure_when_education_empty() throws Exception {
                Education toSaveEdu = new Education();
                mockMvc.perform(post("/users/{id}/educations", 1L).content(eduJson.write(toSaveEdu).getJson()).contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isInternalServerError());
            }

            @Test
            public void should_create_education_failure_when_user_not_exists() throws Exception {
                Education toSaveEdu = genEdu();
                when(userService.saveEducation(1L, toSaveEdu)).thenThrow(new UserNotFoundException());
                mockMvc.perform(post("/users/{id}/educations", 1L).content(eduJson.write(toSaveEdu).getJson()).contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNotFound());
            }
        }
    }
}