package org.example;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServiceImplTest {
    private Map<Integer, Person> testMap = new HashMap<>();
    private Person record = new Person("I'm learning Java");
    private Person record2 = new Person("I'm learning Java too");
    private Person record3 = new Person("I'm learning JavaScript");
    private Person record4 = new Person("I'm tomato");

    @Before
    public void init() {
        testMap.put(0, record);
        testMap.put(1, record2);
        testMap.put(2, record3);
        testMap.put(3, record4);
    }

    @After
    public void clear() {
        testMap.clear();

    }

    @Test
    public void saveTest() {

        StorageServiceImpl storageService = new StorageServiceImpl(testMap);

        Integer expected1 = 0;
        Integer expected2 = 1;
        Integer num1 = storageService.save(record);
        Integer num2 = storageService.save(record2);

        assertEquals(expected1, num1);
        assertEquals(expected2, num2);
        clear();
    }

    @Test
    public void findByIdTest() {
        init();

        StorageServiceImpl storageService = new StorageServiceImpl(testMap);

        Person rec1 = storageService.findById(0);
        Person rec2 = storageService.findById(1);
        Person expected1 = testMap.get(0);
        Person expected2 = testMap.get(1);

        assertEquals(expected1, rec1);
        assertEquals(expected2, rec2);
        clear();
    }

    @Test
    public void deleteTest1() {
        init();
        StorageServiceImpl storageService = new StorageServiceImpl(testMap);

        Integer expected1 = 0;
        Integer num1 = storageService.deleteById(0);
        Object expected2 = storageService.findById(0);

        assertEquals(expected1, num1);
        assertEquals(expected2, null);
        clear();
    }

    @Test
    public void deleteTest2() {
        init();
        StorageServiceImpl storageService = new StorageServiceImpl(testMap);

        Integer expected1 = 1;

        Integer num1 = storageService.deleteById(1);
        Object expected2 = storageService.findById(1);
        assertEquals(expected1, num1);
        assertEquals(expected2, null);
        clear();
    }

    @Test
    public void updateTest() {
        init();

        StorageServiceImpl storageService = new StorageServiceImpl(testMap);

        Integer num9 = 9;
        Integer num1 = 1;

        Integer expected1 = storageService.updateById(num9, record4);
        Integer expected2 = storageService.updateById(num1, record4);

        Person rec = storageService.findById(num9);

        assertEquals(expected1, rec);
        assertEquals(expected2, num1);
        clear();
    }

    @Test
    public void getAllTest() {
        init();

        List<Person> expectedRecord = List.of(record, record2, record3, record4);
        StorageServiceImpl storageService = new StorageServiceImpl(testMap);

        Map<Integer, Person> copyStorage = storageService.getAllRecords();
        int index = 0;
        for (Map.Entry<Integer, Person> entry : copyStorage.entrySet()) {
            Person expected = expectedRecord.get(index);
            Person actual = entry.getValue();

            assertEquals(expected, actual, "Ошибка в записи с ключом: " + entry.getKey());
            index++;
        }
        clear();
    }
}
