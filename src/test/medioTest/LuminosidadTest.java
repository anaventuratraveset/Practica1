package test.medioTest;

import medio.Luminosidad;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LuminosidadTest {
    @Test
    void testLuminosidadEnum() {
        // Test the count of enum values
        assertEquals(3, Luminosidad.luminosidad.values().length);
        // Test each enum value
        assertEquals(Luminosidad.luminosidad.ALTA, Luminosidad.luminosidad.valueOf("ALTA"));
        assertEquals(Luminosidad.luminosidad.MEDIA, Luminosidad.luminosidad.valueOf("MEDIA"));
        assertEquals(Luminosidad.luminosidad.BAJA, Luminosidad.luminosidad.valueOf("BAJA"));
    }
}
