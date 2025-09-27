package com.example.tasks.core.usecases;

import com.example.tasks.infrastructure.InMemoryTaskRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IntegrationTest {
    @Test
    public void test() {
        var repo = new InMemoryTaskRepository();
        var createUsecase = new CreateTask(repo);
        var updateUsecase = new UpdateTaskTitle(repo);
        var deleteUsecase = new DeleteTask(repo);
        var listUsecase = new ListAllTasks(repo);
        var toggleUsecase = new ToggleTaskCompletion(repo);

        assert listUsecase.execute().isEmpty();

        int numTasks = 5;
        for (int i = 1; i <= numTasks; i++) {
            createUsecase.execute(String.valueOf(i), "Task " + i);
        }

        var list = listUsecase.execute();
        assert list.size() == numTasks;
        assert list.get(3).getTitle().equals("Task 4");

        for (var task : list) {
            assertFalse(task.isCompleted());
        }

        updateUsecase.execute("3", "Updated Task 3");
        assertEquals("Updated Task 3", repo.findById("3").orElseThrow().getTitle());

        toggleUsecase.execute("2");
        assertTrue(repo.findById("2").orElseThrow().isCompleted());

        deleteUsecase.execute("4");
        assert repo.findById("4").isEmpty();
        assert listUsecase.execute().size() == 4;
    }
}
