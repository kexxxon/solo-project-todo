package com.solo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TodoResponseDto {
    private long todoId;
    private String title;
    private int order;
    private boolean completed;
}
