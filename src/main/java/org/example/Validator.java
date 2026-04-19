package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Validator {
    public void validate(String input) {
        String[] words = input.split(" ",3);
        if (words.length == 1) {
            validateGetAll(words[0]);
        } else {

            String[] args = input.split(" ");
            switch (args[0].toUpperCase()) {
                case "GET" -> validateGet(input);
                case "UPDATE" -> validateUpdate(input);
                case "DELETE" -> validateDelete(input);
                case "CREATE" -> validateCreate(input);
                default -> throw new IllegalArgumentException("Wrong command");
            }
        }
    }

    private void validateGetAll(String input) {
        if (!"GET".equals(input.toUpperCase())) {
            throw new IllegalArgumentException("Wrong command");
        }
    }

    private void validateGet(String input) {
        String[] words = input.split(" ", 3);
        if (words.length > 2) {
            throw new IllegalArgumentException("Wrong string");
        }
        if (!isInt(words[1])) {
            throw new IllegalArgumentException("Wrong id");
        }
        if (!"GET".equals(words[0].toUpperCase())) {
            throw new IllegalArgumentException("Wrong command");
        }
    }

    private void validateDelete(String input) {
        String[] words = input.split(" ", 3);
        if (words.length > 2) {
            throw new IllegalArgumentException("Wrong string");
        }
        if (!isInt(words[1])) {
            throw new IllegalArgumentException("Wrong id");
        }
        if (!"DELETE".equals(words[0].toUpperCase())) {
            throw new IllegalArgumentException("Wrong command");
        }
    }

    private void validateCreate(String input) {
        String[] words = input.split(" ", 2);
        if (words.length < 2) {
            throw new IllegalArgumentException("Wrong command");
        }
        if (!isValidJson(words[1])) {
            throw new IllegalArgumentException("It's not json");
        }
    }
    private void validateUpdate(String input) {
        String[] words = input.split(" ", 3);
        if (words.length < 3) {
            throw new IllegalArgumentException("Wrong string");
        }
        if (!isInt(words[1])) {
            throw new IllegalArgumentException("Wrong id");
        }
        if (!"UPDATE".equals(words[0].toUpperCase())) {
            throw new IllegalArgumentException("Wrong command");
        }
        if (!isValidJson(words[2])) {
            throw new IllegalArgumentException("It's not json");
        }
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

    private boolean isInt(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
