package ru.task_tracker;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ru.task_tracker.enums.TaskStatus;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@EqualsAndHashCode
public class Task {
    private String name;
    private String description;
    private TaskStatus status;
    private LocalDateTime deadline;

    public Task(String name, String description, LocalDateTime deadline) {
        this.name = name;
        this.description = description;
        this.status = TaskStatus.TODO;
        this.deadline = deadline;
    }

    public String toString() {
        return "Задача: " + name + "\nОписание: " + description + "\nСтатус: " + status + "\nСрок: " + deadline + "\n";
    }
}
