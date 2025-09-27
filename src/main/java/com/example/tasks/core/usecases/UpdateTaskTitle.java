package com.example.tasks.core.usecases;

import com.example.tasks.application.TaskRepository;
import com.example.tasks.core.entities.Task;

public class UpdateTaskTitle {
    private final TaskRepository repo;

    public UpdateTaskTitle(TaskRepository repo) {
        this.repo = repo;
    }

    public void execute(String id, String newTitle) {
        if (newTitle == null || newTitle.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        var optional_task = repo.findById(id);
        if (optional_task.isPresent()) {
            Task task = optional_task.get();
            task.setTitle(newTitle);
            repo.save(task);
        }
        else {
            throw new IllegalArgumentException("Task with id " + id + " not found");
        }
    }
}
