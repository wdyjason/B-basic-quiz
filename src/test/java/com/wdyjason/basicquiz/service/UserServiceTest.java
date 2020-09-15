package com.wdyjason.basicquiz.service;

import com.wdyjason.basicquiz.domain.Education;
import com.wdyjason.basicquiz.domain.User;
import com.wdyjason.basicquiz.exception.UserNotFoundException;
import com.wdyjason.basicquiz.repository.EducationRepository;
import com.wdyjason.basicquiz.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private EducationRepository educationRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldCreateUserSuccessfully() {
        User receivedUser = User.builder()
                .name("test")
                .avatar("url")
                .description("des")
                .build();

        User returnedUser = User.builder()
                .id(1L)
                .name("test")
                .avatar("url")
                .description("des")
                .build();

        when(userRepository.save(receivedUser)).thenReturn(returnedUser);

        Long result = userService.saveUser(receivedUser);

        assertEquals(result, 1L);
    }

    @Test
    public void shouldGetAUserSuccessfully() throws UserNotFoundException {
        Long userId = 1L;

        User returnedUser = User.builder()
                .id(1L)
                .name("test")
                .avatar("url")
                .description("des")
                .build();

        User expectedUser = User.builder()
                .id(userId)
                .name("test")
                .avatar("url")
                .description("des")
                .build();
        when(userRepository.findOneById(userId)).thenReturn(Optional.of(returnedUser));

        User result = userService.findOne(userId);

        assertEquals(expectedUser, result);
    }

    @Test
    public void shouldGetAUserFailure() {
        when(userRepository.findOneById(1L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () ->userService.findOne(1L));

    }

    @Test
    public void shouldCreateEducationSuccess() {
        Education receivedEdu = Education.builder()
                .userId(1L)
                .year(2020L)
                .title("title")
                .description("des")
                .build();

        Education returnedEdu = Education.builder()
                .id(1L)
                .userId(1L)
                .year(2020L)
                .title("title")
                .description("des")
                .build();

        Education expectEdu = Education.builder()
                .id(1L)
                .userId(1L)
                .year(2020L)
                .title("title")
                .description("des")
                .build();

        when(educationRepository.save(receivedEdu)).thenReturn(returnedEdu);

        Education result = userService.saveEducation(receivedEdu);
        assertEquals(result, expectEdu);
    }
}