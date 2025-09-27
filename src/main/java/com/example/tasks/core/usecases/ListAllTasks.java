package com.example.tasks.core.usecases;

import com.example.tasks.application.TaskRepository;
import com.example.tasks.core.entities.Task;

import java.util.List;

public class ListAllTasks {
    private final TaskRepository repo;

    public ListAllTasks(TaskRepository repo) {
        this.repo = repo;
    }

    public List<Task> execute() {
        return repo.findAll();
    }
}
