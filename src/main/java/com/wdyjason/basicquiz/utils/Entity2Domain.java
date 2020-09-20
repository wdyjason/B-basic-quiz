package com.wdyjason.basicquiz.utils;

import com.wdyjason.basicquiz.domain.Education;
import com.wdyjason.basicquiz.domain.User;
import com.wdyjason.basicquiz.entity.EducationEntity;
import com.wdyjason.basicquiz.entity.UserEntity;

public class Entity2Domain {
    public static User toUser(UserEntity entity) {
        return User.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .avatar(entity.getAvatar())
                .age(entity.getAge())
                .build();
    }

    public static Education toEdu(EducationEntity entity) {
        return Education.builder()
                .id(entity.getId())
                .description(entity.getDescription())
                .userId(entity.getUser().getId())
                .title(entity.getTitle())
                .year(entity.getYear())
                .build();
    }
}
