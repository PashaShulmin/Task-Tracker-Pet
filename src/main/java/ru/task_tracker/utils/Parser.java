package ru.task_tracker.utils;

import java.util.HashMap;
import java.util.IllegalFormatFlagsException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    public static String[] addGetArgs(String input) throws IllegalFormatFlagsException {
        final Pattern PARAM_PATTERN = Pattern.compile("^add\\s+" +
                "-n\\s+(?<name>[^-]+?)\\s+" +
                "-d\\s+(?<description>[^-]+?)\\s+" +
                "-dl\\s+(?<deadline>\\d{4}-\\d{2}-\\d{2}\\s+\\d{2}:\\d{2})$"
        );

        Matcher matcher = PARAM_PATTERN.matcher(input);

        String name;
        String description;
        String deadline;

        if (matcher.matches()) {
            name = matcher.group("name").trim();
            description = matcher.group("description").trim();
            deadline = matcher.group("deadline").trim();
        } else {
            throw new IllegalFormatFlagsException("Вы задали аргументы неверно");
        }

        return new String[]{name, description, deadline};
    }

    public static Map<String, String> editGetArgs(String input) {
        final Pattern PARAM_PATTERN = Pattern.compile(
                "^edit\\s+(?<name>[^-]+?)" +
                        "-n\\s+(?<newName>[^-]+?)\\s+" +
                        "-d\\s+(?<newDescription>[^-]+?)\\s+" +
                        "-dl\\s+(?<newDeadline>\\d{4}-\\d{2}-\\d{2}\\s+\\d{2}:\\d{2})\\s+" +
                        "-s\\s+(?<newStatus>[^-]+?)$"
        );

        Map<String, String> params = new HashMap<>();

        Matcher matcher = PARAM_PATTERN.matcher(input);

        if (matcher.matches()) {
            params.put("name", matcher.group("name").trim());
            params.put("-n", matcher.group("newName").trim());
            params.put("-d", matcher.group("newDescription").trim());
            params.put("-dl", matcher.group("newDeadline").trim());
            params.put("-s", matcher.group("newStatus").trim());
        } else {
            throw new IllegalFormatFlagsException("Вы задали аргументы неверно");
        }

        return params;
    }

    public static String getArg(String input, String command) throws IllegalArgumentException {
        int index = command.length();
        if (input.length() == command.length()) {
            throw new IllegalArgumentException("Вы ввели команду без аргументов");
        }
        while (input.charAt(index) == ' ') {
            index++;
        }

        return input.substring(index);
    }

}
