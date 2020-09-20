package com.wdyjason.basicquiz.repository;

import com.wdyjason.basicquiz.domain.User;
import com.wdyjason.basicquiz.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    Optional<UserEntity> findOneById(long id);
}
