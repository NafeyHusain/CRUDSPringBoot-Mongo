package com.nafey.mongodb.tutor.spring_boot_mongo_db.controller;

import com.nafey.mongodb.tutor.spring_boot_mongo_db.exception.TodoCollectionException;
import com.nafey.mongodb.tutor.spring_boot_mongo_db.model.TodoDTO;
import com.nafey.mongodb.tutor.spring_boot_mongo_db.repository.TodoRepository;
import com.nafey.mongodb.tutor.spring_boot_mongo_db.service.TodoService;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private TodoService todoService;

    @GetMapping("/todos")
    public ResponseEntity<?> getAllTodos(){
        List<TodoDTO> list=todoService.getAllTodos();
        if(list.size()>0){
            return new ResponseEntity<List<TodoDTO>>(list, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No todos available ", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/createTodo")
    public ResponseEntity<?> createTodo(@RequestBody TodoDTO todoDTO){
        try{
            todoService.createTodo(todoDTO);
            return new ResponseEntity<TodoDTO>(todoDTO,HttpStatus.CREATED);
        }catch (ConstraintViolationException exception){
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (TodoCollectionException exception1){
            return new ResponseEntity<>(exception1.getMessage(),HttpStatus.CONFLICT);
        }
    }
    @GetMapping("/fetchTodo/{id}")
    public ResponseEntity<?> getSingleTodo(@PathVariable("id") String id){
        try {
            return new ResponseEntity<>(todoService.getSingleTodo(id), HttpStatus.OK);
        } catch (TodoCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateTodo/{id}")
    public ResponseEntity<?> updateSingleTodo(@PathVariable("id") String id,@RequestBody TodoDTO todoDTO){
        try {
            todoService.updateTodo(id,todoDTO);
            return new ResponseEntity<>("Updated movie with id "+id+"", HttpStatus.OK);
        }
        catch(ConstraintViolationException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        catch (TodoCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteTodo/{id}")
    public ResponseEntity<?> deleteSingleTodo(@PathVariable("id") String id){
        try{
            todoService.deleteTodoById(id);
            return new ResponseEntity<>("Successfully deleted todo with id "+id, HttpStatus.OK);
        }
        catch (TodoCollectionException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
