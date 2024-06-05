package tests.Ordenacion;

import laboratorio.Poblacion;
import ordenacion.OrdenacionCuantitativa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de pruebas unitarias para la clase OrdenacionCuantitativa.
 * @autor Ana Ventura-Traveset
 */

public class OrdenacionCuantitativaTest {

    private OrdenacionCuantitativa ordenacionCuantitativa;
    private Poblacion p1;
    private Poblacion p2;

    @BeforeEach
    public void setUp() {
        ordenacionCuantitativa = new OrdenacionCuantitativa();
        p1 = new Poblacion();
        p2 = new Poblacion();
    }

    @Test
    public void testCompare() {
        p1.setNumInicialBacterias(5);
        p2.setNumInicialBacterias(10);
        int result = ordenacionCuantitativa.compare(p1, p2);
        assertTrue(result < 0);
    }

    @Test
    public void testCompareEqual() {
        p1.setNumInicialBacterias(10);
        p2.setNumInicialBacterias(10);
        int result = ordenacionCuantitativa.compare(p1, p2);
        assertEquals(0, result);
    }

    @Test
    public void testCompareNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            ordenacionCuantitativa.compare(p1, null);
        });
    }
}