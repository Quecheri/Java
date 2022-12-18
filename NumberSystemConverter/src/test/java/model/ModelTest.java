package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
/**
 *
 * @author Quecheri
 * @version 1.1
 */
public class ModelTest {

    /**
     * model instance for tests to use
     */
    private Model mod=new Model();
    

    /**
     * Test Cheks whether provided values are corectly recognized as roman numbers
     * @param value String - roman number
     */
    @ParameterizedTest
    @ValueSource(strings = {"I","III","VI","XV","X","L","LX","C","CX","MCX","MMMCMXCIX"})
    public void isRomanNumberTest(String value) {
        mod.setNumber(value);
        assertTrue (mod.isRomanNumber(),"Tests should be fine");
    }
    
    /**
     * Test Cheks whether provided values are corectly not recognized as roman numbers
     * @param value String - not proper roman number
     */
    @ParameterizedTest
    @ValueSource(strings = {"VF","XM","MMMMXM","123","MVM"})
    public void isRomanNumberFailTest(String value) {
        mod.setNumber(value);
        assertFalse (mod.isRomanNumber(),"Tests should fail");
    }

     /**
     * Test Cheks whether provided values are corectly recognized as arabic numbers
     * @param value String - proper arabic number
     */
    @ParameterizedTest
    @ValueSource(strings = {"12", "1","13","10000","10","-9","-10000"})
    public void isArabicNumberTest(String value) {
        mod.setNumber(value);
        assertTrue (mod.isArabicNumber(),"Tests should be fine");
    }
    
    /**
     * Test Cheks whether provided values are corectly not recognized as arabic numbers
     * @param value String - not proper arabic number
     */
    @ParameterizedTest
    @ValueSource(strings = {"!@#@2","1a" ,"sd2","aS","ad","-a23","sc","asd","0"})
    public void isArabicNumberFailTest(String value) {
        mod.setNumber(value);
        assertFalse (mod.isArabicNumber(),"Tests should fail");

    }


    /**
     * Test Cheks whether provided value is corectly converted to arabic number
     * with inproper input method behavior is undefined
     * @param input - Proper roman number
     * @param expected - expected result of conversion
     */
    @ParameterizedTest
    @CsvSource({"X,10", "I,1", "MMV,2005","MMMCMXCIX,3999","XXX,30"})
    public void changeToArabicTest(String input, String expected) {
        mod.setNumber(input);
        mod.changeToArabic();
        assertEquals(expected,mod.getConvertedNumber(), input+" Should convert to: "+expected+" but result was "+mod.getConvertedNumber());
    }
    
    /**
     * Test Cheks whether provided value is corectly not converted to arabic number
     * with inproper input method behavior is undefined
     * @param input - inproper roman number
     * @param expected - expected result of conversion
     */
    @ParameterizedTest
    @CsvSource({"VIII,VIII", "I,2", "MVM,2005","XXMM,2020","XXIXC,39"})
    public void changeToArabicFailTest(String input, String wrong) {
        mod.setNumber(input);
        mod.changeToArabic();
        assertNotEquals(wrong,mod.getConvertedNumber(), input+" Should't convert to: "+wrong);
    }

     /**
     * Test Cheks whether provided value is corectly converted to roman number
     * with inproper input method behavior is undefined
     * @param input - proper arabic number
     * @param expected - expected result of conversion
     */
    @ParameterizedTest
    @CsvSource({"1,I", "3999,MMMCMXCIX", "2005,MMV","7,VII","30,XXX"})
    public void changeToRomanTest(String input, String expected) {
        mod.setNumber(input);
        try
        {   
            mod.changeToRoman();
            assertEquals(expected,mod.getConvertedNumber(), input+" Should convert to: "+expected+" but result was "+mod.getConvertedNumber());
        }
        catch (model.NumberOutOfBoundsExeption ex){fail("Test Should't fail on NumberOutOfBoundsExeption");  }
            catch (NumberFormatException ex){fail("Test Should't fail on NumberFormatException");}
        
    }
    
    /**
     * Test Cheks whether exceptions are corectly thrown
     * with inproper input method behavior is undefined
     * @param input - proper arabic number outside of (0,4000) range
     */
    @ParameterizedTest
    @ValueSource(strings = {"-100","-1" ,"999999","0","4000"})
    public void changeToRomanExceptionTest(String input) {
        mod.setNumber(input);
        try
        {   
            mod.changeToRoman();
            fail("An exception should be thrown with this input");
        }
        catch (model.NumberOutOfBoundsExeption ex)
        {
            
        }
        catch (NumberFormatException ex)
        {
            
        }
        
    }
     /**
     * Test Cheks whether provided value is corectly not converted to roman number
     * with inproper input method behavior is undefined
     * @param input - proper arabic number
     * @param expected - expected result of conversion
     */
    @ParameterizedTest
    @CsvSource({"132,CXXLII", "3999,MMMIM", "2000,MDD","90,LXL","40,XXXX"})
    public void changeToRomanFailTest(String input, String expected) {
        mod.setNumber(input);
        try
        {   
            mod.changeToRoman();
            assertNotEquals(expected,mod.getConvertedNumber(), input+" Should convert to: "+expected+" but result was "+mod.getConvertedNumber());
        }
        catch (model.NumberOutOfBoundsExeption ex){fail("Test Should't fail on NumberOutOfBoundsExeption");  }
            catch (NumberFormatException ex){fail("Test Should't fail on NumberFormatException");}
        
    }

}
