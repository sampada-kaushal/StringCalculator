package com.calculator.java;

public class StringCalculator {
	public static int add(String numbers)
	{
		String[] numArray=numbers.split(",|\n");
		int result=0;
		if(numbers.isEmpty()) {
			result=0;
		}
		else {
			for(String number: numArray) // Refactored to handle extra space for any amount of numbers
				result+=Integer.parseInt(number.trim());
			}
		return result;
	}
}
