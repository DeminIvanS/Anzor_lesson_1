package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class InMemoryServiceImpl implements StorageService {
    private Map<Integer, Person> storage = new HashMap<>();
    private Integer lastId = -1;

    public InMemoryServiceImpl(Map<Integer, Person> map) {
        load(map);
    }

    @Override
    public Integer save(Person record) {
        lastId++;
        storage.put(lastId, record);
        return lastId;
    }

    @Override
    public Person findById(Integer id) {
        return storage.get(id);
    }

    @Override
    public Integer deleteById(Integer id) {
        storage.remove(id);
        return id;
    }

    @Override
    public Integer updateById(Integer id, Person person) {
        if (storage.containsKey(id)) {
            storage.put(id, person);
            return id;
        } else {
            return null;
        }
    }

    @Override
    public Map getAllRecords() {
        Map<Integer, Person> copyStorage = new HashMap<>(storage);
        return copyStorage;
    }


    private void load(Map<Integer, Person> loadedStorage) {
        storage.putAll(loadedStorage);
        this.lastId = storage.keySet().stream().max(Integer::compare).orElse(-1);
    }
    public String toJson(Person person) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(person);
        return json;
    }
}
