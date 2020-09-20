package com.wdyjason.basicquiz.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wdyjason.basicquiz.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class EducationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = {CascadeType.DETACH}, targetEntity = UserEntity.class)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private Long year;

    private String title;

    private String description;
}