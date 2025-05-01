package ru.task_tracker.service;

import ru.task_tracker.enums.TaskStatus;
import ru.task_tracker.repository.Repository;
import ru.task_tracker.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Service {
    private final Repository repository;

    public Service() {
        repository = new Repository();
    }

    public Task add(String name, String description, String deadline) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm");
        Task task = new Task(name, description, LocalDateTime.parse(deadline, formatter));
        Task result = repository.add(task);
        if (result != null) {
            throw new IllegalArgumentException("Задача с таким названием уже существует:\n" + result);
        } else {
            return task;
        }
    }

    public List<Task> list() {
        Collection<Task> list = repository.list();
        return new ArrayList<Task>(list);
    }

    public Task edit(String taskName, Map<String, String> args) {
        if (repository.get(taskName) == null) {
            throw new IllegalArgumentException("Задачи с таким названием не существует");
        }

        String newName = args.get("-n");
        String newDescription = args.get("-d");
        String newDeadline = args.get("-dl");
        String newStatus = args.get("-s");

        if (newDescription != null) {
            repository.setDescription(taskName, newDescription);
        }
        if (newDeadline != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm");
            repository.setDeadline(taskName, LocalDateTime.parse(newDeadline, formatter));
        }
        if (newStatus != null) {
            repository.setStatus(taskName, TaskStatus.valueOf(newStatus.toUpperCase()));
        }
        if (newName != null) {
            Task result = repository.setName(taskName, newName);
            if (result != null) {
                throw new IllegalArgumentException("Задача с таким названием уже существует:\n" + result);
            } else {
                return repository.get(newName);
            }
        }
        return repository.get(taskName);
    }

    public Task delete(String taskName) {
        return repository.delete(taskName);
    }

    public List<Task> filter(String status) {
        return repository.list().stream().filter(t -> t.getStatus() == TaskStatus.valueOf(status.toUpperCase())).toList();
    }

    public List<Task> sort() {
        return repository.list().stream().sorted(Comparator.comparing(Task::getDeadline)).toList();
    }
}
