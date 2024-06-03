package tests;

import excepciones.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de Tests para Excepciones
 */
public class ExcepcionesTest {


    /**
     * Test class for ComidaCeldaExcepcion
     */
    @Test
    public void testComidaCeldaExcepcion() {
        String mensaje = "Comida celda Excepcion";
        ComidaCeldaExcepcion excepcion = new ComidaCeldaExcepcion(mensaje);
        assertEquals(mensaje, excepcion.getMessage());
    }

    /**
     * Test class for ComidaMaxExcepcion
     */
    @Test
    public void testeComidaMaxExcepcion() {
        Exception exception = assertThrows(ComidaMaxExcepcion.class, () -> {
            throw new ComidaMaxExcepcion("Comida máxima excedida");
        });

        String expectedMessage = "Comida máxima excedida";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Test class for ComidaMinExcepcion
     */
    @Test
    public void testComidaMinExcepcion(){
        Exception exception = assertThrows(ComidaMaxExcepcion.class, () -> {
            throw new ComidaMaxExcepcion("Comida minima excedida");
        });

        String expectedMessage = "Comida minima excedida";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));    }

    /**
     * Test class for ComidaRangoExcepcion
     */

    @Test
    public void testComidaRangoExcepcionMessage() {
        try {
            throw new ComidaRangoExcepcion("Fuera del rango de comida");
        } catch (ComidaRangoExcepcion e) {
            assertEquals("Fuera del rango de comida", e.getMessage());
        }
    }
    /**
     * Test class for FechaExcepcion
     */
    @Test
    public void testFechaExcepcion() {
        String mensaje = "Fecha Excepcion";
        FechaExcepcion excepcion = new FechaExcepcion(mensaje);
        assertEquals(mensaje, excepcion.getMessage());
    }
    /**
     * Test class for NInicialBacterias
     */
    @Test
    public void testNInicialBacterias() {
        String mensaje = "Numero inicial de bacterias inferior al mínimo permitido";
        NInicialBacterias excepcion = new NInicialBacterias(mensaje);
        assertEquals(mensaje, excepcion.getMessage());
    }

}
