package org.example;

import java.util.Objects;

public class Result {
    private final String message;

    public Result(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Result record = (Result) o;
        return Objects.equals(message, record.message);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(message);
    }
}
