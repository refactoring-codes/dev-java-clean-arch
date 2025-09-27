package com.example.tasks.core.usecases;

import com.example.tasks.application.TaskRepository;

public class ToggleTaskCompletion {
    private final TaskRepository repo;

    public ToggleTaskCompletion(TaskRepository repo) {
        this.repo = repo;
    }

    public void execute(String id) {
        var optional_task = repo.findById(id);
        if (optional_task.isPresent()) {
            var task = optional_task.get();
            task.toggleCompleted();
            repo.save(task);
        } else {
            throw new IllegalArgumentException("Task with id " + id + " not found");
        }
    }
}
