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

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GestionLabTest {

     GestionLab gestionLab;



        Experimento experimento /*= new Experimento("Experimento")*/;

    void mock(Experimento experimento) {
        when(experimento.getPoblacionesList()).thenReturn(new ArrayList<Poblacion>());
    }

   /* @Test
    void testCreatePoblacion() throws Exception {
        // Mock the UserInput responses
        when(UserInput.readString(anyString())).thenReturn("Test");
        when(UserInput.readInt(anyString())).thenReturn(25);
        when(UserInput.readFloat(anyString())).thenReturn(100.0f);
        when(UserInput.readLuminosidad(anyString())).thenReturn(Luminosidad.luminosidad.ALTA);
        when(UserInput.readDate(anyString())).thenReturn(LocalDate.now());

        // Call the method to test
        Poblacion result = GestionLab.createPoblacion(experimento);

        // Verify the result
        assertNotNull(result);
        assertEquals("Test", result.getNombrePoblacion());
        assertEquals(25, result.getTemperatura());
        assertEquals(Luminosidad.luminosidad.ALTA, result.getLuminosidad());
        assertEquals(100.0f, result.getComida().getCantidadInicial());
        assertEquals(LocalDate.now(), result.getFechaInicio());
    }*/

    @Test
    void testAddPoblacion() {
        Poblacion poblacion = new Poblacion();
        GestionLab.addPoblacion(poblacion, experimento);
        verify(experimento, times(1)).setPoblacionNueva(poblacion);
    }

    @Test
    void testDeletePoblacion() {
        Poblacion poblacion = new Poblacion();
        poblacion.setNombrePoblacion("Test");
        when(experimento.getPoblacionesList()).thenReturn(experimento.getPoblacionesList());

        GestionLab.deletePoblacion("Test", experimento);
        verify(experimento, times(1)).setNumPoblaciones(0);
    }

    @Test
    void testBuscarPoblacionFound() {
        Poblacion poblacion = new Poblacion();
        poblacion.setNombrePoblacion("Test");
        when(experimento.getPoblacionesList()).thenReturn(experimento.getPoblacionesList());

        Poblacion result = GestionLab.buscarPoblacion("Test", experimento);

        assertNotNull(result);
        assertEquals("Test", result.getNombrePoblacion());
    }

    @Test
    void testBuscarPoblacionNotFound() {
        Poblacion poblacion = new Poblacion();
        poblacion.setNombrePoblacion("Test");
        when(experimento.getPoblacionesList()).thenReturn(experimento.getPoblacionesList());

        assertThrows(RuntimeException.class, () -> GestionLab.buscarPoblacion("NotFound", experimento));
    }
}