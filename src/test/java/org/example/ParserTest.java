package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    private Parser parser = new Parser();
    @Test
    public void parseGetTest(){
        // given
        String input = "Get 1";
        Command expected = new Command(1,CommandType.GET);
        // when
        Command command = parser.parse(input);
        // then
        assertEquals(expected,command);

    }
    @Test
    public void parseGetAllTest(){
        String input = "GET";


        Command expected = new Command(null, null, CommandType.GET_ALL);
        Command command = parser.parse(input);


        assertEquals(expected, command);
    }
    @Test
    public void parseDeleteTest(){
        String input = "delete 2";

        Command expected = new Command(2, null, CommandType.DELETE);
        Command command = parser.parse(input);

        assertEquals(expected, command);
    }
    @Test
    public void parseUpdateTest(){
        String input = "update 1 dsgf";
        String[] words = input.split(" ", 3);


        Command expected = new Command(Integer.parseInt(words[1]), new Record(words[2]), CommandType.UPDATE);
        Command command = parser.parse(input);

        assertEquals(expected, command);
    }

    @Test
    public void parseCreateTest(){
        String input = "create dsgf";
        String[] words = input.split(" ", 3);


        Command expected = new Command(2, new Record(words[1]), CommandType.CREATE);
        Command command = parser.parse(input);

        assertEquals(expected, command);
    }

}
