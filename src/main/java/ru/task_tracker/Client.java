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
            \t\tСуществующие статусы: "todo", "in_progress", "done". new_deadline (срок выполнения) указывать в формате "uuuu-MM-dd HH:mm".
            \tУдаление задачи - delete <task_name>
            \tФильтрация задач по статусу - filter <task_status>
            \tСортировка задач по сроку выполнения - dsort
            \tСортировка задач по статусу - ssort
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
        String input;
        String command = "";

        while (true) {
            try {
                input = scanner.nextLine();
                command = getCommand(input);
                switch (command) {
                    case "add" -> System.out.println(controller.add(input));
                    case "list" -> System.out.println(controller.list());
                    case "edit" -> System.out.println(controller.edit(input));
                    case "delete" -> System.out.println(controller.delete(input));
                    case "filter" -> System.out.println(controller.filter(input));
                    case "dsort" -> System.out.println(controller.sortByDeadline());
                    case "ssort" -> System.out.println(controller.sortByStatus());
                    case "help" -> System.out.println(help);
                    case "exit" -> {
                        return;
                    }
                    default -> System.out.println("Вы ввели несуществующую команду");
                }
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
