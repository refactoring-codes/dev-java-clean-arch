package com.example.tasks.core.usecases;

import com.example.tasks.infrastructure.InMemoryTaskRepository;
import org.junit.jupiter.api.Test;

public class ListAllTasksTest {
    @Test
    void listsAllTasksWithSomeTasks() {
        var repo = new InMemoryTaskRepository();
        var listAllUsecase = new ListAllTasks(repo);
        var createUsecase = new CreateTask(repo);

        createUsecase.execute("1", "Task 1");
        createUsecase.execute("2", "Task 2");

        var list = listAllUsecase.execute();
        assert list.size() == 2;
        assert list.get(0).getTitle().equals("Task 1");
        assert list.get(1).getTitle().equals("Task 2");
    }

    @Test
    void listsAllTasksWithNoTasks() {
        var repo = new InMemoryTaskRepository();
        var listAllUsecase = new ListAllTasks(repo);
        var list = listAllUsecase.execute();
        assert list.isEmpty();
    }
}
