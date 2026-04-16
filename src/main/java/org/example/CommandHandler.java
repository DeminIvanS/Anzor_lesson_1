package org.example;

import java.util.Map;
import java.util.stream.Collectors;

public class CommandHandler {
    private final StorageService service;

    public CommandHandler(StorageService service) {
        this.service = service;
    }

    public Result handle(Command command) {
        return switch (command.getType()) {
            case GET -> {
                Person person = service.findById(command.getId());
                yield new Result(person.toString());
            }
            case CREATE -> {
                Integer id = service.save(command.getPerson());
                yield new Result("String saved with id = " + id);
            }
            case UPDATE -> {
                service.updateById(command.getId(), command.getPerson());
                yield new Result(String.format("String with id = %s updated", command.getId()));
            }
            case DELETE -> {
                service.deleteById(command.getId());
                yield new Result(String.format("String with id = %s deleted", command.getId()));
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

