package com.example.tasks.core.usecases;

import com.example.tasks.infrastructure.InMemoryTaskRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ToggleTaskCompletionTest {
    @Test
    void toggleTaskCompletion() {
        var repo = new InMemoryTaskRepository();
        var createUsecase = new CreateTask(repo);
        var toggleUsecase = new ToggleTaskCompletion(repo);

        var task = createUsecase.execute("1", "Test Task");
        assertFalse(task.isCompleted());

        toggleUsecase.execute("1");
        assertTrue(repo.findById("1").orElseThrow().isCompleted());

        toggleUsecase.execute(task.getId());
        assertFalse(repo.findById("1").orElseThrow().isCompleted());
    }

    @Test
    void throwsOnToggleNonExistentTask(){
        var repo = new InMemoryTaskRepository();
        var toggleUsecase = new ToggleTaskCompletion(repo);
        assertThrows(IllegalArgumentException.class, () -> {
            toggleUsecase.execute("non-existent-id");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            toggleUsecase.execute(null);
        });
    }
}
