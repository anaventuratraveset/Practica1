package tests.laboratorio;

import laboratorio.Bacteria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase BacteriaTest
 * Test de los métodos de la clase Bacteria
 * @author Ana Ventura-Traveset
 * */

public class BacteriaTest {
    private Bacteria bacteria;

    /**
     * Test del método setComidaIngerida
     * */
    @Test
    public void testSetComidaIngerida() {
        bacteria.setComidaIngerida(15);
        assertEquals(15, bacteria.getComidaIngerida());
    }

    /**
     * Test del método contarComidaIngerida
     * */
    @Test
    public void testContarComidaIngerida() {
        int comidaIngerida = bacteria.contarComidaIngerida(5);
        assertTrue(comidaIngerida >= 0 && comidaIngerida <= 50);
        assertEquals(5, bacteria.getComidaIngerida());
    }
}
