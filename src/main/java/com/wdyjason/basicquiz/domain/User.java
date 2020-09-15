package com.wdyjason.basicquiz.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long id;

    @NotBlank(message = "user name is not null!")
    private String name;

    @NotNull(message = "user age is not null!")
    @Min(value = 16, message = "age should greater than 16!")
    private Long age;

    @NotBlank(message = "user avatar is not null!")
    private String avatar;

    @Size(min = 0, max = 1024, message = "description should less than 1024 bytes!")
    private String description;

}
