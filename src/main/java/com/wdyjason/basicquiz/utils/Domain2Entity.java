package com.wdyjason.basicquiz.utils;

import com.wdyjason.basicquiz.domain.Education;
import com.wdyjason.basicquiz.domain.User;
import com.wdyjason.basicquiz.entity.EducationEntity;
import com.wdyjason.basicquiz.entity.UserEntity;

public class Domain2Entity {
    public static UserEntity fromUser(User user) {
        return UserEntity.builder()
                .id(user.getId())
                .name(user.getName())
                .description(user.getDescription())
                .age(user.getAge())
                .avatar(user.getAvatar())
                .build();
    }

    public static EducationEntity fromEdu(Education edu, UserEntity constraintUser) {
        return EducationEntity.builder()
                .id(edu.getId())
                .year(edu.getYear())
                .title(edu.getTitle())
                .description(edu.getDescription())
                .user(constraintUser)
                .build();
    }
}
