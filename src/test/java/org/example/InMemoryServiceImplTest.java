package org.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InMemoryServiceImplTest {
    Parser parser = new Parser();
    private Map<Integer, Person> testMap = new HashMap<>();
    private Person record = parser.toPerson("{\"name\":\"Vasily\",\"age\":\"29\"}");
    private Person record2 = parser.toPerson("{\"name\":\"Nikolay\",\"age\":\"51\"}");
    private Person record3 = parser.toPerson("{\"name\":\"Mary\",\"age\":\"22\"}");
    private Person record4 = parser.toPerson("{\"name\":\"Elon\",\"age\":\"61\"}");

    public InMemoryServiceImplTest() throws JsonProcessingException {
    }

    @BeforeEach
    public void init() {
        testMap.put(0, record);
        testMap.put(1, record2);
        testMap.put(2, record3);
        testMap.put(3, record4);
    }

    @AfterEach
    public void clear() {
        testMap.clear();
    }

    @Test
    public void saveTest() {
        StorageServiceImpl storageService = new StorageServiceImpl(Map.of());

        Integer expected1 = 0;
        Integer expected2 = 1;
        Integer num1 = storageService.save(record);
        Integer num2 = storageService.save(record2);

        assertEquals(expected1, num1);
        assertEquals(expected2, num2);
    }

    @Test
    public void findByIdTest() {
        StorageServiceImpl storageService = new StorageServiceImpl(testMap);

        Person rec1 = storageService.findById(0);
        Person rec2 = storageService.findById(1);
        Person expected1 = testMap.get(0);
        Person expected2 = testMap.get(1);

        assertEquals(expected1, rec1);
        assertEquals(expected2, rec2);
    }

    @Test
    public void deleteTest1() {
        StorageServiceImpl storageService = new StorageServiceImpl(testMap);

        Integer expected1 = 0;
        Integer num1 = storageService.deleteById(0);
        Object expected2 = storageService.findById(0);

        assertEquals(expected1, num1);
        assertEquals(expected2, null);
    }

    @Test
    public void deleteTest2() {
        StorageServiceImpl storageService = new StorageServiceImpl(testMap);

        Integer expected1 = 1;

        Integer num1 = storageService.deleteById(1);
        Object expected2 = storageService.findById(1);
        assertEquals(expected1, num1);
        assertEquals(expected2, null);
    }

    @Test
    public void updateTest() {
        StorageServiceImpl storageService = new StorageServiceImpl(testMap);

        Integer num9 = 9;
        Integer num1 = 1;

        Integer expected1 = storageService.updateById(num9, record4);
        Integer expected2 = storageService.updateById(num1, record4);

        Person rec = storageService.findById(num9);

        assertEquals(expected1, rec);
        assertEquals(expected2, num1);
    }

    @Test
    public void getAllTest() {
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
    }
}
