package com.calculator.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import javax.management.RuntimeErrorException;

public class StringCalculator {
	public static int add(String numbers) {

		String[] numArray = new String[100];
		ArrayList<Integer> negativeNumList = new ArrayList<Integer>();
		int result = 0;
//Custom delimiter logic
		if (numbers.startsWith("//") && !(numbers.contains("["))) {
			String default_delimiter = Character.toString(numbers.charAt(2));
			numArray = numbers.split(default_delimiter);
		} 
		else if(numbers.startsWith("//") && numbers.contains("[")) {
			String anyLengthDelimiter=numbers.substring(numbers.indexOf("[")+1,numbers.indexOf("]"));
			System.out.println(""+anyLengthDelimiter);
			String temp=""+anyLengthDelimiter.charAt(0);
			String newAnyLengthDelimiter="";
			
			//Checking if delimiter entered is a meta character or not
			
			final char[] escapeChars = { '<', '(', '[', '{', '\\', '^', '-', '=', '$', '!', '|', ']', '}', ')', '?', '*', '+', '.', '>' };
			for (char escapeChar : escapeChars) {
		        if (anyLengthDelimiter.charAt(0) == escapeChar) {
		            temp= "\\" + anyLengthDelimiter.charAt(0);}
		    }
			for (int i=0;i<anyLengthDelimiter.length();i++) {
				newAnyLengthDelimiter+=temp;
			}
			numArray=numbers.split(newAnyLengthDelimiter);		
		}
		
		else {
			numArray = numbers.split(",|\n");
		}
//Addition logic
		if (numbers.isEmpty()) {
			result = 0;
		} 
		else {
			if (numbers.startsWith("//")&& !(numbers.contains("["))) {
				for (int i = 1; i < numArray.length; i++) 
					{result += Integer.parseInt(numArray[i].trim());
					if(Integer.parseInt(numArray[i].trim())<0) { 
							negativeNumList.add(Integer.parseInt(numArray[i].trim())); 
					}
					//Logic for numbers>1000
					if(Integer.parseInt(numArray[i].trim())>1000)
						result=result-Integer.parseInt(numArray[i].trim());
				}
				}
			else if(numbers.startsWith("//") && numbers.contains("[")) {
				for (int i = 1; i < numArray.length; i++) {
					if(numArray[i].contains("]") || numArray[i].contains("\n"))
						numArray[i]=numArray[i].replace("]", "").replace("\\n", "");
					result += Integer.parseInt(numArray[i].trim());
					if(Integer.parseInt(numArray[i].trim())<0) { 
						negativeNumList.add(Integer.parseInt(numArray[i].trim())); 
				}
				//Logic for numbers>1000
				if(Integer.parseInt(numArray[i].trim())>1000)
					result=result-Integer.parseInt(numArray[i].trim());
				}
			}
			
			else {
			for (String number : numArray) // Refactored to handle extra space for any amount of numbers
				{result += Integer.parseInt(number.trim());
				if(Integer.parseInt(number.trim())<0)
				{
					negativeNumList.add(Integer.parseInt(number.trim())); 
				}
				//Logic for numbers>1000
				if(Integer.parseInt(number.trim())>1000)
					result=result-Integer.parseInt(number.trim());
				}
				}
			
		
//Negative Exception handling
			if(negativeNumList.size()>0) {
				 throw new RuntimeException("Negatives not allowed: "+negativeNumList);
			}
		}
		return result;
	
		
	
	}
}
