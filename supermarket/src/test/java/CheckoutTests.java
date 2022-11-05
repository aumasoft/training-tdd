import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

class CheckoutTests {

    @Test
    void test() {
        new Checkout().scan("test");
        assertFalse(true);
    }
}
