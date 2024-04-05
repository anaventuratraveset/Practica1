package test.laboratorioTest;
import laboratorio.Poblacion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class PoblacionTest {

    private Poblacion poblacion;

    @BeforeEach
    void setUp() {
        poblacion = new Poblacion();
    }

    @Test
    void testGetNombrePoblacion() {
        poblacion.setNombrePoblacion("Población A");
        assertEquals("Población A", poblacion.getNombrePoblacion());
    }

    @Test
    void testSetNumInicialBacterias() {
        poblacion.setNumInicialBacterias(100);
        assertEquals(100, poblacion.getNumInicialBacterias());
    }

    @Test
    void testSetTemperatura() {
        poblacion.setTemperatura(25.5f);
        assertEquals(25.5f, poblacion.getTemperatura());
    }

    @Test
    void testSetFechaInicio() {
        LocalDate fechaInicio = LocalDate.of(2024, 4, 1);
        poblacion.setFechaInicio(fechaInicio);
        assertEquals(fechaInicio, poblacion.getFechaInicio());
    }

    @Test
    void testSetFechaFin() {
        LocalDate fechaFin = LocalDate.of(2024, 4, 30);
        poblacion.setFechaFin(fechaFin);
        assertEquals(fechaFin, poblacion.getFechaFin());
    }
}

