package test.java;
import com.java.glen.DataManipulator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class DataManipulatorTests  {


    @Test
    public void verifyIsNumberAllDigits() {
        Boolean result = DataManipulator.isNumber("1234");
        assertTrue(result);
    }

    @Test
    public void verifyIsNumberAllDigitsLeadingZero() {
        Boolean result = DataManipulator.isNumber("01234");
        assertTrue(result);
    }

    @Test
    public void verifyIsNumberMixAlphaNumeric() {
        Boolean result = DataManipulator.isNumber("12d34");
        assertFalse(result);
    }

    @Test
    public void verifyIsNumberAllLetters() {
        Boolean result = DataManipulator.isNumber("asdg");
        assertFalse(result);
    }

    @Test
    public void verifyIsNumberEmptyString() {
        Boolean result = DataManipulator.isNumber("");
        assertFalse(result);
    }
}
