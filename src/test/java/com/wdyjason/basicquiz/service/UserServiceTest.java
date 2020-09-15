package com.wdyjason.basicquiz.service;

import com.wdyjason.basicquiz.domain.User;
import com.wdyjason.basicquiz.repository.EducationRepository;
import com.wdyjason.basicquiz.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
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

        Long restult = userService.save(receivedUser);

        assertEquals(restult, 1L);
    }
}