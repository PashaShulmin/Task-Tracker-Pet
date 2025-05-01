package ru.task_tracker.enums;

public enum TaskStatus {
    TODO("todo"), IN_PROGRESS("in progress"), DONE("done");

    private final String status;

    TaskStatus(String status) {
        this.status = status;
    }

    public String get() {
        return status;
    }
}
