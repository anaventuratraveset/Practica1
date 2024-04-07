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
        comida = new Comida(100.0f, LocalDate.of(2023, 1, 1), 200.0f, LocalDate.of(2023, 1, 15), 50.0f, LocalDate.of(2023, 1, 30));
    }

    @Test
    void testConstructor() {
        assertEquals(100.0f, comida.getCantidadInicial());
        assertNotNull(comida.calcularComida());
    }

    @Test
    void testCalcularComida() {
        float[] expected = new float[30];
        for (int i = 0; i < 15; i++) {
            expected[i] = ((200.0f - 100.0f) / 15) * i + 100.0f;
        }
        for (int i = 15; i < 30; i++) {
            expected[i] = ((200.0f - 50.0f) / 15) * i + 150.0f;
        }
        assertArrayEquals(expected, comida.calcularComida(), 0.01f);
    }
}