package test.dataioTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FileManagerTest {

    @Mock
    private BufferedReader bufferedReader;

    @InjectMocks
    private FileManager fileManager;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAbrirArchivo() throws IOException {
        String input = "Mi Experimento;30\nPoblación A;100;25.5;ALTA;2024-04-01;50.0\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        when(bufferedReader.readLine()).thenReturn("Mi Experimento;30", "Población A;100;25.5;ALTA;2024-04-01;50.0", null);

        Experimento experimento = fileManager.abrirArchivo(inputStream);

        assertEquals("Mi Experimento", experimento.getNombreExperimento());
        assertEquals(30, experimento.getDias());

        Poblacion poblacion = experimento.getPoblacionesList().get(0);
        assertEquals("Población A", poblacion.getNombrePoblacion());
        assertEquals(100, poblacion.getNumInicialBacterias());
        assertEquals(25.5f, poblacion.getTemperatura());
        assertEquals(Luminosidad.luminosidad.ALTA, poblacion.getLuminosidad());
        assertEquals(LocalDate.of(2024, 4, 1), poblacion.getFechaInicio());
        assertEquals(50.0f, poblacion.getComida().getCantidadInicial());
    }
}
