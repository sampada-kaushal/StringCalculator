package com.calculator.test;

import org.junit.Assert; 
import org.junit.Test;
import com.calculator.java.StringCalculator;



public class StringCalculatorTest {

	@Test
	public void whenNoNumbersArePassed() {
		int res=StringCalculator.add("");
		//fail("Not yet implemented");
		Assert.assertEquals(0, res);
	}

}
