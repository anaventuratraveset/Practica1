package tests.Ordenacion;

import laboratorio.Poblacion;
import ordenacion.OrdenacionAlfabetica;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;

/**
 * Clase de pruebas unitarias para la clase OrdenacionAlfabetica.
 */
public class OrdenacionAlfabeticaTest {

    @Test
    public void testCompare() {
        // Crear dos objetos mock de la clase Poblacion
        Poblacion p1 = Mockito.mock(Poblacion.class);
        Poblacion p2 = Mockito.mock(Poblacion.class);

        // Configurar los mocks para devolver valores específicos cuando se llame a getNombrePoblacion
        Mockito.when(p1.getNombrePoblacion()).thenReturn("A");
        Mockito.when(p2.getNombrePoblacion()).thenReturn("B");

        // Crear una instancia de la clase a probar
        OrdenacionAlfabetica ordenacion = new OrdenacionAlfabetica();

        // Ejecutar el método a probar y verificar el resultado
        int result = ordenacion.compare(p1, p2);
        assertTrue(result < 0); // "A" es alfabéticamente menor que "B", por lo que el resultado debe ser negativo

        // Cambiar el orden de los argumentos y verificar el resultado
        result = ordenacion.compare(p2, p1);
        assertTrue(result > 0); // "B" es alfabéticamente mayor que "A", por lo que el resultado debe ser positivo
    }

    @Test
    public void testCompareWithNull() {
        // Crear un objeto mock de la clase Poblacion
        Poblacion p1 = Mockito.mock(Poblacion.class);

        // Configurar el mock para devolver un valor específico cuando se llame a getNombrePoblacion
        Mockito.when(p1.getNombrePoblacion()).thenReturn("A");

        // Crear una instancia de la clase a probar
        OrdenacionAlfabetica ordenacion = new OrdenacionAlfabetica();

        // Ejecutar el método a probar con un argumento nulo y verificar que se lanza una excepción
        assertThrows(IllegalArgumentException.class, () -> ordenacion.compare(p1, null));
        assertThrows(IllegalArgumentException.class, () -> ordenacion.compare(null, p1));
    }
}
