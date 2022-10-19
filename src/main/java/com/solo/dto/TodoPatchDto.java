package com.solo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class TodoPatchDto {
    private long todoId;

    @NotBlank(message = "제목은 공백이 아니어야 합니다.")
    private String title;

    @Range(min = 1)
    private int order;

    private boolean completed;
}
