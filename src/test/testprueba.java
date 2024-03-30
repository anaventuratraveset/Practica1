package test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testprueba {
    @Test
    public void testprueba() {
        double actualArea = 3.141592653589793;
        double expectedArea = 3.141592653589793;
        assertEquals(expectedArea, actualArea, 0.005);
    }
}
