package org.example;

import java.util.Map;

public interface StorageService {

    Integer save(Person record);

    Person findById(Integer id);

    Integer deleteById(Integer id);

    Integer updateById(Integer id, Person rec);

    Map<Integer, Person> getAllRecords();

}
