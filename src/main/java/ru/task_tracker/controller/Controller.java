package ru.task_tracker.controller;

import ru.task_tracker.service.Service;
import ru.task_tracker.Task;

import java.util.List;
import java.util.Map;

import static ru.task_tracker.utils.Parser.*;

public class Controller {

    private final Service service;

    public Controller() {
        service = new Service();
    }

    public Task add(String input) {
        String[] args = addGetArgs(input);
        return service.add(args[0], args[1], args[2]);
    }

    public List<Task> list() {
        return service.list();
    }

    public Task edit(String input) {
        Map<String, String> args = editGetArgs(input);
        return service.edit(args.get("name"), args);
    }

    public Task delete(String input) {
        String taskName = getArg(input, "delete");
        return service.delete(taskName);
    }

    public List<Task> filter(String input) {
        String status = getArg(input, "filter");
        return service.filter(status);
    }

    public List<Task> sort() {
        return service.sort();
    }
}
