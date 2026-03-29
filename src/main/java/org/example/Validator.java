package org.example;

public class Validator {
    public void validate(String input) {
        String[] words = getSplit(input);
        if (words.length == 1) {
            validateGetAll(input);
        } else {

            String[] args = input.split(" ");
            switch (args[0].toUpperCase()) {
                case "GET" -> validateGet(input);
                case "UPDATE" -> validateUpdate(input);
                case "DELETE" -> validateDelete(input);
                case "CREATE" -> validateCreate(input);
                default -> throw new IllegalArgumentException("Wrong string");
            }
        }
    }

    private void validateGetAll(String input) {
        if (!"GET".equals(getSplit(input)[0].toUpperCase())) {
            throw new IllegalArgumentException("Wrong command");
        }
    }

    private String[] getSplit(String input) {
        String[] args = input.split(" ");
        return args;
    }

    private void validateGet(String input) {
        if (getSplit(input).length > 2) {
            throw new IllegalArgumentException("Wrong string");
        }
        if (!isInt(getSplit(input)[1])) {
            throw new IllegalArgumentException("Wrong id");
        }
        if (!"GET".equals(getSplit(input)[0].toUpperCase())) {
            throw new IllegalArgumentException("Wrong command");
        }
    }

    private void validateDelete(String input) {
        if (getSplit(input).length > 2) {
            throw new IllegalArgumentException("Wrong string");
        }
        if (!isInt(getSplit(input)[1])) {
            throw new IllegalArgumentException("Wrong id");
        }
        if (!"DELETE".equals(getSplit(input)[0].toUpperCase())) {
            throw new IllegalArgumentException("Wrong command");
        }
    }

    private void validateCreate(String input) {
        if (getSplit(input).length < 2) {
            throw new IllegalArgumentException("Wrong string");
        }
        if (getSplit(input)[1].isBlank()) {
            throw new IllegalArgumentException("Empty string");
        }
        if (!"CREATE".equals(getSplit(input)[0].toUpperCase())) {
            throw new IllegalArgumentException("Wrong command");
        }
    }

    private void validateUpdate(String input) {
        if (getSplit(input).length < 3) {
            throw new IllegalArgumentException("Wrong string");
        }
        if (!isInt(getSplit(input)[1])) {
            throw new IllegalArgumentException("Wrong id");
        }
        if (!"UPDATE".equals(getSplit(input)[0].toUpperCase())) {
            throw new IllegalArgumentException("Wrong command");
        }
    }

    private boolean isInt(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
