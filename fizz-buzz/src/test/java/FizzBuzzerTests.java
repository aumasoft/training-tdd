import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FizzBuzzerTests {

    @Test
    void test() {
        assertEquals("1", new FizzBuzzer().execute(1));
    }
}
