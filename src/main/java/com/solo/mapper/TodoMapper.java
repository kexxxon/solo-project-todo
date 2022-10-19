package com.solo.mapper;

import com.solo.dto.TodoPatchDto;
import com.solo.dto.TodoPostDto;
import com.solo.dto.TodoResponseDto;
import com.solo.entity.Todo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TodoMapper {
    Todo todoPostDtoToTodo(TodoPostDto todoPostDto);

    Todo todoPatchDtoToTodo(TodoPatchDto todoPatchDto);

    TodoResponseDto todoToTodoResponseDto(Todo todo);
}
