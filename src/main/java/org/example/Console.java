package org.example;

import java.util.Scanner;

public class Console {
    Scanner scanner = new Scanner(System.in);
    CommandHandler handler = new CommandHandler(new StorageServiceImpl());
    Parser parser = new Parser();

    public void read() {
        String str = "";
        while (!"exit".equals(str)) {
            str = scanner.nextLine();
            Command command = parser.parse(str);
            Result result = handler.handle(command);
            System.out.println(result.getMessage());
        }
    }
}
