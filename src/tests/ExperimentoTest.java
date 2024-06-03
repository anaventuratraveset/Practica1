package tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import laboratorio.*;

public class ExperimentoTest {

    @Test
    public void testGetNombreExperimento() {
        Experimento experimento = new Experimento("Exp");
        assertEquals("Exp", experimento.getNombreExperimento());
    }

    @Test
    public void testSetNombreExperimento() {
        Experimento experimento = new Experimento();
        experimento.setNombreExperimento("Exp");
        assertEquals("Exp", experimento.getNombreExperimento());
    }

    @Test
    public void testGetNumPoblaciones() {
        Experimento experimento = new Experimento();
        experimento.setNumPoblaciones(50);
        assertEquals(50, experimento.getNumPoblaciones());
    }

    @Test
    public void testSetNumPoblaciones() {
        Experimento experimento = new Experimento();
        experimento.setNumPoblaciones(50);
        assertEquals(50, experimento.getNumPoblaciones());
    }

    @Test
    public void testGetPoblacionesList() {
        Experimento experimento = new Experimento();
        ArrayList<Poblacion> poblaciones = new ArrayList<>();
        Poblacion poblacion = new Poblacion(); // Asumiendo que la clase Poblacion tiene un constructor sin parámetros
        poblaciones.add(poblacion);
        experimento.setPoblacionesList(poblaciones);
        assertEquals(poblaciones, experimento.getPoblacionesList());
    }

    @Test
    public void testSetPoblacionesList() {
        Experimento experimento = new Experimento();
        ArrayList<Poblacion> poblaciones = new ArrayList<>();
        Poblacion poblacion = new Poblacion(); // Asumiendo que la clase Poblacion tiene un constructor sin parámetros
        poblaciones.add(poblacion);
        experimento.setPoblacionesList(poblaciones);
        assertEquals(poblaciones, experimento.getPoblacionesList());
    }

    @Test
    public void testToString() {
        Experimento experimento = new Experimento("Exp");
        String expected = "Nombre Experimento: Exp\n\nInformación de las poblaciones:\n";
        assertEquals(expected, experimento.toString());
    }

    @Test
    public void testToStringNombres() {
        Experimento experimento = new Experimento("Exp");
        String expected = "Experimento: Exp\n\n";
        assertEquals(expected, experimento.toStringNombres());
    }

    @Test
    public void testToStringInfoExperimentoToFile() {
        Experimento experimento = new Experimento("Exp");
        String expected = "Exp";
        assertEquals(expected, experimento.toStringInfoExperimentoToFile());
    }
}