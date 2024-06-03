package tests.laboratorio;
import medio.ComidaCte;
import medio.ComidaPadre;
import medio.Luminosidad;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import laboratorio.*;

/**
 * Clase de test para la clase Poblacion
 */
public class PoblacionTest {

    /**
     * Test para el getNombrePoblacion
     */
    @Test
    public void testGetNombrePoblacion() {
        Poblacion poblacion = new Poblacion();
        poblacion.setNombrePoblacion("Test");
        assertEquals("Test", poblacion.getNombrePoblacion());
    }

    /**
     * Test para el getNumInicialBacterias
     */
    @Test
    public void testGetNumInicialBacterias() {
        Poblacion poblacion = new Poblacion();
        poblacion.setNumInicialBacterias(100);
        assertEquals(100, poblacion.getNumInicialBacterias());
    }

    /**
     * Test para el getTemperatura
     */
    @Test
    public void testGetTemperatura() {
        Poblacion poblacion = new Poblacion();
        poblacion.setTemperatura(37.0f);
        assertEquals(37.0f, poblacion.getTemperatura());
    }

    /**
     * Test para el getFechaInicio
     */
    @Test
    public void testGetFechaInicio() {
        Poblacion poblacion = new Poblacion();
        LocalDate date = LocalDate.now();
        poblacion.setFechaInicio(date);
        assertEquals(date, poblacion.getFechaInicio());
    }

    /**
     * Test para el getFechaFin
     */
    @Test
    public void testGetFechaFin() {
        Poblacion poblacion = new Poblacion();
        LocalDate date = LocalDate.now();
        poblacion.setFechaFin(date);
        assertEquals(date, poblacion.getFechaFin());
    }

    /**
     * Test para el getLuminosidad
     */
    @Test
    public void testGetLuminosidad() {
        Poblacion poblacion = new Poblacion();
        poblacion.setLuminosidad(Luminosidad.luminosidad.ALTA);
        assertEquals(Luminosidad.luminosidad.ALTA, poblacion.getLuminosidad());
    }

    /**
     * Test para el getBacteriasList
     */
    @Test
    public void testGetBacteriasList() {
        Poblacion poblacion = new Poblacion();
        Bacteria bacteria = new Bacteria();
        poblacion.setBacteriaNueva(bacteria);
        assertTrue(poblacion.getBacteriasList().contains(bacteria));
    }


    /**
     * Test para el getComida
     */
    @Test
    public void testGetComida() {
        Poblacion poblacion = new Poblacion();
        ComidaPadre comida = new ComidaCte(100, LocalDate.now(), LocalDate.now().plusDays(1));
        poblacion.setComida(comida);
        assertEquals(comida, poblacion.getComida());
    }

    /**
     * Test para el toStringInfoPobFile
     */
    @Test
    public void testToStringInfoPobFile() {
        Poblacion poblacion = new Poblacion(100, "Test", 37.0f, LocalDate.now(), LocalDate.now().plusDays(1), Luminosidad.luminosidad.ALTA, 1);
        assertNotNull(poblacion.toStringInfoPobFile());
    }
}