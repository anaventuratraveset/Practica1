package test.dataioTest;

import dataio.UserInput;
import medio.Luminosidad;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserInputTest {
    void inputDeUsuario(String data){
        ByteArrayInputStream in = new ByteArrayInputStream(data.getBytes());
        System.setIn(in);
    }
    @Test
    void testReadFloat() {
        inputDeUsuario("10,5");
        float result = UserInput.readFloat("Enter a float:");
        assertEquals(10.5, result, 0.001);
    }
    @Test
    void testReadString() {
        inputDeUsuario("test");
        String result = UserInput.readString("Enter a string:");
        assertEquals("test", result);
    }
    @Test
    void testReadInt() {
        inputDeUsuario("10");
        int result = UserInput.readInt("Enter an integer:");
        assertEquals(10, result);
    }
    @Test
    void testReadLuminosidad() {
        inputDeUsuario("ALTA");
        Luminosidad.luminosidad result = UserInput.readLuminosidad("Enter luminosidad:");
        assertEquals(Luminosidad.luminosidad.ALTA, result);
    }
    @Test
    void testReadDate() {
        inputDeUsuario("2023.09.09");
        LocalDate result = UserInput.readDate("Enter a date:");
        assertEquals(LocalDate.of(2023, 9, 9), result);
    }
}