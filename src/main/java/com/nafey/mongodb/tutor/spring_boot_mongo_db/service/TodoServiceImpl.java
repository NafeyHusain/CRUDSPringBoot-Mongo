package com.nafey.mongodb.tutor.spring_boot_mongo_db.service;

import com.nafey.mongodb.tutor.spring_boot_mongo_db.exception.TodoCollectionException;
import com.nafey.mongodb.tutor.spring_boot_mongo_db.model.TodoDTO;
import com.nafey.mongodb.tutor.spring_boot_mongo_db.repository.TodoRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService{

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public void createTodo(TodoDTO todoDTO) throws ConstraintViolationException, TodoCollectionException {
        Optional<TodoDTO> createTodoOptional=todoRepository.findByTodo(todoDTO.getTodo());
        if(createTodoOptional.isPresent()){
            throw new TodoCollectionException(TodoCollectionException.TodoAlreadyExists());
        }else{
            todoDTO.setCreatedAt(new Date(System.currentTimeMillis()));
            todoRepository.save(todoDTO);
        }
    }

    @Override
    public List<TodoDTO> getAllTodos() {
        List<TodoDTO> list=todoRepository.findAll();
        if(list.size()>0){
            return list;
        }else{
            return new ArrayList<TodoDTO>();
        }
    }

    @Override
    public TodoDTO getSingleTodo(String id) throws TodoCollectionException{
        Optional<TodoDTO> todoOptional = todoRepository.findById(id);
        if (todoOptional.isEmpty()) {
            throw new TodoCollectionException(TodoCollectionException.NotFoundException(id));
        }else {
            return todoOptional.get();
        }
    }

    @Override
    public void updateTodo(String id, TodoDTO todo) throws TodoCollectionException{
        Optional<TodoDTO> todoWithId = todoRepository.findById(id);
        Optional<TodoDTO> todoWithSameName = todoRepository.findByTodo(todo.getTodo());
        if(todoWithId.isPresent())
        {
            if(todoWithSameName.isPresent() && !todoWithSameName.get().getId().equals(id))
            {

                throw new TodoCollectionException(TodoCollectionException.TodoAlreadyExists());
            }
            TodoDTO todoToUpdate=todoWithId.get();

            todoToUpdate.setTodo(todo.getTodo());
            todoToUpdate.setDescription(todo.getDescription());
            todoToUpdate.setCompleted(todo.getCompleted());
            todoToUpdate.setUpdatedAt(new Date(System.currentTimeMillis()));
            todoRepository.save(todoToUpdate);
        }
        else
        {
            throw new TodoCollectionException(TodoCollectionException.NotFoundException(id));
        }
    }

    @Override
    public void deleteTodoById(String id) throws TodoCollectionException{
        Optional<TodoDTO> todoOptional = todoRepository.findById(id);
        if(todoOptional.isEmpty())
        {
            throw new TodoCollectionException(TodoCollectionException.NotFoundException(id));
        }
        else
        {
            todoRepository.deleteById(id);
        }

    }
}
