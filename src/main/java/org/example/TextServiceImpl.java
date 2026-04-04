package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TextServiceImpl implements FileService {
    private final StorageService service;

    public TextServiceImpl(StorageService service) {
        this.service = service;
    }


    @Override
    public void write() {
        Map<Integer, Record> storage = service.getAllRecords();
        Path path = Path.of("storage.txt");

        try (var writer = Files.newBufferedWriter(path)) {
            for (var entry : storage.entrySet()) {
                writer.write(entry.getKey() + " - " + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Сontent has been written to the file.");
    }

    @Override
    public void reed() {
        try (Stream<String> lines = Files.lines(Path.of("storage.txt"))) {
            Map<Integer, Record> restoredMap = lines
                    .map(line -> line.split(" - ", 2))
                    .filter(parts -> parts.length == 2)
                    .collect(Collectors.toMap(
                            parts -> Integer.valueOf(parts[0].trim()),
                            parts -> new Record(parts[1].trim())
                    ));
            service.load(restoredMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Content has been uploaded to storage from a file.");
    }
}
