package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Parser {


    private final Validator validator = new Validator();
    //UPDATE 3 {"name":"Петя","age":16}

    public Command parse(String string) throws JsonProcessingException {
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
        if (words.length == 1 && "GET".equals(words[0].toUpperCase())) {
            return parseGetAll();
        } else {
            validator.validate(input);
            Integer id = Integer.parseInt(words[1]);
            return new Command(id, CommandType.GET);
        }
    }

    private Command parseGetAll() {
        return new Command(CommandType.GET_ALL);
    }
//UPDATE 3 {"name":"Петя","age":16}

    private Command parseCreate(String input) throws JsonProcessingException {
        Person person = null;
        String[] words = input.split(" ", 2);

        //TODO: Json -> objectMapper = Person
        if(isValidJson(words[1])){
           person = toPerson(words[1]);
        }
        return new Command(person, CommandType.CREATE);
    }

    private Command parseUpdate(String input) throws JsonProcessingException {

        String[] words = input.split(" ", 3);
        Person person = null;
        Integer id = null;
        if (isInt(words[1])) {
            id = Integer.parseInt(words[1]);
        }
        if(isValidJson(words[2])){
            person = toPerson(words[2]);
        }
        String text = words[2];
        return new Command(id, person, CommandType.UPDATE);
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


    public Person toPerson(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Person person = objectMapper.readValue(json, Person.class);

        return person;
    }
    private static boolean isValidJson(String json){
        ObjectMapper object = new ObjectMapper();
        try {
            object.readTree(json);
        }catch (JsonProcessingException e ){
            return false;
        }
        return true;
    }
}
