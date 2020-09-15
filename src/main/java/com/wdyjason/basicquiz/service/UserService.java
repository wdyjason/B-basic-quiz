package com.wdyjason.basicquiz.service;

import com.wdyjason.basicquiz.domain.Education;
import com.wdyjason.basicquiz.domain.User;
import com.wdyjason.basicquiz.exception.UserNotFoundException;
import com.wdyjason.basicquiz.repository.EducationRepository;
import com.wdyjason.basicquiz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EducationRepository educationRepository;

    public UserService(UserRepository userRepository, EducationRepository educationRepository) {
        this.userRepository = userRepository;
        this.educationRepository = educationRepository;
    }


    public Long saveUser(User receivedUser) {
        return userRepository.save(receivedUser).getId();
    }

    public User findOne(Long userId) throws UserNotFoundException {
        Optional<User> findResult = userRepository.findOneById(userId);
        if (!findResult.isPresent()) {
            throw new UserNotFoundException("user not found!");
        }
        return findResult.get();
    }

    public Education saveEducation(Education receivedEdu) {
        return educationRepository.save(receivedEdu);
    }
}
