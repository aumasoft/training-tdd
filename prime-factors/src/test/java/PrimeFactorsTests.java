import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class PrimeFactorsTests {

    @Test
    void test() {
        assertEquals(List.of(), PrimeFactors.generate(1));
    }
}
