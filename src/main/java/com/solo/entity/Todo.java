package com.solo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Todo {
    @Id @GeneratedValue
    @Column(name = "todo_id")
    private Long todoId;

    private String title;

    @Column(name = "orders")
    private Integer order;

    private Boolean completed;
}