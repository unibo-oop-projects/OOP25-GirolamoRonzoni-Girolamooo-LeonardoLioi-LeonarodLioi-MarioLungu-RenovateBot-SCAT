package it.unibo.sampleapp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * Test di base.
 */
class AppTest {

    @Test
    void testPlaceholder() {
        final var expected = "OOP25-SCAT";
        assertEquals("OOP25-SCAT", expected);
    }
}
