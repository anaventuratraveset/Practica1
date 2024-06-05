package tests.medio;
import medio.ComidaPadre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de pruebas unitarias para la clase ComidaPadre.
 * @author Ana Ventura-Traveset
 */

class ComidaPadreTest {

    private ComidaPadre comidaPadre;

    @BeforeEach
    void setUp() {
        comidaPadre = new ComidaPadre(100, LocalDate.now(), LocalDate.now().plusDays(5)) {
            @Override
            public int[] calcularComida() {
                return new int[0];
            }
        };
    }

    @Test
    void getCantidadComida() {
        assertNotNull(comidaPadre.getCantidadComida());
    }

    @Test
    void toStringTest() {
        String expected = "La duración es de 5 días.\nEn la fecha de inicio: " + LocalDate.now() + ", cantidad de comida inicial: 100 microgramos .";
        assertEquals(expected, comidaPadre.toString());
    }

    @Test
    void toStringToFile() {
        String expected = LocalDate.now() + ";100";
        assertEquals(expected, comidaPadre.toStringToFile());
    }
}
