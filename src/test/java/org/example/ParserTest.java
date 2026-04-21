package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    private Parser parser = new Parser();
    @Test
    public void parseGetTest() throws JsonProcessingException {
        // given
        String input = "Get 1";
        Command expected = new Command(1,CommandType.GET);
        // when
        Command command = parser.parse(input);
        // then
        assertEquals(expected,command);

    }
    @Test
    public void parseGetAllTest() throws JsonProcessingException {
        String input = "GET";

        Command expected = new Command(null, null, CommandType.GET_ALL);
        Command command = parser.parse(input);

        assertEquals(expected, command);
    }
    @Test
    public void parseDeleteTest() throws JsonProcessingException {
        String input = "delete 2";

        Command expected = new Command(2, null, CommandType.DELETE);
        Command command = parser.parse(input);

        assertEquals(expected, command);
    }
    @Test
    public void parseUpdateTest() throws JsonProcessingException {
        String input = "update 1 {\"name\":\"Vas\",\"age\": 29}";
        String[] words = input.split(" ", 3);


        Command expected = new Command(Integer.parseInt(words[1]), parser.toPerson(words[2]), CommandType.UPDATE);
        Command command = parser.parse(input);

        assertEquals(expected, command);
    }

    @Test
    public void parseCreateTest() throws JsonProcessingException {
        String input = "create {\"name\":\"Vasy\",\"age\": 27}";
        String[] words = input.split(" ", 2);


        Command expected = new Command(null, parser.toPerson(words[1]), CommandType.CREATE);
        Command command = parser.parse(input);

        assertEquals(expected, command);
    }

}
