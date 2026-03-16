package org.example;

public class Parser {
    Record record;

    private int introducedId;

    // команда, команда + id, команда + строка, команда + id + строка
    public void parse(String string) {

        String[] commandAndTail = splitStringOnToo(string);
        if(commandAndTail.length>1) {
        String[] idOrStringAndString = splitStringOnToo(commandAndTail[1]);
            if(isInt(idOrStringAndString[0])){
                introducedId = stringToInt(idOrStringAndString[0]);
                if(idOrStringAndString.length>1){
                    record = new Record(idOrStringAndString[1]);
                }
            }else if(!idOrStringAndString[0].isBlank()){
                record = new Record(idOrStringAndString[0]);
            }
        }
        String firstWord = commandAndTail[0];
        System.out.println(command(firstWord.toUpperCase(),commandAndTail.length));

    }


    public String[] splitStringOnToo(String string){
        String[] strings = string.split(" ", 2);
        return strings;
    }

    private Command command(String command, int lengthArray) {
        if(lengthArray < 2 && "GET".equals(command)){
            return parseGetAll();
        }else{
        return switch (command.toUpperCase()) {
            case "GET" -> parseGet(introducedId);
            case "UPDATE" -> parseUpdate(introducedId, record);
            case "DELETE" -> parseDelete(introducedId);
            case "CREATE" -> parseCreate(Id.getGenerateId(), record);
            default -> throw new RuntimeException("Wrong string");
            };
        }
    }




    private boolean isInt(String string){
        try {
            stringToInt(string);
                return true;
        } finally {
            return false;
        }
    }
    private Integer stringToInt(String string){
        return Integer.parseInt(string);

    }

    private Command parseGet(Integer id) {
        return new Command(id, CommandType.GET);
    }

    private Command parseGetAll() {
        return new Command(CommandType.GET_ALL);
    }


    private Command parseCreate(Integer id, Record record) {
        return new Command(id, record, CommandType.CREATE);
    }

    private Command parseUpdate(Integer id, Record record) {
        return new Command(id, record, CommandType.UPDATE);
    }

    private Command parseDelete(Integer id) {
        return new Command(id, CommandType.DELETE);
    }

}
