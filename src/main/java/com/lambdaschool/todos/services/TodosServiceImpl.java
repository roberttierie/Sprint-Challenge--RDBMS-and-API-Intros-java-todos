package com.lambdaschool.todos.services;


import com.lambdaschool.todos.models.Todos;
import com.lambdaschool.todos.models.User;
import com.lambdaschool.todos.repository.TodosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;


@Service(value = "todosService")
@Transactional
public class TodosServiceImpl implements TodosService {
    @Autowired
    private TodosRepository todosRepository;

    @Autowired
    UserService userService;

    @Override
    public Todos save(long userid, Todos newTodos){
        User currentUser = userService.findUserById(userid);
        Todos saveTodos = new Todos(currentUser, newTodos.getDescription());
        todosRepository.save(saveTodos);
        return saveTodos;
    }


    @Override
    public Todos markComplete(long todoid)
    {
        Todos updateTodos = todosRepository.findById(todoid).orElseThrow(() ->new EntityNotFoundException("This " + todoid + "not available"));
        updateTodos.setCompleted(true);
        return todosRepository.save(updateTodos);
    }

}
