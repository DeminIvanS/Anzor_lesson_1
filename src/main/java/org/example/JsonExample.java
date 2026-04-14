package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonExample {
    static class Client{
        String name;
        String address;
        String phone;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;

        }

        @Override
        public String toString() {
            return "Client{" +
                    "name='" + name + '\'' +
                    ", address='" + address + '\'' +
                    ", phone='" + phone + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) throws JsonProcessingException {

        Client client = new Client();
        client.name = "vasily";
        client.address = "Mars";
        client.phone = "911";


        System.out.println(toJson(client));
        System.out.println(toClient("{\"name\":\"vasily\",\"address\":\"Mars\",\"phone\":\"911\"}"));
        System.out.println(isValidJson("{\"name\":\"vasily\",\"address\":\"Mars\"\"phone\":\"911\"}"));

    }
    private static String toJson(Client client) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(client);
    return json;
    }
    private static Client toClient(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Client client = objectMapper.readValue(json, Client.class);

        return client;
    }
    private static boolean isValidJson(String json){
        ObjectMapper object = new ObjectMapper();
        try {
            object.readTree(json);
        }catch (JsonProcessingException e ){
            return false;
        }


        return true;
    }
}
