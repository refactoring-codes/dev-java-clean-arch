package com.example.tasks.infrastructure;

import com.example.tasks.application.TaskRepository;
import com.example.tasks.core.entities.Task;
import java.util.*;

public class InMemoryTaskRepository implements TaskRepository {
    private final Map<String, Task> tasks = new HashMap<>();

    public void save(Task task) {
        tasks.put(task.getId(), task);
    }

    public Optional<Task> findById(String id) {
        return Optional.ofNullable(tasks.get(id));
    }

    public List<Task> findAll() {
        return new ArrayList<>(tasks.values());
    }

    public void delete(String id) {
        tasks.remove(id);
    }
}
