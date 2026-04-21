package org.example;

import java.util.Objects;

public record Command(Integer id, Person person, CommandType type) {

    public Command(Person person, CommandType type) {
        this(null, person, type);
    }

    public Command(CommandType type) {
        this(null, null, type);
    }

    public Command(Integer id, CommandType type) {
        this(id, null, type);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Command command = (Command) o;
        return Objects.equals(id, command.id) && Objects.equals(person, command.person) && type == command.type;
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
