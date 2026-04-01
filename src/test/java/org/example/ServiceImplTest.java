package org.example;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServiceImplTest {

    @Test
    public void saveTest() {
        Record record = new Record("I'm learning Java");
        Record record2 = new Record("I'm learning Java too");
        StorageServiceImpl storageService = new StorageServiceImpl();

        Integer expected1 = 0;
        Integer expected2 = 1;
        Integer num1 = storageService.save(record);
        Integer num2 = storageService.save(record2);

        assertEquals(expected1,num1);
        assertEquals(expected2,num2);

    }
    @Test
    public void findByIdTest() {
        Record record = new Record("I'm learning Java");
        Record record2 = new Record("I'm learning Java too");
        StorageServiceImpl storageService = new StorageServiceImpl();
        storageService.save(record);
        storageService.save(record2);

        Record expected1 = record;
        Record expected2 = record2;
        Record rec1 = storageService.findById(0);
        Record rec2 = storageService.findById(1);

        assertEquals(expected1,rec1);
        assertEquals(expected2,rec2);

    }
    @Test
    public void deleteTest1() {
        Record record = new Record("I'm learning Java");
        Record record2 = new Record("I'm learning Java too");
        StorageServiceImpl storageService = new StorageServiceImpl();

        Integer expected1 = 0;

        storageService.save(record);
        storageService.save(record2);

        Integer num1 = storageService.deleteById(0);
        Object expected2 = storageService.findById(0);

        assertEquals(expected1,num1);
        assertEquals(expected2,null);


    }
    @Test
    public void deleteTest2() {
        Record record = new Record("I'm learning Java");
        Record record2 = new Record("I'm learning Java too");
        StorageServiceImpl storageService = new StorageServiceImpl();

        Integer expected1 = 1;

        storageService.save(record);
        storageService.save(record2);

        Integer num1 = storageService.deleteById(1);
        Object expected2 = storageService.findById(1);
        assertEquals(expected1,num1);
        assertEquals(expected2,null);

    }

    @Test
    public void updateTest() {
        Record record = new Record("I'm learning Java");
        Record record2 = new Record("I'm learning Java too");
        Record record3 = new Record("I'm learning JavaScript");
        Record record4 = new Record("I'm tomato");
        StorageServiceImpl storageService = new StorageServiceImpl();
        storageService.save(record);
        storageService.save(record2);
        storageService.save(record3);
        storageService.save(record4);

        Integer num9 = 9;
        Integer num2 = 2;

        Integer expected1 = storageService.updateById(num9,record4);
        Integer expected2 = storageService.updateById(num2,record4);
        Record expected3 = record4;


        Record rec = storageService.findById(num9);

        Record rec2 = storageService.findById(num2);

        assertEquals(expected1,rec);
        assertEquals(expected2,num2);
        assertEquals(expected3,rec2);

    }
    @Test
    public void getAllTest() {

        Record record = new Record("I'm learning Java");
        Record record2 = new Record("I'm learning Java too");
        Record record3 = new Record("I'm learning JavaScript");
        Record record4 = new Record("I'm tomato");
        List<Record> expectedRecord = List.of(record,record2,record3,record4);
        StorageServiceImpl storageService = new StorageServiceImpl();

        storageService.save(record);
        storageService.save(record2);
        storageService.save(record3);
        storageService.save(record4);

        Map<Integer, Record> copyStorage = storageService.getAllRecords();
        int index = 0;
        for (Map.Entry<Integer, Record> entry : copyStorage.entrySet()) {
            Record expected = expectedRecord.get(index);
            Record actual = entry.getValue();

            assertEquals(expected, actual, "Ошибка в записи с ключом: " + entry.getKey());
            index++;


        }
    }
}
