package com.example.tasks.core.usecases;

import com.example.tasks.application.TaskRepository;

public class DeleteTask {
    private final TaskRepository repo;

    public DeleteTask(TaskRepository repo) {
        this.repo = repo;
    }

    public void execute(String id) {
        var optional_task = repo.findById(id);
        if (optional_task.isPresent()) {
            repo.delete(id);
        }
        else {
            throw new IllegalArgumentException("Task with id " + id + " not found");
        }
    }
}
