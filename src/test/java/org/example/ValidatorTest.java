package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ValidatorTest {
    Validator validator = new Validator();

    @Test
    public void updateOkTest(){
        String input = "UPDATE 2 {\"name\":\"Vasily\",\"age\":\"29\"}";
        assertDoesNotThrow(() -> validator.validate(input));
    }
    @Test
    public void updateFailsTest1(){
        String input = "UPDATE sdf";

        Exception ex = assertThrows(
                IllegalArgumentException.class,
                () -> validator.validate(input)
        );
        assertEquals("Wrong string", ex.getMessage());
    }
    @Test
    public void updateFailsTest2(){
        String input = "UPDATE sdf skdhjsbfkh";

        Exception ex = assertThrows(
                IllegalArgumentException.class,
                () -> validator.validate(input)
        );
        assertEquals("Wrong id", ex.getMessage());
    }
    @Test
    public void updateFailsTest3(){
        String input = "UPDdATE 2 skdhjsbfkh";

        Exception ex = assertThrows(
                IllegalArgumentException.class,
                () -> validator.validate(input)
        );
        assertEquals("Wrong command", ex.getMessage());
    }

    @Test
    public void createOkTest(){
        String input = "create {\"name\":\"Vasily\",\"age\":\"29\"}";
        assertDoesNotThrow(() -> validator.validate(input));
    }
    @Test
    public void createFailsTest1(){
        String input = "create {\"name\":\"Vasily\",\"age\"\"29\"}";

        Exception ex = assertThrows(
                IllegalArgumentException.class,
                () -> validator.validate(input)
        );
        assertEquals("It's not json", ex.getMessage());
    }
    @Test
    public void createFailsTest2(){
        String input = "cReate  .";

        Exception ex = assertThrows(
                IllegalArgumentException.class,
                () -> validator.validate(input)
        );
        assertEquals("It's not json", ex.getMessage());
    }
    @Test
    public void createFailsTest3(){
        String input = "creeate 2 skdhjsbfkh";

        Exception ex = assertThrows(
                IllegalArgumentException.class,
                () -> validator.validate(input)
        );
        assertEquals("Wrong command", ex.getMessage());
    }

    @Test
    public void getAllOkTest(){
        String input = "get";
        assertDoesNotThrow(() -> validator.validate(input));
    }

    @Test
    public void getOkTest(){
        String input = "get 1";
        assertDoesNotThrow(() -> validator.validate(input));
    }
    @Test
    public void getFailsTest1(){
        String input = "get zdfxg";

        Exception ex = assertThrows(
                IllegalArgumentException.class,
                () -> validator.validate(input)
        );
        assertEquals("Wrong id", ex.getMessage());
    }
    @Test
    public void getFailsTest2(){
        String input = "get 2 skdhjsbfkh";

        Exception ex = assertThrows(
                IllegalArgumentException.class,
                () -> validator.validate(input)
        );
        assertEquals("Wrong string", ex.getMessage());
    }

    @Test
    public void deleteOkTest(){
        String input = "delete 1";
        assertDoesNotThrow(() -> validator.validate(input));
    }
    @Test
    public void deleteFailsTest1(){
        String input = "delete zdfxg";

        Exception ex = assertThrows(
                IllegalArgumentException.class,
                () -> validator.validate(input)
        );
        assertEquals("Wrong id", ex.getMessage());
    }
    @Test
    public void deleteFailsTest2(){
        String input = "delete 2 skdhjsbfkh";

        Exception ex = assertThrows(
                IllegalArgumentException.class,
                () -> validator.validate(input)
        );
        assertEquals("Wrong string", ex.getMessage());
    }

}
