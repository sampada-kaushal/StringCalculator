package com.calculator.java;

import java.util.ArrayList;
import java.util.Arrays;

public class StringCalculator {
	public static int add(String numbers) {

		String[] numArray = new String[1000];
		int result = 0;
//Custom delimiter logic
		if (numbers.startsWith("//")) {
			String default_delimiter = Character.toString(numbers.charAt(2));
			numArray = numbers.split(default_delimiter);
		} 
		else {
			numArray = numbers.split(",|\n");
		}
//Addition logic
		if (numbers.isEmpty()) {
			result = 0;
		} 
		else {
			if (numbers.startsWith("//")) {
				for (int i = 1; i < numArray.length; i++) 
					result += Integer.parseInt(numArray[i].trim());
				}
			
			else {
			for (String number : numArray) // Refactored to handle extra space for any amount of numbers
				result += Integer.parseInt(number.trim());
		}
			}
		return result;
	}
}
