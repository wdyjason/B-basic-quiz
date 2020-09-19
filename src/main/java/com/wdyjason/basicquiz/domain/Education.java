package com.wdyjason.basicquiz.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Education {

    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;

    private Long userId;

    @NotNull(message = "year can not be null!")
    @Digits( integer = 4, fraction = 0, message = "year is illegal!")
    private Long year;

    @NotNull(message = "title can not be null!")
    private String title;

    @Size(min = 0, max = 1024, message = "description should less than 1024 bytes!")
    private String description;
}
