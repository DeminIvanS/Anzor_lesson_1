package org.example;

import java.util.Objects;

public class Record {
    private String value;

    public Record(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Record record = (Record) o;
        return Objects.equals(value, record.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
