package com.calculator.test;

import org.junit.Assert; 
import org.junit.Test;
import com.calculator.java.StringCalculator;



public class StringCalculatorTest {

	@Test
	public void whenNoNumbersArePassed() {
		int res=StringCalculator.add("");
		Assert.assertEquals(0, res);
	}
	@Test
	public void whenOneNumberIsPassed() {
		int res=StringCalculator.add("1");
		Assert.assertEquals(1, res);
	}
	@Test
	public void whenTwoNumbersArePassed() {
		int res=StringCalculator.add("1,2");
		Assert.assertEquals(3, res);
	}
	@Test
	public void whenMultipleNumbersArePassed() {
		int res=StringCalculator.add("1 ,2,5,5,10");
		Assert.assertEquals(23, res);
	}
	@Test
	public void whenNewLineIsAlsoPassedAsDelimiter() {
		int res=StringCalculator.add("1,2\n3");
		Assert.assertEquals(6, res);
	}
	@Test
	public void whenDefaultDelimiterIsSet() {
		int res=StringCalculator.add("//;\n1;2;3");
		Assert.assertEquals(6, res);
	}
	@Test
	public void whenNegativesAreNotAllowedExceptionIsThrown() {
		RuntimeException ex=null;
		try {
		StringCalculator.add("-1,-2,3");
		}
		catch(RuntimeException e) {
			ex=e;
		}
		Assert.assertNotNull(ex);
		Assert.assertEquals("Negatives not allowed: [-1, -2]",ex.getMessage());
	}
	@Test
	public void ignoringNumbersGreaterThanThousand() {
		int res=StringCalculator.add("1,1001\n3");
		Assert.assertEquals(4, res);
	}
	@Test
	public void handlingDelimitersOfAnyLength() {
		int res=StringCalculator.add("//[$$$$$]\n1$$$$$2$$$$$5");
		Assert.assertEquals(8, res);
	}
}
