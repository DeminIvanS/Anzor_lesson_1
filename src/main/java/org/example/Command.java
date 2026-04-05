package org.example;

import java.util.Objects;

public class Command {
    private final Integer id;
    private final Record record;
    private final CommandType type;

    public Command(Integer id, Record record, CommandType type) {
        this.id = id;
        this.record = record;
        this.type = type;
    }

    public Command(CommandType type) {
        this.id = null;
        this.record = null;
        this.type = type;
    }

    public Command(Integer id, CommandType type) {
        this.id = id;
        this.record = null;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public Record getRecord() {
        return record;
    }

    public CommandType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Command command = (Command) o;
        return Objects.equals(id, command.id) && Objects.equals(record, command.record) && type == command.type;
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(record);
        result = 31 * result + Objects.hashCode(type);
        return result;
    }

    @Override
    public String toString() {
        return "Command{" +
                "id=" + id +
                ", record=" + record +
                ", type=" + type +
                '}';
    }
}
