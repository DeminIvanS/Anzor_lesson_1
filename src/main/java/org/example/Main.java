package org.example;

import org.example.controller.Application;
import org.example.model.Person;

public class Main {
    public static void main(String[] args) throws Exception {
        Application app = new Application();
     //   app.start();

        Person vasy = new Person("kolya", 2);
        int i = 15;

        rename(i);
        rename(vasy);
        System.out.println(vasy); //"Вася"
        System.out.println(i); // 15
    }

    public static void rename(Person person) throws Exception {
        person.setName("Вася");
    }
    public static void rename(int i){
        i = 10;
    }
    public static void toString(Object obj){
        String str = obj.toString();
        System.out.println(str);
    }
}
