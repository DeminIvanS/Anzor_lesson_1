package org.example.storage;

import org.example.model.Person;

import java.sql.SQLException;
import java.util.Map;

public interface StorageService {

    Integer save(Person record) throws SQLException;

    Person findById(Integer id);

    Integer deleteById(Integer id);

    Integer updateById(Integer id, Person rec);

    Map<Integer, Person> getAllRecords();

}
