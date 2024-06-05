package tests.medio;

import medio.Luminosidad;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 * Clase de test para la enumeración Luminosidad
 * @autor Ana Ventura-Traveset
 * */
public class LuminosidadTest {
    @Test
    void testLuminosidadEnum() {
// Comprueba que la enumeración tiene 3 valores
        assertEquals(3, Luminosidad.luminosidad.values().length);
// Comprueba cada uno de los valores de la enumeración
        assertEquals(Luminosidad.luminosidad.ALTA, Luminosidad.luminosidad.valueOf("ALTA"));
        assertEquals(Luminosidad.luminosidad.MEDIA, Luminosidad.luminosidad.valueOf("MEDIA"));
        assertEquals(Luminosidad.luminosidad.BAJA, Luminosidad.luminosidad.valueOf("BAJA"));
    }
}