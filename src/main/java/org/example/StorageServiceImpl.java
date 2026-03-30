package org.example;

import java.util.LinkedHashMap;
import java.util.Map;

public class StorageServiceImpl implements StorageService {
    private Map<Integer, Record> storage = new LinkedHashMap<>();
    private Integer id = null;

    @Override
    public Integer save(Record record) {
        if (storage.isEmpty()) {
            id = 0;
            storage.put(id, record);
        } else {
            id = storage.keySet().stream()
                    .reduce((first, second) -> second)
                    .orElse(null);
            storage.put(++id, record);
        }
        return id;
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
    public Record getAllRecords() {
        for (Map.Entry<Integer, Record> entry : storage.entrySet()) {
            return entry.getValue();

        }
        return null;
    }


}
