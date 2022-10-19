package com.solo.service;

import com.solo.entity.Todo;
import com.solo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    public Todo createTodo(Todo todo) {
        Todo saveTodo = todoRepository.save(todo);

        return todo;
    }

    public Todo updateTodo(Todo todo) {
        Todo updateTodo = todoRepository.save(todo);

        Optional.ofNullable(todo.getTitle()).ifPresent(todo::setTitle);
        Optional.ofNullable(todo.getOrder()).ifPresent(todo::setOrder);
        Optional.ofNullable(todo.getCompleted()).ifPresent(todo::setCompleted);

        return updateTodo;
    }

    public Todo findTodo(long todoId) {
        Optional<Todo> optionalTodo = todoRepository.findById(todoId);

        Todo findTodo = optionalTodo.orElseThrow(() -> new NoSuchElementException());

        return findTodo;
    }

    public List<Todo> findAllTodos() {

        return todoRepository.findAll();
    }

    public void deleteTodo(long todoId) {

        todoRepository.deleteById(todoId);
    }

    public void deleteTodos() {

        todoRepository.deleteAll();
    }
}
