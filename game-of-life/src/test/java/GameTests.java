import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

class GameTests {

    @Test
    void test() {
        assertFalse(new Game().isEnded());
    }
}
