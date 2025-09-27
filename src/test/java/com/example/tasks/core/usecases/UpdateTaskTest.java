package com.example.tasks.core.usecases;

import com.example.tasks.infrastructure.InMemoryTaskRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UpdateTaskTest {
    @Test
    void updatesTaskWithValidTitle(){
        var repo = new InMemoryTaskRepository();
        var createUsecase = new CreateTask(repo);
        var updateUsecase = new UpdateTaskTitle(repo);

        var task = createUsecase.execute("1", "Initial Title");
        updateUsecase.execute("1", "Update Task");
        assertEquals("Update Task", repo.findById("1").orElseThrow().getTitle());

        updateUsecase.execute("1", "Updated Title");
        var updatedTask = repo.findById("1").orElseThrow();
        assertEquals("Updated Title", updatedTask.getTitle());

        updateUsecase.execute(updatedTask.getId(), "Another Update");
        assertEquals("Another Update", repo.findById("1").orElseThrow().getTitle());

        updateUsecase.execute(task.getId(), "Last Update");
        assertEquals("Last Update", repo.findById("1").orElseThrow().getTitle());
    }

    @Test
    void throwsOnUpdateNonExistentTask(){
        var repo = new InMemoryTaskRepository();
        var updateUsecase = new UpdateTaskTitle(repo);

        assertThrows(IllegalArgumentException.class, () -> {
            updateUsecase.execute("non-existent-id", "New Title");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            updateUsecase.execute(null, "New Title");
        });
    }

    @Test
    void throwsOnUpdateWithEmptyTitle(){
        var repo = new InMemoryTaskRepository();
        var createUsecase = new CreateTask(repo);
        var updateUsecase = new UpdateTaskTitle(repo);

        createUsecase.execute("1", "Initial Title");
        assertThrows(IllegalArgumentException.class, () -> {
            updateUsecase.execute("1", "   ");
        });
    }
}
