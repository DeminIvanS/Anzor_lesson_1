package org.example;

import java.sql.SQLException;
import java.util.Map;
import java.util.stream.Collectors;

public class CommandHandler {
    private final StorageService service;

    public CommandHandler(StorageService service) {
        this.service = service;
    }

    public Result handle(Command command) throws SQLException {
        return switch (command.type()) {
            case GET -> {
                Person person = service.findById(command.id());
                yield new Result(person.toString());
            }
            case CREATE -> {
                Integer id = service.save(command.person());
                yield new Result("String saved with id = " + id);
            }
            case UPDATE -> {
                service.updateById(command.id(), command.person());
                yield new Result(String.format("String with id = %s updated", command.id()));
            }
            case DELETE -> {
                service.deleteById(command.id());
                yield new Result(String.format("String with id = %s deleted", command.id()));
            }
            case GET_ALL -> {
                Map<Integer, Person> copyStorage = service.getAllRecords();
                yield new Result(copyStorage.entrySet().stream()
                        .map(e -> e.getKey() + " - " + e.getValue().toString())
                        .collect(Collectors.joining("\n")));
            }
        };
    }
}

