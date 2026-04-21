package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DBServiceImpl implements StorageService{




    @Override
    public Integer save(Person record) {
        String sql = "insert into person (name, age) VALUES (?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
        ){
            statement.setString(1, record.getName());
            statement.setInt(2,record.getAge());
            statement.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException("Ошибка записи данных их бд: " + e.getMessage());
            //TODO: возвращать последний вставленный id
        }
        return lastId();
    }
    private Integer lastId(){
        String sql = "select id from person order by id desc limit 1";
        Integer lastId = null;
        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ){
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                lastId = result.getInt(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lastId;
    }


    @Override
    public Person findById(Integer id) {
        String sql = "select * from person where id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
        ){
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if(rs.next()) {
                    Person person = new Person();
                    person.setName(rs.getString("name"));
                    person.setAge(rs.getInt("age"));
                    return person;
                }
            }
        }catch (SQLException e){
            throw new RuntimeException("Ошибка записи данных их бд: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Integer deleteById(Integer id) {
        String sql = "delete from person where id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
        ){
            statement.setInt(1, id);
            statement.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException("Ошибка записи данных их бд: " + e.getMessage());
        }
        return id;
    }

    @Override
    public Integer updateById(Integer id, Person rec) {
        String sql = "update person set name = ?, age = ? where id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
        ){
            statement.setString(1, rec.getName());
            statement.setInt(2,rec.getAge());
            statement.setInt(3,id);
            statement.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException("Ошибка записи данных их бд: " + e.getMessage());
        }
        return id;
    }

    @Override
    public Map<Integer, Person> getAllRecords() {
        String sql = "select * from person";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
        ){
            try (ResultSet rs = statement.executeQuery()){
                Map<Integer, Person> map = new HashMap<>();
                while (rs.next()){
                    Person person = new Person();
                    person.setName(rs.getString("name"));
                    person.setAge(rs.getInt("age"));
                    map.put(rs.getInt("id"), person);
                }
                return map;
            }

        }catch (SQLException e){
            throw new RuntimeException("Ошибка записи данных их бд: " + e.getMessage());
        }

    }
}
