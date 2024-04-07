package test.GestionLabTest;

import gestionLab.GestionLab;
import laboratorio.Experimento;
import laboratorio.Poblacion;
import dataio.UserInput;
import medio.Luminosidad;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.text.ParseException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GestionLabTest {

    private GestionLab gestionLab;
    private Experimento experimento;
    private UserInput userInput;

    @BeforeEach
    void setUp() {
/*
        gestionLab = new GestionLab();
*/
        experimento = mock(Experimento.class);
        userInput = mock(UserInput.class);
    }

    @Test
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
    }

    @Test
    void testAddPoblacion() {
        Poblacion poblacion = new Poblacion();
        GestionLab.addPoblacion(poblacion, experimento);
        verify(experimento, times(1)).setPoblacionNueva(poblacion);
    }
}