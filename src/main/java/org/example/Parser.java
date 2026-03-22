package org.example;

public class Parser {
    Record record;

    Validator validator = new Validator();


    public Command parse(String string) {
        Command command = command(string);
        System.out.println(command);
        return command;
    }


    public String[] getSplit(String string){
        String[] strings = string.split(" ");
        return strings;
    }
    public String[] getSplitOnTwo(String string){
        String[] strings = string.split(" ",2);
        return strings;
    }

    private Command command(String string) {
        String[] words = getSplit(string);
        if(words.length==1 && "GET".equals(words[0])){
            return parseGetAll(string);
        }
        String command = getSplitOnTwo(string)[0];
        return switch (command.toUpperCase()) {
            case "GET" -> parseGet(string);
            case "UPDATE" -> parseUpdate(string);
            case "DELETE" -> parseDelete(string);
            case "CREATE" -> parseCreate(string);
            default -> throw new RuntimeException("Wrong string");
        };

    }

    private boolean isInt(String string){
        try {
            stringToInt(string);
                return true;
            } catch (Exception e){
                return false;
        }
    }
    private Integer stringToInt(String string){
        return Integer.parseInt(string);

    }

    private Command parseGet(String input) {
        validator.validate(input);
        getSplit(input);
        return new Command(1, CommandType.GET);
    }

    private Command parseGetAll(String string) {
        return new Command(CommandType.GET_ALL);
    }


    private Command parseCreate(String input) {
        validator.validate(input);
        String text = getSplitOnTwo(input)[1];
        return new Command(2, new Record(text), CommandType.CREATE);
    }

    private Command parseUpdate(String input) {
        validator.validate(input);
        String[] commandAndString = getSplitOnTwo(input);
        String[] idAndString = getSplitOnTwo(commandAndString[1]);
        Integer id = null;
        if(isInt(idAndString[0])){
            id = stringToInt(idAndString[0]);
        }
        String text = idAndString[1];
        return new Command(id, new Record(text), CommandType.UPDATE);
    }

    private Command parseDelete(String input) {
        validator.validate(input);
        String[] commandAndString = getSplitOnTwo(input);
        Integer id = null;
        if(isInt(commandAndString[1])){
            id = stringToInt(getSplitOnTwo(input)[1]);
        }


        return new Command(id, CommandType.DELETE);
    }

}
