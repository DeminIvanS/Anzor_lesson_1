package org.example;

import java.util.Map;

public interface FileService {
    void write(Map<Integer, Person> allRecords);

    Map<Integer, Person> read();
}
