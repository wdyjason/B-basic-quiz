package com.wdyjason.basicquiz.repository;

import com.wdyjason.basicquiz.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void should_find_one_by_id_success() throws Exception {
        entityManager.persistAndFlush(UserEntity.builder()
                .name("test")
                .age(19L)
                .avatar("url")
                .description("des")
                .build());

        Optional<UserEntity> result = userRepository.findOneById(1L);

        assertEquals(true, result.isPresent());
        assertEquals(UserEntity.builder()
                .id(1L)
                .name("test")
                .age(19L)
                .avatar("url")
                .description("des")
                .build(), result.get());
    }

}