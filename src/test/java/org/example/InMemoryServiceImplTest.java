package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InMemoryServiceImplTest {

    private Map<Integer, Person> testMap = new HashMap<>();
    private Person person = new Person("Vasily", 29);
    private Person person1 = new Person("Nikolay", 51);
    private Person record3 = new Person("Mary", 22);
    private Person record4 = new Person("Elon", 61);

    public InMemoryServiceImplTest() throws JsonProcessingException {
    }

    @BeforeEach
    public void init() {
        testMap.put(0, person);
        testMap.put(1, person1);
        testMap.put(2, record3);
        testMap.put(3, record4);
    }

    @AfterEach
    public void clear() {
        testMap.clear();
    }

    @Test
    public void saveTest() {
        InMemoryServiceImpl storageService = new InMemoryServiceImpl(Map.of());

        Integer expected1 = 0;
        Integer expected2 = 1;
        Integer num1 = storageService.save(person);
        Integer num2 = storageService.save(person1);

        assertEquals(expected1, num1);
        assertEquals(expected2, num2);
    }

    @Test
    public void findByIdTest() {
        InMemoryServiceImpl storageService = new InMemoryServiceImpl(testMap);

        Person rec1 = storageService.findById(0);
        Person rec2 = storageService.findById(1);
        Person expected1 = testMap.get(0);
        Person expected2 = testMap.get(1);

        assertEquals(expected1, rec1);
        assertEquals(expected2, rec2);
    }

    @Test
    public void deleteTest1() {
        InMemoryServiceImpl storageService = new InMemoryServiceImpl(testMap);

        Integer expected1 = 0;
        Integer num1 = storageService.deleteById(0);
        Object expected2 = storageService.findById(0);

        assertEquals(expected1, num1);
        assertEquals(expected2, null);
    }

    @Test
    public void deleteTest2() {
        InMemoryServiceImpl storageService = new InMemoryServiceImpl(testMap);

        Integer expected1 = 1;

        Integer num1 = storageService.deleteById(1);
        Object expected2 = storageService.findById(1);
        assertEquals(expected1, num1);
        assertEquals(expected2, null);
    }

    @Test
    public void updateTest() {
        InMemoryServiceImpl storageService = new InMemoryServiceImpl(testMap);

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
        List<Person> expectedRecord = List.of(person, person1, record3, record4);
        InMemoryServiceImpl storageService = new InMemoryServiceImpl(testMap);
        Map<Integer, Person> copyStorage = storageService.getAllRecords();
        for (Map.Entry<Integer, Person> entry : copyStorage.entrySet()) {
            Person actual = entry.getValue();
            assertTrue(expectedRecord.contains(actual));
        }
    }
}
