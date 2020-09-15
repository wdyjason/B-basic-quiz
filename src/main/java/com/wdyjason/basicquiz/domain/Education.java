package com.wdyjason.basicquiz.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Education {

    @JsonIgnore
    private Long id;

    private Long userId;

    private Long year;

    private String title;

    private String description;
}
