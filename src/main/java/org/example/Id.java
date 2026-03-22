package org.example;

public class Id {
    private Integer id=0;
    StorageServiceImpl service = new StorageServiceImpl();

    public Integer getGenerateId(){
        id = service.storage.getLastId();
        id++;
        return id;
    }





}
