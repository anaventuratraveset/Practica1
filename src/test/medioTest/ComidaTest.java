package test.medioTest;

import medio.Comida;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ComidaTest {

    private Comida comida;

    @BeforeEach
    void setUp() {
        comida = new Comida(2.0f, LocalDate.of(2000, 9, 9), 9.0f, LocalDate.of(2000, 9, 12), 3.0f, LocalDate.of(2000, 10, 9));
    }

    @Test
    void testConstructor() {
        assertEquals(2.0f, comida.getCantidadInicial());
        assertEquals(9.0f, comida.getCantidadPico());
        assertNotNull(comida.calcularComida());
    }

    @Test
    public void testCalcularComida() {
        float[] result = comida.calcularComida();
        float[] expected = {2.0f, 4.333333f, 6.6666665f, 9.444445f, 9.666667f, 9.888889f, 10.111111f, 10.333333f, 10.555555f, 10.777778f, 11.0f, 11.222222f, 11.444445f, 11.666666f, 11.888889f, 12.111111f, 12.333333f, 12.555555f, 12.777778f, 13.0f, 13.222222f, 13.444445f, 13.666666f, 13.888889f, 14.111111f, 14.333334f, 14.555555f, 14.777778f, 15.0f, 15.222222f};
        assertArrayEquals (expected, result, 0.001f);
    }

}