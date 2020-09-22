package com.wdyjason.basicquiz.repository;

import com.wdyjason.basicquiz.entity.EducationEntity;
import com.wdyjason.basicquiz.entity.UserEntity;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class EducationRepositoryTest {

    @Autowired
    private EducationRepository educationRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void should_find_by_user_id_success() {
        entityManager.persist(UserEntity.builder().build());

        entityManager.persist(EducationEntity.builder()
                .user(UserEntity.builder().id(1L).build())
                .description("des")
                .title("title")
                .build());

        List<EducationEntity> result = educationRepository.findByUserId(1L);

        assertEquals(1, result.size());
        assertEquals(EducationEntity.builder()
                .id(1L)
                .user(UserEntity.builder().id(1L).build())
                .description("des")
                .title("title")
                .build(), result.get(0));
    }

}