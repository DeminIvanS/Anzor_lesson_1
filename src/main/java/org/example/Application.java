package org.example;

import java.util.Collections;
import java.util.Scanner;

public class Application {
    FileService textService = new FileServiceImpl();
    Scanner scanner = new Scanner(System.in);
    StorageService service = new StorageServiceImpl(Collections.emptyMap());
    CommandHandler handler = new CommandHandler(service);

    Parser parser = new Parser();

    public void start() {
        String str = "";
        while (!"exit".equals(str)) {
            try {
                str = scanner.nextLine();
                Command command = parser.parse(str);
                Result result = handler.handle(command);
                System.out.println(result.getMessage());
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        textService.write(service.getAllRecords());
    }
}
