package org.example;

import java.util.HashMap;
import java.util.Map;

public class Storage {
    private Map<Integer, Record> storage = new HashMap<>();
    public int getLastId(){
        int lastId = storage.entrySet().size();
        return lastId;
    }
}
