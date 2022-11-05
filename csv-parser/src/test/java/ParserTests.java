import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

class ParserTests {

    @Test
    void test() {
        new Parser().main("/tmp/test.csv");
        assertFalse(true);
    }
}
