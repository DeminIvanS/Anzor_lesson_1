package org.example;

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
    public void write(Map<Integer, Record> storage) {
        try (var writer = Files.newBufferedWriter(FILE_PATH)) {
            storage.forEach((key, value) -> {
                try {
                    writer.write(key + " - " + value.getValue());
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

    public Map<Integer, Record> read() {
        Map<Integer, Record> restoredMap = new HashMap<>();
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
                                parts -> new Record(parts[1].trim())
                        ));
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Content has been uploaded to storage from a file.");
            return restoredMap;
        }
    }
}
