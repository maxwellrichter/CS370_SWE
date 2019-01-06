package edu.qc.seclass;

import org.junit.After;
import org.junit.Before;
import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class MyCustomStringTest {

    private MyCustomStringInterface mycustomstring;

    @Before
    public void setUp() {
        mycustomstring = new MyCustomString();
    }

    @After
    public void tearDown() {
        mycustomstring = null;
    }

    @Test
    public void testCountNumbers1() {
        mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        assertEquals(7, mycustomstring.countNumbers());
    }

    @Test
    public void testCountNumbers2() {
    	//Make sure that it reads the entire number as one no matter the size.
        mycustomstring.setString("454543534509909090943241234213444323");
        assertEquals(1, mycustomstring.countNumbers());


    }

    @Test
    public void testCountNumbers3() {
    	//Test to return 0 when there's no numbers.
    	mycustomstring.setString("adsfasdfadsASDFADSZERooO");
        assertEquals(0, mycustomstring.countNumbers());
    }

    @Test
    public void testCountNumbers4() {
    	//Test empty string case.
    	mycustomstring.setString("");
        assertEquals(0, mycustomstring.countNumbers());
    }

    @Test
    public void testCountNumbers5() {
    	//Test to include zeros and beginning and end characters.
    	mycustomstring.setString("8df00dfd9");
        assertEquals(3, mycustomstring.countNumbers());
    }

    @Test(expected = NullPointerException.class)
    public void testCountNumbers6() {
    	//Test when string is null.
    	mycustomstring.setString(null);
        assertEquals(0, mycustomstring.countNumbers());
    }

    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd1() {
        mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        assertEquals("d33p md1  i51,it", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(3, false));
    }

    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd2() {
        mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        assertEquals("'bt t0 6snh r6rh", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(3, true));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetEveryNthCharacterFromBeginningOrEnd3() {
    	//Test throw on n = 0    	
    		mycustomstring.setString("hello zero");
    		mycustomstring.getEveryNthCharacterFromBeginningOrEnd(0, true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetEveryNthCharacterFromBeginningOrEnd4() {
    	//Test throw on n < 0
		mycustomstring.setString("hello zero");
		mycustomstring.getEveryNthCharacterFromBeginningOrEnd(-34, true);
    }

    @Test(expected = NullPointerException.class)
    public void testGetEveryNthCharacterFromBeginningOrEnd5() {
    	//Test throw on s == null;
		mycustomstring.setString(null);
		mycustomstring.getEveryNthCharacterFromBeginningOrEnd(3, true);    
    }

    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd6() {
    	//Test function works on false.
    	mycustomstring.setString("AaBbCcDd");
    	assertEquals("abcd", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(2, false));
    }

    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd7() {
    	//Test that 1 doesn't change the string with false.
    	mycustomstring.setString("AaBbCcDd");
    	assertEquals("AaBbCcDd", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(1, false));    
    }

    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd8() {
    	//Test the function works on true.
    	mycustomstring.setString("AaBbCcDd");
    	assertEquals("ABCD", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(2, true));    
    }

    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd9() {
    	//Test that 1 doesn't change the string with true.
    	mycustomstring.setString("AaBbCcDd");
    	assertEquals("AaBbCcDd", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(1, true));
    }

    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd10() {
    	//Test empty string case.
    	mycustomstring.setString("");
    	assertEquals("", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(1, false));
    }

    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd11() {
    	//Test n > length on false
    	mycustomstring.setString("ABC");
    	assertEquals("", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(4, false));
    }

    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd12() {
    	//Test n > legnth on true;
    	mycustomstring.setString("ABC");
    	assertEquals("", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(4, true));
    }

    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd13() {
    	//Test to make sure we get the first char.
    	mycustomstring.setString("ABCD");
    	assertEquals("A", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(4, true));
    }

    @Test
    public void testGetEveryNthCharacterFromBeginningOrEnd14() {
    	//Test escape characters.
    	mycustomstring.setString("A\\nB");
    	assertEquals("\\B", mycustomstring.getEveryNthCharacterFromBeginningOrEnd(2, false));
    	
    }

    @Test
    public void testConvertDigitsToNamesInSubstring1() {
        mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        mycustomstring.convertDigitsToNamesInSubstring(17, 23);
        assertEquals("I'd b3tt3r put sZerome dOneSix1ts in this 5tr1n6, right?", mycustomstring.getString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConvertDigitsToNamesInSubstring2() {
    	//Test start > end throws.
    	mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
  		mycustomstring.convertDigitsToNamesInSubstring(17, 16);
    }

    @Test(expected = MyIndexOutOfBoundsException.class)
    public void testConvertDigitsToNamesInSubstring3() {
    	//Test start == 0 throws.
    	mycustomstring.setString("hello");
		mycustomstring.convertDigitsToNamesInSubstring(0, 1);
    }

    @Test(expected = MyIndexOutOfBoundsException.class)
    public void testConvertDigitsToNamesInSubstring4() {
    	//Test end > length
    	mycustomstring.setString("hello");
		mycustomstring.convertDigitsToNamesInSubstring(3, 6);
    }

    @Test(expected = NullPointerException.class)
    public void testConvertDigitsToNamesInSubstring5() {
    	//Test s == null
    	mycustomstring.setString(null);
		mycustomstring.convertDigitsToNamesInSubstring(1, 90);
    }

    @Test
    public void testConvertDigitsToNamesInSubstring6() {
    	//Test Indexing by 1
    	mycustomstring.setString("0");
        mycustomstring.convertDigitsToNamesInSubstring(1, 1);
        assertEquals("Zero", mycustomstring.getString());
    }

    @Test
    public void testConvertDigitsToNamesInSubstring7() {
    	//Test all int possibilities
    	mycustomstring.setString("0123456789");
        mycustomstring.convertDigitsToNamesInSubstring(1, 10);
        assertEquals("ZeroOneTwoThreeFourFiveSixSevenEightNine", mycustomstring.getString());
    }

    @Test
    public void testConvertDigitsToNamesInSubstring8() {
    	//Test that we keep all other characters.
    	mycustomstring.setString("p1p");
        mycustomstring.convertDigitsToNamesInSubstring(2, 2);
        assertEquals("pOnep", mycustomstring.getString());    }

}
