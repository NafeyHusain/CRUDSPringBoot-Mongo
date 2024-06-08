package com.nafey.mongodb.tutor.spring_boot_mongo_db.service;

import com.nafey.mongodb.tutor.spring_boot_mongo_db.exception.TodoCollectionException;
import com.nafey.mongodb.tutor.spring_boot_mongo_db.model.TodoDTO;
import jakarta.validation.ConstraintViolationException;

import java.util.List;

public interface TodoService {
    public void createTodo(TodoDTO todoDTO) throws ConstraintViolationException, TodoCollectionException;
    public List<TodoDTO> getAllTodos();

    public void updateTodo(String id, TodoDTO todo) throws TodoCollectionException;
    public TodoDTO getSingleTodo(String id) throws TodoCollectionException;
    public void deleteTodoById(String id) throws TodoCollectionException;
}
