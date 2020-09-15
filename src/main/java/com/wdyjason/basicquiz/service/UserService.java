package com.wdyjason.basicquiz.service;

import com.wdyjason.basicquiz.repository.EducationRepository;
import com.wdyjason.basicquiz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


}
