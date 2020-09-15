package com.wdyjason.basicquiz.repository;

import com.wdyjason.basicquiz.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserRepository {
    private final List<User> userDataSource = new ArrayList<User>();
    private static AtomicLong nextId = new AtomicLong(0); //

    public User save(User receivedUser) {
        Long newId = nextId.incrementAndGet();
        receivedUser.setId(newId);
        userDataSource.add(receivedUser);
        return receivedUser;
    }

    public void deleteAll() {
        userDataSource.clear();
        nextId.set(0);
    }

    public Optional<User> findOneById(long id) {
        for (User user : userDataSource) {
            if (user.getId() == id) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }
}
