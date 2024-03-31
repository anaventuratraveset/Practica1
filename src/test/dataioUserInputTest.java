package test;

import dataio.UserInput;
import medio.Luminosidad;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Ana Ventura-Traveset
 *
 * test de la clase UserInput del paquete dataio
 */
public class dataioUserInputTest {

    void provideInputString(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }
    @Test
    public void userInputReadString_returns_string(){
        provideInputString("hello");
        String input = UserInput.readString("Pedimos string");
        assertEquals("hello", input);
    }
    @Test
    public void userInputReadInt_returns_int(){
        provideInputString("1");
        int input = UserInput.readInt("Pedimos int");
        assertEquals(1, input);
    }
    @Test
    public void userInputReadFloat_returns_float(){
        provideInputString("1.123");
        float input = UserInput.readFloat("Pedimos float");
        assertEquals(1.123, input, 0.001);
    }
    @Test
    public void userInputReadLumin_returns_lumin(){
        provideInputString("ALTA");
        Luminosidad.luminosidad input1 = UserInput.readLuminosidad("Pedimos lumin1");
        assertEquals(Luminosidad.luminosidad.ALTA, input1);
        provideInputString("MEDIA");
        Luminosidad.luminosidad input2 = UserInput.readLuminosidad("Pedimos lumin2");
        assertEquals(Luminosidad.luminosidad.MEDIA, input2);
        provideInputString("BAJA");
        Luminosidad.luminosidad input3 = UserInput.readLuminosidad("Pedimos lumin3");
        assertEquals(Luminosidad.luminosidad.BAJA, input3);
    }
    @Test
    public void userInputReadDate_returns_Date() throws ParseException {
        provideInputString("2008.01.03");
        LocalDate input = UserInput.readDate("Pedimos fecha");
        LocalDate myDate = new SimpleDateFormat("yyyy.MM.dd").parse("2008.01.03").toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        assertEquals(myDate, input);
    }
}
