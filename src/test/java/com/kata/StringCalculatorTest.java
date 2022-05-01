package com.kata;



import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;




public class StringCalculatorTest {

    //  Step 1 ------------------

    @Test
    public void emptyStringShouldReturnZero() {
        StringCalculator stringCalculator=new StringCalculator();
        Assert.assertEquals(0, stringCalculator.add(""));
    }

    @Test
    public void oneNumberShouldReturnItself() {
        StringCalculator stringCalculator=new StringCalculator();
        Assert.assertEquals(1, stringCalculator.add("1"));
    }


    @Test
    public void twoNumbersShouldBeAdded() {
        StringCalculator stringCalculator=new StringCalculator();
        Assert.assertEquals(3, stringCalculator.add("2,1"));
    }

    @Test
    public void calculateTwoToOneWithNewlines_expectThree() {
        StringCalculator stringCalculator=new StringCalculator();
        Assert.assertEquals(3, stringCalculator.add("2\n1"));
    }

    @Test
    public void moreDigitsSupported() {
        StringCalculator stringCalculator = new StringCalculator();
        assertEquals(77, stringCalculator.add("22,55"));
    }

    //  Step 2 ------------------
    @Test
    public void allowNnumersAsInput() {
        StringCalculator stringCalculator = new StringCalculator();
        assertEquals(45, stringCalculator.add("1,2,3,4,5,6,7,8,9"));
    }

    //  Step 3 ------------------
    @Test
    public void supportNewLineAsSeparator() {
        StringCalculator stringCalculator=new StringCalculator();
        assertEquals(6, stringCalculator.add("1\n2,3"));
    }
    @Test
    public void supportNewLineAsSeparator2() {
        StringCalculator stringCalculator=new StringCalculator();
        assertEquals(1, stringCalculator.add("1,\n"));
    }

    //  Step 4 ------------------

    @Test
    public void SupportDifferentDelimiters() {
        StringCalculator stringCalculator=new StringCalculator();
        Assert.assertEquals(3, stringCalculator.add("//[x]\n2x1"));
    }

    //  Step 5 ------------------
    @Test
    public void negativeNotSupported() {
        StringCalculator stringCalculator=new StringCalculator();
        try {
            stringCalculator.add("-1");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("negatives not allowed (-1)", e.getMessage());
        }
    }

    @Test
    public void calculateTwoNegativeValue_exception() {
        StringCalculator stringCalculator=new StringCalculator();
        try {
            stringCalculator.add("-1,-2");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("negatives not allowed (-1,-2)", e.getMessage());
        }
    }

    //  Step 6 ------------------

    @Test
    public void biggerThan1000ShouldIgnored() {
        StringCalculator stringCalculator=new StringCalculator();
        Assert.assertEquals(2, stringCalculator.add("2,1001"));
    }

    //  Step 7 ------------------

    @Test
    public void delimitersLongerThanOneCharStillAccepted() {
        StringCalculator stringCalculator=new StringCalculator();
        Assert.assertEquals(3, stringCalculator.add("//[xx]\n2xx1"));
    }

    //  Step 8 ------------------
    @Test
    public void MltipleDelimitersAllUsed() {
        StringCalculator stringCalculator=new StringCalculator();
        Assert.assertEquals(6, stringCalculator.add("//[x][y]\n2x1y3"));
    }






}
