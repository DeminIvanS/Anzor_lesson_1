package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileServiceImpl implements FileService {
    private final String FILE_NAME = "storage.txt";
    private final Path FILE_PATH = Path.of(System.getProperty("user.home") + File.separator + FILE_NAME);

    @Override
    public void write(Map<Integer, Person> storage) {
        try (var writer = Files.newBufferedWriter(FILE_PATH)) {
            storage.forEach((key, value) -> {
                try {
                    writer.write(key + " - " + toJson(value));
                    writer.newLine();
                } catch (IOException e) {
                    try {
                        throw new IOException(e);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
        } catch (IOException | UncheckedIOException e) {
            e.printStackTrace();
        }
        System.out.println("Сontent has been written to the file.");
    }

    public Map<Integer, Person> read() {
        Map<Integer, Person> restoredMap = new HashMap<>();
        if (!Files.exists(FILE_PATH)) {
            System.out.println("File not found. Returning empty map.");
            return restoredMap;
        } else {
            try (Stream<String> lines = Files.lines(FILE_PATH)) {
                restoredMap = lines
                        .map(line -> line.split(" - ", 2))
                        .filter(parts -> parts.length == 2)
                        .collect(Collectors.toMap(
                                parts -> Integer.valueOf(parts[0].trim()),
                                parts -> {
                                    try {
                                        return toPerson(parts[1]);
                                    } catch (JsonProcessingException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                        ));
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Content has been uploaded to storage from a file.");
            return restoredMap;
        }
    }

    public Person toPerson(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Person person = objectMapper.readValue(json, Person.class);
        return person;
    }

    public String toJson(Person person) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(person);
        return json;
    }
}
