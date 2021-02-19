package com.lambdaschool.todos.services;


import com.lambdaschool.todos.models.Todos;
import com.lambdaschool.todos.models.User;
import com.lambdaschool.todos.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;


@Service(value = "todosService")
@Transactional
public class TodosServiceImpl implements TodosService {
    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    UserService userService;

    @Override
    public Todos save(long userid, Todos newTodos){
        User currentUser = userService.findUserById(userid);
        Todos saveTodos = new Todos(currentUser, newTodos.getDescription());
        todoRepository.save(saveTodos);
        return saveTodos;
    }


    @Override
    public Todos markComplete(long todoid)
    {
        Todos updateTodos = todoRepository.findById(todoid).orElseThrow(() ->new EntityNotFoundException("This " + todoid + "not available"));
        updateTodos.setCompleted(true);
        return todoRepository.save(updateTodos);
    }

}
