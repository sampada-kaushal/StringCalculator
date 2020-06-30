package com.calculator.java;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
	public static int add(String numbers) {

		String[] numArray = new String[100];
		ArrayList<Integer> negativeNumList = new ArrayList<Integer>();
		int result = 0;

// Counting multiple occurrence of "]" to count number of multiple delimiters given
		int lastIndex = 0;
		int count = 0;
		String findStr = "]";
		while (lastIndex != -1) {

			lastIndex = numbers.indexOf(findStr, lastIndex);

			if (lastIndex != -1) {
				count++;
				lastIndex += findStr.length();
			}
		}

//Custom delimiter logic
		if (numbers.startsWith("//") && !(numbers.contains("["))) {
			String default_delimiter = Character.toString(numbers.charAt(2));
			numArray = numbers.split(default_delimiter);
		}

//Custom delimiter logic with multiple delimiters and any length		
		else if (numbers.startsWith("//") && numbers.contains("[") && count > 1) {
			String mulDelimiter="";
			String mulDelimiterTemp = "";
			String splitString = "";
			int  lengthOfDelimiters;

			Pattern p = Pattern.compile("\\[([^\\]]+)\\]");
			Matcher m = p.matcher(numbers);
			while (m.find()) {
					mulDelimiter = m.group(1);
					mulDelimiterTemp = "" + mulDelimiter.charAt(0);
					lengthOfDelimiters = mulDelimiter.length();

					// Checking if delimiter entered is a meta character or not
					final char[] escapeChars = { '<', '(', '[', '{', '\\', '^', '-', '=', '$', '!', '|', ']', '}', ')',
							'?', '*', '+', '.', '>' };
					for (char escapeChar : escapeChars) {
						if (mulDelimiter.charAt(0) == escapeChar) {
							mulDelimiterTemp = "\\" + mulDelimiter.charAt(0);
							for (int j = 0; j < lengthOfDelimiters; j++)
								mulDelimiter += mulDelimiterTemp;
							mulDelimiter = mulDelimiter.substring(lengthOfDelimiters);
						}
					}

					splitString += mulDelimiter + "|";
			}
			splitString = splitString.substring(0, splitString.length() - 1);
			numArray = numbers.split(splitString);
		}

//Custom delimiter logic with any length and one delimiter		
		else if (numbers.startsWith("//") && numbers.contains("[")) {
			String anyLengthDelimiter = numbers.substring(numbers.indexOf("[") + 1, numbers.indexOf("]"));
			String temp = "" + anyLengthDelimiter.charAt(0);
			String newAnyLengthDelimiter = "";

			// Checking if delimiter entered is a meta character or not
			final char[] escapeChars = { '<', '(', '[', '{', '\\', '^', '-', '=', '$', '!', '|', ']', '}', ')', '?',
					'*', '+', '.', '>' };
			for (char escapeChar : escapeChars) {
				if (anyLengthDelimiter.charAt(0) == escapeChar)
					temp = "\\" + anyLengthDelimiter.charAt(0);
			}
			for (int i = 0; i < anyLengthDelimiter.length(); i++)
				newAnyLengthDelimiter += temp;
			numArray = numbers.split(newAnyLengthDelimiter);
		}

		// Splitting with comma and newline only; default case
		else
			numArray = numbers.split(",|\n");

//Addition logic
		if (numbers.isEmpty())
			result = 0;

		else {
			if (numbers.startsWith("//") && !(numbers.contains("["))) {
				for (int i = 1; i < numArray.length; i++) {
					result += Integer.parseInt(numArray[i].trim());
					if (Integer.parseInt(numArray[i].trim()) < 0)
						negativeNumList.add(Integer.parseInt(numArray[i].trim()));

					// Logic for numbers>1000
					if (Integer.parseInt(numArray[i].trim()) > 1000)
						result = result - Integer.parseInt(numArray[i].trim());
				}
			}

			else if (numbers.startsWith("//") && numbers.contains("[") && count > 1) {
				for (int i = count; i < numArray.length; i++) {
					if (numArray[i].contains("]") || numArray[i].contains("\n"))
						numArray[i] = numArray[i].replace("]", "").replace("\\n", "");

					result += Integer.parseInt(numArray[i].trim());
					if (Integer.parseInt(numArray[i].trim()) < 0)
						negativeNumList.add(Integer.parseInt(numArray[i].trim()));

					// Logic for numbers>1000
					if (Integer.parseInt(numArray[i].trim()) > 1000)
						result = result - Integer.parseInt(numArray[i].trim());
				}
			}

			else if (numbers.startsWith("//") && numbers.contains("[")) {
				for (int i = 1; i < numArray.length; i++) {
					if (numArray[i].contains("]") || numArray[i].contains("\n"))
						numArray[i] = numArray[i].replace("]", "").replace("\\n", "");

					result += Integer.parseInt(numArray[i].trim());
					if (Integer.parseInt(numArray[i].trim()) < 0)
						negativeNumList.add(Integer.parseInt(numArray[i].trim()));

					// Logic for numbers>1000
					if (Integer.parseInt(numArray[i].trim()) > 1000)
						result = result - Integer.parseInt(numArray[i].trim());
				}
			}

			else {
				for (String number : numArray) { // Refactored to handle extra space for any amount of numbers
					result += Integer.parseInt(number.trim());
					if (Integer.parseInt(number.trim()) < 0)
						negativeNumList.add(Integer.parseInt(number.trim()));

					// Logic for numbers>1000
					if (Integer.parseInt(number.trim()) > 1000)
						result = result - Integer.parseInt(number.trim());
				}
			}

//Negative Exception handling
			if (negativeNumList.size() > 0)
				throw new RuntimeException("Negatives not allowed: " + negativeNumList);

		}
		return result;

	}
}
