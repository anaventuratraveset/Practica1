package tests.laboratorio;
import laboratorio.Celda;
import laboratorio.Bacteria;
import excepciones.ComidaCeldaExcepcion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test clase Celda
 */
public class CeldaTest {
    private Celda celda;

    @BeforeEach
    public void setUp() {
        celda = new Celda(100, 5);
    }

    /**
     * Test metodo anadirComida
     * */
    @Test
    public void testAnadirComida() {
        celda.anadirComida(50);
        assertEquals(150, celda.getComida());
    }

    /**
     * Test metodo anadirBacteria
     * */
    @Test
    public void testAnadirBacteria() {
        Bacteria bacteria = new Bacteria();
        celda.anadirBacteria(bacteria);
        assertTrue(celda.getListBacterias().contains(bacteria));
    }

    /**
     * Test metodo getComida
     * */
    @Test
    public void testGetComida() {
        assertEquals(100, celda.getComida());
    }

    /**
     * Test metodo getBacteriasVivas
     * */
    @Test
    public void testGetBacteriasVivas() {
        assertEquals(5, celda.getBacteriasVivas());
    }

    /**
     * Test metodo setComida
     * */
    @Test
    public void testSetComida() {
        celda.setComida(200);
        assertEquals(200, celda.getComida());
    }

    /**
     * Test metodo eliminarComida
     * */
    @Test
    public void testEliminarComida() {
        try {
            celda.eliminarComida(50);
            assertEquals(50, celda.getComida());
        } catch (ComidaCeldaExcepcion e) {
            fail("Exception should not be thrown");
        }
    }

    /**
     * Test metodo eliminarComidaException
     * */
    @Test
    public void testEliminarComidaException() {
        assertThrows(ComidaCeldaExcepcion.class, () -> celda.eliminarComida(150));
    }

    /**
     * Test metodo cantidadAcomer
     * */
    @Test
    public void testCantidadAcomer() {
        try {
            int cantidadAIngerir = celda.cantidadAcomer();
            assertEquals(20, cantidadAIngerir);
            assertEquals(80, celda.getComida());
        } catch (ComidaCeldaExcepcion e) {
            fail("Exception should not be thrown");
        }
    }
}