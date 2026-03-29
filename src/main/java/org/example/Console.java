package org.example;

import java.util.Scanner;

public class Console {
    Scanner scanner = new Scanner(System.in);
    Parser parser = new Parser();

    public void read() {
        String str = "";
        while (!"exit".equals(str)) {
            str = scanner.nextLine();
            parser.command(str);

        }

    }

}
