package com.example.tasks.core.usecases;

import com.example.tasks.infrastructure.InMemoryTaskRepository;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CreateTaskTest {
    @Test
    void createsTaskWithValidTitle() {
        var repo = new InMemoryTaskRepository();
        var usecase = new CreateTask(repo);
        var task = usecase.execute("1", "Test Task");
        assertEquals("Test Task", task.getTitle());
        assertFalse(task.isCompleted());
    }

    @Test
    void throwsOnEmptyTitle() {
        var repo = new InMemoryTaskRepository();
        var usecase = new CreateTask(repo);
        assertThrows(IllegalArgumentException.class, () -> {
            usecase.execute("1", "  ");
        });
    }
}
