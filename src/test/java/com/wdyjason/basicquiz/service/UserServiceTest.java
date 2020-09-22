package com.wdyjason.basicquiz.service;

import com.wdyjason.basicquiz.domain.Education;
import com.wdyjason.basicquiz.domain.User;
import com.wdyjason.basicquiz.entity.UserEntity;
import com.wdyjason.basicquiz.exception.UserNotFoundException;
import com.wdyjason.basicquiz.repository.EducationRepository;
import com.wdyjason.basicquiz.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.wdyjason.basicquiz.utils.Domain2Entity.fromEdu;
import static com.wdyjason.basicquiz.utils.Domain2Entity.fromUser;
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

    @Nested
    class UserAbout {

        @Nested
        class happyPath {

            @Test
            public void should_create_user_successfully() {
                User receivedUser = User.builder()
                        .name("test")
                        .avatar("url")
                        .age(18L)
                        .description("des")
                        .build();

                User returnedUser = User.builder()
                        .id(1L)
                        .name("test")
                        .age(18L)
                        .avatar("url")
                        .description("des")
                        .build();

                when(userRepository.save(fromUser(receivedUser))).thenReturn(fromUser(returnedUser));

                Long result = userService.saveUser(receivedUser);

                assertEquals(result, 1L);
            }

            @Test
            public void should_get_a_user_successfully() throws UserNotFoundException {
                Long userId = 1L;

                User returnedUser = User.builder()
                        .id(1L)
                        .age(18L)
                        .name("test")
                        .avatar("url")
                        .description("des")
                        .build();

                User expectedUser = User.builder()
                        .id(userId)
                        .age(18L)
                        .name("test")
                        .avatar("url")
                        .description("des")
                        .build();
                when(userRepository.findOneById(userId)).thenReturn(Optional.of(fromUser(returnedUser)));

                User result = userService.findOneUser(userId);

                assertEquals(expectedUser, result);
            }
        }

        @Nested
        class sadPath {

            @Test
            public void should_get_a_user_failed_when_user_not_found() {
                when(userRepository.findOneById(1L)).thenReturn(Optional.empty());
                assertThrows(UserNotFoundException.class, () ->userService.findOneUser(1L));

            }
        }
    }

    @Nested
    class EducationAbout {

        @Nested
        class happyPath {

            @Test
            public void shouldCreateEducationSuccess() throws UserNotFoundException {
                Education receivedEdu = Education.builder()
                        .year(2020L)
                        .title("title")
                        .description("des")
                        .build();

                Education toSavedEdu = Education.builder()
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
                UserEntity constraintUser = UserEntity.builder().id(1L).build();
                when(userRepository.findOneById(1L)).thenReturn(Optional.of(constraintUser));
                when(educationRepository.save(fromEdu(toSavedEdu, constraintUser))).thenReturn(fromEdu(returnedEdu, constraintUser));

                Education result = userService.saveEducation(1L, receivedEdu);
                assertEquals(result, expectEdu);
            }

            @Test
            public void shouldGetEducationsSuccess() {
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

                UserEntity constraintUser = UserEntity.builder().id(1L).build();

                when(educationRepository.findByUserId(1L)).thenReturn(Arrays.asList(fromEdu(returnedEdu, constraintUser)));

                List<Education> result = userService.findUserEducations(1L);

                assertEquals(Arrays.asList(expectEdu), result);

            }
        }

        @Nested
        class sadPath {

            @Test
            public void should_save_educations_failed_when_user_not_found() {
                when(userRepository.findOneById(1L)).thenReturn(Optional.empty());
                assertThrows(UserNotFoundException.class, () ->userService.saveEducation(1L, new Education()));
            }
        }
    }
}