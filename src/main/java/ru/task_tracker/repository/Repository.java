package ru.task_tracker.repository;

import ru.task_tracker.Task;
import ru.task_tracker.enums.TaskStatus;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Repository {
    private final Map<String, Task> tasks;

    public Repository() {
        tasks = new HashMap<>();
    }

    public Task get(String taskName) {
        return tasks.get(taskName);
    }

    public Task add(Task task) {
        return tasks.putIfAbsent(task.getName(), task);
    }

    public Task delete(String taskName) {
        return tasks.remove(taskName);
    }

    public void setDescription(String name, String description) {
        tasks.get(name).setDescription(description);
    }

    public void setDeadline(String name, LocalDateTime deadline) {
        tasks.get(name).setDeadline(deadline);
    }

    public void setStatus(String name, TaskStatus status) {
        tasks.get(name).setStatus(status);
    }

    public Task setName(String name, String newName) {
        Task task = tasks.get(name);
        tasks.remove(name);
        task.setName(newName);
        return add(task);
    }

    public Collection<Task> list() {
        return tasks.values();
    }
}
