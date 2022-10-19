package com.solo.controller;

import com.solo.dto.TodoPatchDto;
import com.solo.dto.TodoPostDto;
import com.solo.dto.TodoResponseDto;
import com.solo.entity.Todo;
import com.solo.mapper.TodoMapper;
import com.solo.service.TodoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Validated
@RestController
public class TodoController {
    private final TodoService todoService;
    private final TodoMapper mapper;

    public TodoController(TodoService todoService, TodoMapper mapper) {
        this.todoService = todoService;
        this.mapper = mapper;
    }

    /* 할 일 등록 */
    @PostMapping
    public ResponseEntity postTodo(@Valid @RequestBody TodoPostDto todoPostDto) {
        Todo todo = mapper.todoPostDtoToTodo(todoPostDto);

        Todo response = todoService.createTodo(todo);

        return new ResponseEntity<>(mapper.todoToTodoResponseDto(response), HttpStatus.OK);
    }

    /* 할 일 수정 */
    @PatchMapping("/{todo-id}")
    public ResponseEntity patchTodo(@PathVariable("todo-id") @Positive long todoId,
                                    @Valid @RequestBody TodoPatchDto todoPatchDto) {
        todoPatchDto.setTodoId(todoId);

        Todo response = todoService.updateTodo(mapper.todoPatchDtoToTodo(todoPatchDto));

        return new ResponseEntity<>(mapper.todoToTodoResponseDto(response), HttpStatus.OK);
    }

    /* 특정 할 일 조회 */
    @GetMapping("/{todo-id}")
    public ResponseEntity getTodo(@PathVariable("todo-id") @Positive long todoId) {
        Todo response = todoService.findTodo(todoId);

        return new ResponseEntity<>(mapper.todoToTodoResponseDto(response), HttpStatus.OK);
    }

    /* 전체 할 일 조회 */
    @GetMapping
    public ResponseEntity getTodos() {
        List<Todo> response = todoService.findAllTodos();

        // List<Todo> -> List<TodoResponseDto>
        List<TodoResponseDto> collect = response.stream()
                .map(todo -> mapper.todoToTodoResponseDto(todo))
                .collect(Collectors.toList());

        return new ResponseEntity<>(collect, HttpStatus.OK);
    }

    /* 특정 할 일 지우기 */
    @DeleteMapping("/{todo-id}")
    public ResponseEntity deleteTodo(@PathVariable("todo-id") @Positive long todoId) {
        todoService.deleteTodo(todoId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /* 전체 할 일 지우기 */
    @DeleteMapping
    public ResponseEntity deleteTodos() {
        todoService.deleteTodos();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
