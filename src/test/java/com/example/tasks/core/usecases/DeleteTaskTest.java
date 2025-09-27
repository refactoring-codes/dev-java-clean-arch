package com.example.tasks.core.usecases;

import com.example.tasks.infrastructure.InMemoryTaskRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeleteTaskTest {
    @Test
    void deletesTask() {
        var repo = new InMemoryTaskRepository();
        var createUsecase = new CreateTask(repo);
        var deleteUsecase = new DeleteTask(repo);

        createUsecase.execute("1", "Test Task");
        assert repo.findById("1").isPresent();

        deleteUsecase.execute("1");
        assert repo.findById("1").isEmpty();
    }

    @Test
    void throwsOnDeleteNonExistentTaskById() {
        var repo = new InMemoryTaskRepository();
        var createUsecase = new CreateTask(repo);
        var deleteUsecase = new DeleteTask(repo);

        assertThrows(IllegalArgumentException.class, () -> {
            deleteUsecase.execute(null);
        });

        var task = createUsecase.execute("1", "Test Task");
        assert repo.findById("1").isPresent();

        deleteUsecase.execute("1");
        assert repo.findById("1").isEmpty();

        assertThrows(IllegalArgumentException.class, () -> {
            deleteUsecase.execute(task.getId());
        });
    }

}
