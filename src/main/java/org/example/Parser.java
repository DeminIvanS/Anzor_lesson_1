package org.example;

public class Parser {


    private final Validator validator = new Validator();

    public Command parse(String string) {
        String[] text = string.split(" ", 2);
        String command = text[0];
        validator.validate(string);
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
            Integer.parseInt(string);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Command parseGet(String input) {

        String[] words = (input.split(" "));
        if (words.length == 1 && "GET".equals(words[0])) {
            return parseGetAll();
        } else {
            validator.validate(input);
            //String[] commandAndId = input.split(input);
            Integer id = Integer.parseInt(words[1]);
            return new Command(id, CommandType.GET);
        }
    }

    private Command parseGetAll() {
        return new Command(CommandType.GET_ALL);
    }


    private Command parseCreate(String input) {

        String[] text = input.split(" ", 2);
        String string = text[1];
        return new Command(2, new Record(string), CommandType.CREATE);
    }

    private Command parseUpdate(String input) {

        String[] words = input.split(" ", 3);

        Integer id = null;
        if (isInt(words[1])) {
            id = Integer.parseInt(words[1]);
        }
        String text = words[3];
        return new Command(id, new Record(text), CommandType.UPDATE);
    }

    private Command parseDelete(String input) {
        validator.validate(input);
        String[] words = input.split(" ", 2);
        Integer id = null;
        if (isInt(words[1])) {
            id = Integer.parseInt(words[1]);
        }
        return new Command(id, CommandType.DELETE);
    }

}
