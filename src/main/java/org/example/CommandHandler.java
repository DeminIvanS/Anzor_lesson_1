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
                Person record = service.findById(command.getId());
                yield new Result(record.getName());
            }
            case CREATE -> {
                Integer id = service.save(command.getRecord());
                yield new Result("String saved with id = " + id);
            }
            case UPDATE -> {
                service.updateById(command.getId(), command.getRecord());
                yield new Result(String.format("String with id = %s updated", command.getId()));
            }
            case DELETE -> {
                service.deleteById(command.getId());
                yield new Result(String.format("String with id = %s deleted", command.getId()));
            }
            case GET_ALL -> {
                Map<Integer, Person> copyStorage = service.getAllRecords();
                yield new Result(copyStorage.entrySet().stream()
                        .map(e -> e.getKey() + " - " + e.getValue().getName())
                        .collect(Collectors.joining("\n")));
            }
        };
    }
}

