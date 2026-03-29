package org.example;

public class Parser {


    Validator validator = new Validator();

    public Command command(String string) {

        String command = string.split(" ", 2)[0];
        return switch (command.toUpperCase()) {
            case "GET" -> parseGet(string);
            case "UPDATE" -> parseUpdate(string);
            case "DELETE" -> parseDelete(string);
            case "CREATE" -> parseCreate(string);
            default -> throw new RuntimeException("Wrong string");
        };

    }

    private boolean isInt(String string) {
        try {
            stringToInt(string);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Integer stringToInt(String string) {
        return Integer.parseInt(string);

    }

    private Command parseGet(String input) {
        String[] words = input.split(" ");
        if (words.length == 1 && "GET".equals(words[0])) {
            return parseGetAll();
        } else {
            validator.validate(input);
            String[] commandAndId = input.split(" ");
            Integer id = Integer.parseInt(commandAndId[1]);
            return new Command(id, CommandType.GET);
        }
    }

    private Command parseGetAll() {
        return new Command(CommandType.GET_ALL);
    }


    private Command parseCreate(String input) {
        validator.validate(input);
        String text = input.split(" ", 2)[1];
        return new Command(2, new Record(text), CommandType.CREATE);
    }

    private Command parseUpdate(String input) {
        validator.validate(input);
        String[] commandAndString = input.split(" ", 2);
        String[] idAndString = commandAndString[1].split(" ", 2);
        Integer id = null;
        if (isInt(idAndString[0])) {
            id = stringToInt(idAndString[0]);
        }
        String text = idAndString[1];
        return new Command(id, new Record(text), CommandType.UPDATE);
    }

    private Command parseDelete(String input) {
        validator.validate(input);
        String[] commandAndString = input.split(" ", 2);
        Integer id = null;
        if (isInt(commandAndString[1])) {
            id = stringToInt((input.split(" ", 2))[1]);
        }
        return new Command(id, CommandType.DELETE);
    }

}
