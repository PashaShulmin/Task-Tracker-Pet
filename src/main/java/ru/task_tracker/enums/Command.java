package ru.task_tracker.enums;

import java.util.Arrays;
import java.util.List;

public enum Command {
    ADD, LIST, EDIT, DELETE, FILTER, SORT, EXIT, HELP;

    public static List<String> getAllCommands() {
        return Arrays.stream(Command.values()).map(c -> c.name().toLowerCase()).toList();
    }
}
