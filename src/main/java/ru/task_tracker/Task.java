package ru.task_tracker;

import lombok.Getter;
import lombok.Setter;
import ru.task_tracker.enums.TaskStatus;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(getName(), task.getName()) && Objects.equals(getDescription(), task.getDescription()) && getStatus() == task.getStatus() && Objects.equals(getDeadline(), task.getDeadline());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDescription(), getStatus(), getDeadline());
    }
}
