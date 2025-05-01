package ru.task_tracker;

import ru.task_tracker.controller.Controller;

import java.util.Arrays;
import java.util.IllegalFormatFlagsException;
import java.util.List;
import java.util.Scanner;

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
        try {
            String command = getCommand(input);

            switch (command) {
                case "add" -> System.out.println(controller.add(input));
                case "list" -> System.out.println(controller.list());
                case "edit" -> System.out.println(controller.edit(input));
                case "delete" -> System.out.println(controller.delete(input));
                case "filter" -> System.out.println(controller.filter(input));
                case "sort" -> System.out.println(controller.sort());
                case "exit" -> {
                    return;
                }
                default -> System.out.println("Вы ввели несуществующую команду");
            }
        } catch (IllegalFormatFlagsException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }

        processDialog();
    }

    private String getCommand(String input) {
        List<String> args = Arrays.stream(input.split(" ")).filter(s -> !s.isBlank()).toList();
        if (args.isEmpty()) {
            throw new IllegalArgumentException("Вы ввели пустую команду");
        }
        return args.get(0);
    }
}
