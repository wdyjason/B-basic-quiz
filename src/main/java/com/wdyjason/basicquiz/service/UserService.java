package com.wdyjason.basicquiz.service;

import com.wdyjason.basicquiz.domain.Education;
import com.wdyjason.basicquiz.domain.User;
import com.wdyjason.basicquiz.entity.UserEntity;
import com.wdyjason.basicquiz.exception.UserNotFoundException;
import com.wdyjason.basicquiz.repository.EducationRepository;
import com.wdyjason.basicquiz.repository.UserRepository;
import com.wdyjason.basicquiz.utils.Entity2Domain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.wdyjason.basicquiz.utils.Domain2Entity.fromEdu;
import static com.wdyjason.basicquiz.utils.Domain2Entity.fromUser;
import static com.wdyjason.basicquiz.utils.Entity2Domain.toEdu;
import static com.wdyjason.basicquiz.utils.Entity2Domain.toUser;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final EducationRepository educationRepository;

    public UserService(UserRepository userRepository, EducationRepository educationRepository) {
        this.userRepository = userRepository;
        this.educationRepository = educationRepository;
    }


    public Long saveUser(User receivedUser) {
        return userRepository.save(fromUser(receivedUser)).getId();
    }

    public User findOneUser(Long userId) throws UserNotFoundException {
        return toUser(findOneUserEntity(userId));
    }

    public UserEntity findOneUserEntity(Long userId) throws UserNotFoundException {
        return userRepository.findOneById(userId).orElseThrow(UserNotFoundException::new);
    }

    public Education saveEducation(Long userId, Education receivedEdu) throws UserNotFoundException {
        UserEntity user = findOneUserEntity(userId);
        receivedEdu.setUserId(userId);
        return toEdu(educationRepository.save(fromEdu(receivedEdu, user)));
    }

    public List<Education> findUserEducations(long userId) {
        // GTB: + 按 year 做了升序，不错！
        return educationRepository.findByUserId(userId).stream()
                .map(Entity2Domain::toEdu)
                .sorted(Comparator.comparing(Education::getYear))
                .collect(Collectors.toList());
    }
}
