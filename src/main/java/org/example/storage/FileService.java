package org.example.storage;

import org.example.model.Person;

import java.util.Map;

public interface FileService {
    void write(Map<Integer, Person> allRecords);

    Map<Integer, Person> read();
}
