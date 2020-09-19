package com.wdyjason.basicquiz.service;

import com.wdyjason.basicquiz.domain.Education;
import com.wdyjason.basicquiz.domain.User;
import com.wdyjason.basicquiz.exception.UserNotFoundException;
import com.wdyjason.basicquiz.repository.EducationRepository;
import com.wdyjason.basicquiz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    // GTB: - 不需要写这个 @Autowired 了
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

    public User findOneUser(Long userId) throws UserNotFoundException {
        Optional<User> findResult = userRepository.findOneById(userId);
        return findResult.orElseThrow(UserNotFoundException::new);
    }

    public Education saveEducation(Long userId, Education receivedEdu) throws UserNotFoundException {
        findOneUser(userId);
        receivedEdu.setUserId(userId);
        return educationRepository.save(receivedEdu);
    }

    public List<Education> findUserEducations(long userId) {
        // GTB: + 按 year 做了升序，不错！
        return educationRepository.findByUserId(userId).stream()
                .sorted(Comparator.comparing(Education::getYear))
                .collect(Collectors.toList());
    }
}
