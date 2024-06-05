package tests.dataio;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import dataio.UserInput;
import medio.Luminosidad;
import java.io.ByteArrayInputStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Clase de pruebas unitarias para la clase UserInput.
 * Se prueban los métodos readFloat, readString, readInt, readLuminosidad y readDate.
 * Se simula la entrada de datos del usuario con un ByteArrayInputStream
 * Se comprueba que los métodos devuelven el valor esperado.
 * @author Ana Ventura-Traveset

 */
public class UserInputTest {

        void inputDeUsuario(String data) {
            ByteArrayInputStream in = new ByteArrayInputStream(data.getBytes());
            System.setIn(in);
        }

        @Test
        void testReadFloat() {
            inputDeUsuario("10,5");
            float result = UserInput.readFloat("Introduce un float que sea `10,5`:");
            assertEquals(10.5, result, 0.001);
        }

        @Test
        void testReadString() {
            inputDeUsuario("test");
            String result = UserInput.readString("Introduce un string que sea `test`:");
            assertEquals("test", result);
        }

        @Test
        void testReadInt() {
            inputDeUsuario("10");
            int result = UserInput.readInt("Introduce un entero que sea `10`:");
            assertEquals(10, result);
        }

        @Test
        void testReadLuminosidad() {
            inputDeUsuario("ALTA");
            Luminosidad.luminosidad result = UserInput.readLuminosidad("Introduce una luminosidad que sea `ALTA`:");
            assertEquals(Luminosidad.luminosidad.ALTA, result);
        }

        @Test
        void testReadDate() {
            inputDeUsuario("2023.09.09");
            LocalDate result = UserInput.readDate("Introduce una fecha que sea `2023.09.09`:");
            assertEquals(LocalDate.of(2023, 9, 9), result);
        }
    }