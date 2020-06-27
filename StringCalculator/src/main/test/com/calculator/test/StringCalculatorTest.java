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
		int res=StringCalculator.add("1,2\n3\n5\n10");
		Assert.assertEquals(21, res);
	}
}
