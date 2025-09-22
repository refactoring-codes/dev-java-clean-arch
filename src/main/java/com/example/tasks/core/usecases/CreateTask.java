package com.example.tasks.core.usecases;

import com.example.tasks.application.TaskRepository;
import com.example.tasks.core.entities.Task;

public class CreateTask {
    private final TaskRepository repo;

    public CreateTask(TaskRepository repo) {
        this.repo = repo;
    }

    public Task execute(String id, String title) {
        Task task = new Task(id, title);
        repo.save(task);
        return task;
    }
}
