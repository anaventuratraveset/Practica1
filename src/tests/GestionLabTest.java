package tests;

import gestion.GestionLab;
import laboratorio.Experimento;
import laboratorio.Poblacion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static gestion.GestionLab.buscarPoblacion;
import static org.junit.jupiter.api.Assertions.*;
public class GestionLabTest {

    private Experimento experimento;
    private Poblacion poblacion;

    @BeforeEach
    public void setup() {
        experimento = new Experimento("Experiment");
        poblacion = new Poblacion();
    }


    @Test
    public void testCreatePoblacion() throws Exception {
        Poblacion result = GestionLab.createPoblacion(experimento);
        assertNotNull(result);
        assertEquals(1, experimento.getNumPoblaciones());
    }

    @Test
    public void testAddPoblacion() {
        GestionLab.addPoblacion(poblacion, experimento);
        assertEquals(1, experimento.getNumPoblaciones());
    }
    @Test
    public void testDeletePoblacion() {
        Poblacion poblacion = new Poblacion();
        poblacion.setNombrePoblacion("NombrePoblacionToRemove");
        Experimento experimento = new Experimento("Experiment");
        experimento.getPoblacionesList().add(poblacion); // Add the poblacion to the list
        GestionLab.borrarPoblacion("NombrePoblacionToRemove", experimento); // Replace with actual poblacion name
        assertEquals(0, experimento.getNumPoblaciones()); // Verify that numPoblaciones is updated
        assertEquals(0, experimento.getPoblacionesList().size()); // Verify that poblacion is removed
    }
    @Test
    void testBuscarPoblacion_ExistingPopulation() {
        Experimento e = new Experimento("Experimento"); // Create an instance of Experimento
        Poblacion existingPoblacion = new Poblacion(); // Create an existing population
        existingPoblacion.setNombrePoblacion("PoblacionAEncontrar");
        e.getPoblacionesList().add(existingPoblacion);
        Poblacion result = buscarPoblacion("PoblacionAEncontrar", e);
        assertNotNull(result);
        assertEquals(existingPoblacion, result);
    }
    @Test
    void testBuscarPoblacion_NonExistingPopulation() {
        Experimento e = new Experimento("Experimento"); // Create an instance of Experimento
        assertThrows(RuntimeException.class, () -> buscarPoblacion("NonExistingCity", e));
    }
}

