package com.calculator.java;

public class StringCalculator {
	public static int add(String numbers)
	{
		String[] numArray=numbers.split(",");
		int result=0;
		if(numbers.isEmpty()) {
			result=0;
		}
		else {
			for(String number: numArray) 
				result+=Integer.parseInt(number);
			}
		return result;
	}
}