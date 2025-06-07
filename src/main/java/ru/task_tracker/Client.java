package ru.task_tracker;

import ru.task_tracker.controller.Controller;

import java.util.*;

public class Client {

    private static final String help = """
            Программа поддерживает следующие команды:
            \tДобавление задачи - add -n <task_name> -d <description> -dl <deadline>
            \t\tЗадача создаётся в статусе "todo", deadline (срок выполнения) указывать в формате "uuuu-MM-dd HH:mm".
            \tПолучение списка задач - list
            \tРедактирование задачи - edit <task_name> -n <new_name> -d <new_description> -dl <new_deadline> -s <new_status>
            \t\tСуществующие статусы: "todo", "in_progress", "done". new_deadline (срок выполнения) указывать в формате "uuuu-MM-dd HH:mm". Можно изменять аттрибуты задачи, пользуясь соответствующими флагами, не обязательно изменять все аттрибуты.
            \tУдаление задачи - delete <task_name>
            \tФильтрация задач по статусу - filter <task_status>
            \tСортировка задач по сроку выполнения - sort
            \tВыход из ситемы - exit
            \tСписок доступных команд - help
            """;

    private final Scanner scanner = new Scanner(System.in);

    private final Controller controller = new Controller();

    public void start() {
        System.out.println(help);
        processDialog();
    }

    private void processDialog() {
        String input = scanner.nextLine();
        String command = "";
        try {
            command = getCommand(input);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }

        while (!Objects.equals(command, "exit")) {
            try {
                switch (command) {
                    case "add" -> System.out.println(controller.add(input));
                    case "list" -> System.out.println(controller.list());
                    case "edit" -> System.out.println(controller.edit(input));
                    case "delete" -> System.out.println(controller.delete(input));
                    case "filter" -> System.out.println(controller.filter(input));
                    case "sort" -> System.out.println(controller.sort());
                    case "help" -> System.out.println(help);
                    default -> System.out.println("Вы ввели несуществующую команду");
                }
                input = scanner.nextLine();
                command = getCommand(input);
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private String getCommand(String input) {
        Optional<String> optionalCommand = Arrays.stream(input.split(" ")).filter(s -> !s.isBlank()).findFirst();
        if (optionalCommand.isEmpty()) {
            throw new IllegalArgumentException("Вы ввели пустую команду");
        }
        return optionalCommand.get();
    }
}
