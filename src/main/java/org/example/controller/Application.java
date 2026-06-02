package org.example.controller;

import org.example.model.Result;
import org.example.model.Command;
import org.example.handler.CommandHandler;
import org.example.storage.DBServiceImpl;
import org.example.storage.FileService;
import org.example.storage.FileServiceImpl;
import org.example.storage.StorageService;

import java.util.Scanner;

public class Application {
    FileService textService = new FileServiceImpl();
    Scanner scanner = new Scanner(System.in);

    StorageService service = new DBServiceImpl();
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
