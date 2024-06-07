package com.nafey.mongodb.tutor.spring_boot_mongo_db.controller;

import com.nafey.mongodb.tutor.spring_boot_mongo_db.model.TodoDTO;
import com.nafey.mongodb.tutor.spring_boot_mongo_db.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;


    @GetMapping("/todos")
    public ResponseEntity<?> getAllTodos(){
        List<TodoDTO> list=todoRepository.findAll();
        if(list.size()>0){
            return new ResponseEntity<List<TodoDTO>>(list, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No todos available ", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/createTodo")
    public ResponseEntity<?> createTodo(@RequestBody TodoDTO todoDTO){
        try{
            todoDTO.setCreatedAt(new Date(System.currentTimeMillis()));
            todoRepository.save(todoDTO);
            return new ResponseEntity<TodoDTO>(todoDTO,HttpStatus.CREATED);
        }catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/fetchTodo/{id}")
    public ResponseEntity<?> getSingleTodo(@PathVariable("id") String id){
        Optional<TodoDTO> todoDTO=todoRepository.findById(id);
        if(todoDTO.isPresent()){
            return new ResponseEntity<TodoDTO>(todoDTO.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No todos available for this Id ", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateTodo/{id}")
    public ResponseEntity<?> updateSingleTodo(@PathVariable("id") String id,@RequestBody TodoDTO todoDTO){
        Optional<TodoDTO> todoDTOOptional=todoRepository.findById(id);
        if(todoDTOOptional.isPresent()){
            TodoDTO todoDTO1 = todoDTOOptional.get();
            todoDTO1.setCompleted(todoDTO.getCompleted() != null ? todoDTO.getCompleted() :todoDTO1.getCompleted());
            todoDTO1.setTodo(todoDTO.getTodo()!=null ? todoDTO.getTodo() : todoDTO1.getTodo());
            todoDTO1.setDescription(todoDTO.getDescription()!=null ? todoDTO.getDescription() :todoDTO1.getDescription());
            todoDTO1.setUpdatedAt(new Date(System.currentTimeMillis()));
            todoRepository.save(todoDTO1);
            return new ResponseEntity<TodoDTO>(todoDTO1, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No todos available for this Id ", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteTodo/{id}")
    public ResponseEntity<?> deleteSingleTodo(@PathVariable("id") String id){
        try{
            todoRepository.deleteById(id);
            return new ResponseEntity<>("SuccessFully deleted with id " + id, HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
