package tests.medio;

import medio.ComidaCte;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de pruebas unitarias para la clase ComidaCte.
 * @autor Ana Ventura-Traveset
 */
class ComidaCteTest {

    private ComidaCte comidaCte;


    @BeforeEach
    void setUp() {
        comidaCte = new ComidaCte(100, LocalDate.now(), LocalDate.now().plusDays(5));
    }

    @Test
    void calcularComidaTest() {
        int[] expected = new int[]{100, 100, 100, 100, 100};
        assertArrayEquals(expected, comidaCte.calcularComida());
    }

    @Test
    void toStringTest() {
        String expected =
                "Tipo: Comida Constante" +
                "\nLa duración es de " + 5 + " días." +
                "\nEn la fecha de inicio: " + LocalDate.now() + ", cantidad de comida inicial: 100 microgramos ." +
                "\nTiene como fecha final: " + LocalDate.now().plusDays(5) +
                "\nCantidad de dosis de comida diaria: [100, 100, 100, 100, 100]";

        String supposed = comidaCte.toString();
        assertEquals(expected, supposed);
    }

    @Test
    void toStringToFileTest() {
        String expected = LocalDate.now() + ";100;" + LocalDate.now().plusDays(5);
        assertEquals(expected, comidaCte.toStringToFile());
    }
}