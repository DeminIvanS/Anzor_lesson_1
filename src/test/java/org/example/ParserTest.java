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
        String[] commandAndString = parser.getSplitOnTwo(input);
        String[] idAndString = parser.getSplitOnTwo(commandAndString[1]);

        Command expected = new Command(1, new Record(idAndString[1]), CommandType.UPDATE);
        Command command = parser.parse(input);

        assertEquals(expected, command);
    }

    @Test
    public void parseCreateTest(){
        String input = "create dsgf";
        String[] commandAndString = parser.getSplitOnTwo(input);


        Command expected = new Command(2, new Record(commandAndString[1]), CommandType.CREATE);
        Command command = parser.parse(input);

        assertEquals(expected, command);
    }

}
