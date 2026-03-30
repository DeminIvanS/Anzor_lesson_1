package org.example;

public interface StorageService {

    Integer save(Record record);

    Record findById(Integer id);

    Integer deleteById(Integer id);

    Integer updateById(Integer id, Record rec);

    Record getAllRecords();


}
