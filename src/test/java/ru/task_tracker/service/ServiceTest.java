package ru.task_tracker.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.task_tracker.Task;
import ru.task_tracker.enums.TaskStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {
    Service service;
    String name;
    String description;
    String deadline;
    Task task;

    String newName;
    String newDescription;
    String newDeadline;
    String newStatus;
    Task editedTask;

    Map<String, String> params;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm");

    @BeforeEach
    void setUp() {
        service = new Service();

        name = "Name";
        description = "test description";
        deadline = "2025-05-01 12:00";
        task = new Task(name, description, LocalDateTime.parse(deadline, formatter));

        newName = "newName";
        newDescription = "new description";
        newDeadline = "2025-05-01 14:00";
        newStatus = "in_progress";

        params = new HashMap<>();
        params.put("-n", newName);
        params.put("-d", newDescription);
        params.put("-dl", newDeadline);
        params.put("-s", newStatus);

        editedTask = new Task(newName, newDescription, LocalDateTime.parse(newDeadline, formatter));
        editedTask.setStatus(TaskStatus.valueOf(newStatus.toUpperCase()));
    }

    @Test
    void add() {
        Task result = service.add(name, description, deadline);
        assertEquals(task, result);
    }

    @Test
    void addAgain() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.add(name, description, deadline);
            service.add(name, description, deadline);
        });
    }

    @Test
    void list() {
        service.add(name, description, deadline);
        List<Task> list = List.of(task);
        assertIterableEquals(list, service.list());
    }

    @Test
    void edit() {
        service.add(name, description, deadline);



        assertEquals(editedTask, service.edit(name, params));
    }

    @Test
    void delete() {
        service.add(name, description, deadline);
        assertEquals(task, service.delete(name));
        assertEquals(Collections.EMPTY_LIST, service.list());
    }

    @Test
    void filter() {
        service.add(name, description, deadline);
        service.add(newName, newDescription, newDeadline);

        service.edit(newName, params);

        List<Task> todo = service.filter("todo");
        List<Task> inProgress = service.filter("in_progress");

        assertEquals(1, todo.size());
        assertEquals(1, inProgress.size());
        assertIterableEquals(List.of(task), todo);
        assertIterableEquals(List.of(editedTask), inProgress);
    }

    @Test
    void sort() {
        service.add(name, description, deadline);
        service.add(newName, newDescription, newDeadline);

        service.edit(newName, params);

        List<Task> sorted = service.sort();

        assertEquals(task, sorted.get(0));
        assertEquals(editedTask, sorted.get(1));
    }
}