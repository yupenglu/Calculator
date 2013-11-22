/**
 * CIT-591 Assignment_11 Calculator
 * @author Christopher Ivey
 * @author Yupeng Lu
 */
package calculator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CalculatorChipTest {

    private CalculatorChip chip;
    
    @Before
    public void setUp() throws Exception {
        chip = new CalculatorChip();
        chip.initialize();
    }

    @Test
    public void testClear() {
        assertEquals("0", chip.clear());
    }

    @Test
    public void testMemClear() {
        chip.setM(Double.parseDouble("3"));
        chip.setResult(118);
        assertEquals("118", chip.memClear());
        assertTrue(0 == chip.getM());
    }

    @Test
    public void testMemRead() {
        chip.setM(Double.parseDouble("3"));
        assertEquals("3", chip.memRead());
    }

    @Test
    public void testMemPlus() {
        chip.setResult(118);
        assertEquals("118", chip.memPlus());
        assertTrue(118 == chip.getM());
    }

    @Test
    public void testMemMinus() {
        chip.setResult(-118);
        assertEquals("-118", chip.memPlus());
        assertTrue(-118 == chip.getM());
    }

    @Test
    public void testDigit() {
        chip.setEnterX(true);
        assertEquals("1", chip.digit(1));
        assertEquals("18", chip.digit(8));
        chip.setEnterX(false);
        assertEquals("5", chip.digit(5));
        assertTrue(18 == chip.getX());
        assertEquals("55", chip.digit(5));
    }

    @Test
    public void testDecimalPoint() {
        chip.setEnterX(true);
        chip.setX(11);
        assertEquals("11.", chip.decimalPoint());
        chip.digit(8);
        assertTrue(11.8 == chip.getX());
    }

    @Test
    public void testAdd() {
        chip.setX(110);
        chip.setY(8);
        chip.setEnterX(false);
        chip.setCanCalculate(true);
        chip.setPrevOpn("subtract");
        assertEquals("102", chip.add());
        assertTrue(102 == chip.getResult());
        assertEquals("add", chip.getPrevOpn());
    }

    @Test
    public void testSubtract() {
        chip.setX(110);
        chip.setY(8);
        chip.setEnterX(false);
        chip.setCanCalculate(true);
        chip.setPrevOpn("add");
        assertEquals("118", chip.subtract());
        assertTrue(118 == chip.getResult());
        assertEquals("subtract", chip.getPrevOpn());
    }

    @Test
    public void testMultiply() {
        chip.setX(16);
        chip.setY(8);
        chip.setEnterX(false);
        chip.setCanCalculate(true);
        chip.setPrevOpn("divide");
        assertEquals("2", chip.multiply());
        assertTrue(2 == chip.getResult());
        assertEquals("multiply", chip.getPrevOpn());
    }

    @Test
    public void testDivide() {
        chip.setX(16);
        chip.setY(4);
        chip.setEnterX(false);
        chip.setCanCalculate(true);
        chip.setPrevOpn("multiply");
        assertEquals("64", chip.divide());
        assertTrue(64 == chip.getResult());
        assertEquals("divide", chip.getPrevOpn());
    }

    @Test
    public void testEquals() {
        chip.setX(16);
        chip.setY(4);
        chip.setEnterX(false);
        chip.setPrevOpn("divide");
        assertEquals("4", chip.equals());
        assertTrue(4 == chip.getResult());
        assertEquals("divide", chip.getPrevOpn());
        assertTrue(chip.getEqualCalled());
    }

    @Test
    public void testDoEquals() {
        chip.setDoAC(false);
        chip.setX(16);
        chip.setY(4);
        chip.setEnterX(false);
        chip.setPrevOpn("divide");
        assertEquals("4", chip.doEquals());
        assertTrue(4 == chip.getResult());
        assertEquals("divide", chip.getPrevOpn());
        assertTrue(chip.getEqualCalled());
        assertTrue(chip.isDoAC());
    }

    @Test
    public void testSqrt() {
        chip.setX(16);
        assertEquals("4", chip.sqrt());
        chip.setEnterX(false);
        chip.setY(25);
        chip.setCanCalculate(true);
        assertEquals("5", chip.sqrt());
    }

    @Test
    public void testPercent() {
        chip.setX(16);
        chip.setResult(16);
        assertEquals("0.16", chip.percent());
        chip.setEnterX(false);
        chip.setY(25);
        chip.setResult(25);
        chip.setCanCalculate(true);
        chip.setPrevOpn("add");
        assertEquals("0.04", chip.percent());
    }

    @Test
    public void testInvert() {
        chip.setX(5);
        assertEquals("0.2", chip.invert());
        chip.setEnterX(false);
        chip.setY(10);
        chip.setCanCalculate(true);
        assertEquals("0.1", chip.invert());
    }

    @Test
    public void testChangeSign() {
        chip.setResult(5);
        assertEquals("-5", chip.changeSign());
        assertEquals("5", chip.changeSign());

    }
}