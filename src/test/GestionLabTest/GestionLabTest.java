package test.GestionLabTest;

import gestionLab.GestionLab;
import laboratorio.Experimento;
import laboratorio.Poblacion;
import medio.Luminosidad;
import dataio.UserInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import laboratorio.Experimento;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;

import static gestionLab.GestionLab.buscarPoblacion;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GestionLabTest {

    @Test
    public void testCreatePoblacion() throws Exception {
        // Arrange
        Poblacion poblacion = new Poblacion();
        Experimento experimento = new Experimento("Experiment");

        // Act
        GestionLab.createPoblacion( experimento);

        // Assert
        assertEquals(1, experimento.getNumPoblaciones()); // Verify that numPoblaciones is incremented
        assertEquals(poblacion, experimento.getPoblacionNueva()); // Verify that poblacionNueva is set correctly
    }
    @Test
    public void testAddPoblacion() {
        // Arrange
        Poblacion poblacion = new Poblacion();
        Experimento experimento = new Experimento("Experiment");

        // Act
        GestionLab.addPoblacion(poblacion, experimento);

        // Assert
        assertEquals(1, experimento.getNumPoblaciones()); // Verify that numPoblaciones is incremented
        assertEquals(poblacion, experimento.getPoblacionNueva()); // Verify that poblacionNueva is set correctly
    }


    @Test
    public void testDeletePoblacion() {
        // Arrange
        Poblacion poblacion = new Poblacion();
        poblacion.setNombrePoblacion("NombrePoblacionToRemove");
        Experimento experimento = new Experimento("Experiment");
        experimento.getPoblacionesList().add(poblacion); // Add the poblacion to the list

        // Act
        GestionLab.deletePoblacion("NombrePoblacionToRemove", experimento); // Replace with actual poblacion name

        // Assert
        assertEquals(0, experimento.getNumPoblaciones()); // Verify that numPoblaciones is updated
        assertEquals(0, experimento.getPoblacionesList().size()); // Verify that poblacion is removed
    }


        @Test
        void testBuscarPoblacion_ExistingPopulation() {
            // Arrange
            Experimento e = new Experimento("Experimento"); // Create an instance of Experimento
            Poblacion existingPoblacion = new Poblacion(); // Create an existing population
            existingPoblacion.setNombrePoblacion("PoblacionAEncontrar");
            e.getPoblacionesList().add(existingPoblacion);

            // Act
            Poblacion result = buscarPoblacion("PoblacionAEncontrar", e);

            // Assert
            assertNotNull(result);
            assertEquals(existingPoblacion, result);
        }

        @Test
        void testBuscarPoblacion_NonExistingPopulation() {
            // Arrange
            Experimento e = new Experimento("Experimento"); // Create an instance of Experimento

            // Act and Assert
            assertThrows(RuntimeException.class, () -> buscarPoblacion("NonExistingCity", e));
        }

}