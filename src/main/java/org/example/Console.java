package org.example;

import java.util.Scanner;

public class Console {
    Scanner scanner = new Scanner(System.in);
    StorageService service = new StorageServiceImpl();
    CommandHandler handler = new CommandHandler(service);
    FileService textService = new TextServiceImpl(service);

    Parser parser = new Parser();

    public void read() {
        String str = "";
        textService.reed();

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
        textService.write();
    }
}
