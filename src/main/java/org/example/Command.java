package org.example;

public class Command {
    Integer id;
    Record record;
    CommandType type;

    @Override
    public String toString() {
        return "Command{" +
                "id=" + id +
                ", record=" + record +
                ", type=" + type +
                '}';
    }


    public Command(Integer id, Record record, CommandType type) {
        this.id = id;
        this.record = record;
        this.type = type;
    }
    public Command(Record record, CommandType type) {
        this.record = record;
        this.type = type;
    }
    public Command(CommandType type) {
        this.type = type;
    }
    public Command(Integer id,CommandType type) {
        this.id = id;
        this.type = type;
    }
}
