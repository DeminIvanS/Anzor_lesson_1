package org.example;

import java.util.HashMap;

import java.util.Map;

public class StorageServiceImpl implements StorageService {
    private Map<Integer, Record> storage = new HashMap<>();
    private Integer lastId = -1;

    @Override
    public Integer save(Record record) {
        lastId++;
        storage.put(lastId, record);
        return lastId;
    }

    @Override
    public Record findById(Integer id) {

        return storage.get(id);
    }

    @Override
    public Integer deleteById(Integer id) {
        storage.remove(id);
        return id;
    }

    @Override
    public Integer updateById(Integer id, Record rec) {
        if(storage.containsKey(id)) {
            storage.put(id, rec);
            return id;
        }else {
            return null;
        }
    }

    @Override
    public Map getAllRecords() {
        Map<Integer, Record> copyStorage = new HashMap<>(storage);
        return copyStorage;
    }


}
