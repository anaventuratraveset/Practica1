package test.laboratorioTest;
import laboratorio.Experimento;
import laboratorio.Poblacion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ExperimentoTest {

    private Experimento experimento;

    @BeforeEach
    void setUp() {
        experimento = new Experimento("Mi Experimento");
    }

    @Test
    void testGetNombreExperimento() {
        assertEquals("Mi Experimento", experimento.getNombreExperimento());
    }

    @Test
    void testSetNombreExperimento() {
        experimento.setNombreExperimento("Nuevo Nombre");
        assertEquals("Nuevo Nombre", experimento.getNombreExperimento());
    }

    @Test
    void testGetDias() {
        assertEquals(30, experimento.getDias());
    }

    @Test
    void testGetNumPoblaciones() {
        experimento.setNumPoblaciones(5);
        assertEquals(5, experimento.getNumPoblaciones());
    }

    @Test
    void testGetPoblacionesList() {
        assertNotNull(experimento.getPoblacionesList());
    }

    @Test
    void testSetPoblacionNueva() {
        Poblacion poblacion = new Poblacion();
        experimento.setPoblacionNueva(poblacion);
        assertTrue(experimento.getPoblacionesList().contains(poblacion));
    }
}

