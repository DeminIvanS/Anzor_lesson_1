package org.example;

public class CommandHandler {
    private final StorageService service;

    public CommandHandler(StorageService service) {

        this.service = service;
    }

    public Result handle(Command command) {
        return switch (command.getType()) {
            case GET -> {
                Record record = service.findById(command.getId());
                yield new Result(record.getValue());
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
                Record record = service.getAllRecords();
                //TODO: реализовать метод в сервисе возвращающую все записи.
                yield new Result(record.getValue());
            }
        };

    }
}
