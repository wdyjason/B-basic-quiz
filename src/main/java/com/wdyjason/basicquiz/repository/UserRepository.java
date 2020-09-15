package com.wdyjason.basicquiz.repository;

import com.wdyjason.basicquiz.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class UserRepository {
    private final List<User> userDataSource = new ArrayList<User>();
    private static AtomicInteger maxId = new AtomicInteger(0); //

    public User save(User receivedUser) {
        Long newId = (long) maxId.incrementAndGet();
        receivedUser.setId(newId);
        userDataSource.add(receivedUser);
        return receivedUser;
    }

    public void deleteAll() {
        userDataSource.clear();
        maxId.set(0);
    }
}
