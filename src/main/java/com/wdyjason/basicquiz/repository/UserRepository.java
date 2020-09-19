package com.wdyjason.basicquiz.repository;

import com.wdyjason.basicquiz.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findOneById(long id);
}
