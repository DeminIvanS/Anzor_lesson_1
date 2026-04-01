package org.example;

import java.util.Map;

public interface StorageService {

    Integer save(Record record);

    Record findById(Integer id);

    Integer deleteById(Integer id);

    Integer updateById(Integer id, Record rec);

    Map<Integer, Record> getAllRecords();


}
