import java.util.Map;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class TemplateTests {

    @Test
    void test() {
        assertEquals("", new Template("").evaluate(Map.of()));
    }
}
