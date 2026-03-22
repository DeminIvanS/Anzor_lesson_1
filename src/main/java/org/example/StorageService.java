package org.example;

public interface StorageService {

    Record save(Record record);

    Record findById(Integer id);

    Record deleteById(Integer id);




}
