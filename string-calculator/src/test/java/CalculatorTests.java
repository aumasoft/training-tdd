import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class CalculatorTests {

    @Test
    void test() {
        assertEquals(null, Calculator.calculate(null));
    }
}
