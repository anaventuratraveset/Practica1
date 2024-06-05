package tests.Ordenacion;

import laboratorio.Poblacion;
import ordenacion.OrdenacionCronologica;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;

import java.time.LocalDate;

/**
 * Clase de pruebas unitarias para la clase OrdenacionCronologica.
 * @autor Ana Ventura-Traveset
 */
public class OrdenacionCronologicaTest {

    @Test
    public void testCompare() {
        // Crear dos objetos Poblacion simulados con Mockito
        Poblacion p1 = Mockito.mock(Poblacion.class);
        Poblacion p2 = Mockito.mock(Poblacion.class);

        // Configurar las fechas de inicio de los objetos Poblacion
        Mockito.when(p1.getFechaInicio()).thenReturn(LocalDate.of(2020, 1, 1));
        Mockito.when(p2.getFechaInicio()).thenReturn(LocalDate.of(2021, 1, 1));

        // Crear un objeto OrdenacionCronologica para realizar la prueba
        OrdenacionCronologica ordenacion = new OrdenacionCronologica();

        // Verificar que compare() devuelve un número negativo cuando la fecha de inicio de p1 es anterior a la de p2
        assertTrue(ordenacion.compare(p1, p2) < 0);

        // Verificar que compare() devuelve un número positivo cuando la fecha de inicio de p1 es posterior a la de p2
        Mockito.when(p1.getFechaInicio()).thenReturn(LocalDate.of(2022, 1, 1));
        assertTrue(ordenacion.compare(p1, p2) > 0);

        // Verificar que compare() devuelve 0 cuando las fechas de inicio son iguales
        Mockito.when(p1.getFechaInicio()).thenReturn(LocalDate.of(2021, 1, 1));
        assertEquals(0, ordenacion.compare(p1, p2));
    }

    @Test
    public void testCompareWithNull() {
        // Crear un objeto Poblacion simulado con Mockito
        Poblacion p1 = Mockito.mock(Poblacion.class);

        // Crear un objeto OrdenacionCronologica para realizar la prueba
        OrdenacionCronologica ordenacion = new OrdenacionCronologica();

        // Verificar que compare() lanza una excepción cuando uno de los objetos es nulo
        assertThrows(IllegalArgumentException.class, () -> ordenacion.compare(p1, null));
        assertThrows(IllegalArgumentException.class, () -> ordenacion.compare(null, p1));
    }
}
