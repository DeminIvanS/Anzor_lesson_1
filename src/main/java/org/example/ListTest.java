package org.example;

import java.util.ArrayList;
import java.util.List;

public class ListTest {
    public static void main(String[] args) {
        List<Integer> num = new ArrayList<>(List.of(1,2,3,4,5));
       /* for(int i : num ){
            if(i%2 ==0){
                num.remove(i);
            }
        }*/
    //TODO: почтитать про итератор и исключения модификации
        for (int i = 0; i < num.size(); i++) {
            if(num.get(i)%2 ==0){
                num.remove(i);

            }
        }
        System.out.println(num);

    }
}
