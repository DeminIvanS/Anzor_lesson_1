package org.example;

import java.util.Objects;

public class Command {
    private final Integer id;
    private final Person person;
    private final CommandType type;

    public Command(Integer id, Person person, CommandType type) {
        this.id = id;
        this.person = person;
        this.type = type;
    }
    public Command(Person person, CommandType type) {
        this.id = null;
        this.person = person;
        this.type = type;
    }

    public Command(CommandType type) {
        this.id = null;
        this.person = null;
        this.type = type;
    }

    public Command(Integer id, CommandType type) {
        this.id = id;
        this.person = null;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public Person getPerson() {
        return person;
    }

    public CommandType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Command command = (Command) o;
        return Objects.equals(id, command.id) && Objects.equals(person, command.person) && type == command.type;
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(person);
        result = 31 * result + Objects.hashCode(type);
        return result;
    }

    @Override
    public String toString() {
        return "Command{" +
                "id=" + id +
                ", person=" + person +
                ", type=" + type +
                '}';
    }
}
