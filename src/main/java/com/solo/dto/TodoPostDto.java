package com.solo.dto;

import lombok.Getter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;

@Getter
public class TodoPostDto {
    @NotBlank(message = "제목은 공백이 아니어야 합니다.")
    private String title;

    @Range(min = 1)
    private int order;

    private boolean completed;
}
