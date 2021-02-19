package com.lambdaschool.todos.services;

import com.lambdaschool.todos.models.Todos;

public interface TodosService {
    Todos save(long userid, Todos newTodos);
    Todos markComplete(long todoid);
}