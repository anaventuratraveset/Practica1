package tests.laboratorio;

import laboratorio.Plato;
import laboratorio.Celda;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de pruebas unitarias para la clase Plato.
 * @autor Ana Ventura-Traveset
 * */
class PlatoTest {
    private Plato plato;

    @BeforeEach
    void setUp() {
        plato = new Plato(16, 400);
    }

    @Test
    void testGetCelda() {
        Celda[][] celdas = plato.getCelda();
        assertNotNull(celdas);
        assertEquals(20, celdas.length);
        assertEquals(20, celdas[0].length);
    }

    @Test
    void testGetDimension() {
        assertEquals(20, plato.getDimension());
    }

    @Test
    void testGetSpecificCelda() {
        Celda celda = plato.getCelda(10, 10);
        assertNotNull(celda);
    }

    @Test
    void testInicializarPlato() {
        plato.inicializarPlato(16, 400);
        Celda[][] celdas = plato.getCelda();
        for (int i = 0; i < plato.getDimension(); i++) {
            for (int j = 0; j < plato.getDimension(); j++) {
                assertNotNull(celdas[i][j]);
            }
        }
    }
}
