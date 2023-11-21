package Klausur1;

import java.util.ArrayList;

public class PenguNums {

	public static void main(String[] args) {

		System.out.println(penguNumPositive(73)); // 7015254043203144209
		System.out.println(penguNumPositive(69)); // 612979045863284359

		System.out.println(penguNumNegative(-69)); // 612979045863284359
		System.out.println(penguNumNegative(-73)); // 7015254043203144209
		System.out.println(penguNumNegative(-70)); // -1127444240280152749

		System.out.println(penguNumIndex(612979045863284359L)); // 69
		System.out.println(penguNumIndex(7015254043203144209L)); // 73
		System.out.println(penguNumIndex(-1127444240280152749L)); // -70

		System.out.println(isPenguNumPositive(61297904586328435L)); // false
		System.out.println(isPenguNumPositive(7015254043203144210L)); // false
		System.out.println(isPenguNumPositive(7015254043203144209L)); // true
		System.out.println(isPenguNumPositive(612979045863284360L)); // false
		System.out.println(isPenguNumPositive(612979045863284359L)); // true

		System.out.println(isPenguNum(612979045863284360L)); // false
		System.out.println(isPenguNum(7015254043203144209L)); // true
		System.out.println(isPenguNum(7015254043203144210L)); // false
		System.out.println(isPenguNum(612979045863284358L)); // false
	}

	/**
	 * Gets the n-th penguin number for positive n
	 * 
	 * @param n the index of the penguin number, positive including zero
	 * @return n-th penguin number
	 */
	public static long penguNumPositive(int n) {
		/*
		ArrayList<Long> answerContainer = new ArrayList<>();
		answerContainer.add(0L);
		answerContainer.add(1L);
		answerContainer.add(1L);

		for(int i = 3; i <= n; i++){
			long value = (answerContainer.get(i - 1) + answerContainer.get(i - 2) + answerContainer.get(i - 3));
			answerContainer.add(value);
		}

		return answerContainer.get(n);
		*/


		if (n == 0) {
			return 0;
		} else if (n == 1 || n == 2) {
			return 1;
		}

		long a = 0;
		long b = 1;
		long c = 1;

		for (int i = 2; i < n; i++) {
			long next = a + b + c;
			a = b;
			b = c;
			c = next;
		}

		return c;
	}

	/**
	 * Gets the n-th penguin number for negative n
	 * 
	 * @param n the index of the penguin number, strictly negative
	 * @return n-th penguin number
	 */
	public static long penguNumNegative(int n) {
	    int positiveValue = n * -1;

		long result = penguNumPositive(positiveValue);

		if(positiveValue % 2 == 0){
			result *= -1;
		}

		return result;
	}

	/**
	 * Gets the n-th penguin number
	 * 
	 * @param n the index of the penguin number
	 * @return n-th penguin number
	 */
	public static long penguNum(int n) {
		long result = 0;
	    if(n < 0){
			result = penguNumNegative(n);
		}
		else {
			result = penguNumPositive(n);
		}
		return result;
	}

	/**
	 * Gets the index of the given penguin number
	 * 
	 * @param n the penguin number
	 * @return the index of the penguin number
	 */
	public static int penguNumIndex(long n) {
		boolean found = false;
		boolean isNegative = false;

		if(n < 0){
			isNegative = true;
		}

		int limit = 100;
		int index = 0;

		// case negative input
	    while(!found && isNegative){
			for(int i = -1; i > -limit; i--){
				long result = penguNumNegative(i);

				if(result == n){
					index = i;
					found = true;
					break;
				}
			}

			limit += 100;
		}

		// case positive input
		while(!found){
			for(int i = 0; i < limit; i++){
				long result = penguNumPositive(i);

				if(result == n){
					index = i;
					found = true;
					break;
				}
			}

			limit += 100;
		}

		return index;
	}

	/**
	 * Gets if n is a penguin number for positive n
	 * 
	 * @param n the number that should be checked, positive including zero
	 * @return true if n is penguin number, false otherwise
	 */
	public static boolean isPenguNumPositive(long n) {
		/*
		ArrayList<Long> answerContainer = new ArrayList<>();
		answerContainer.add(0L);
		answerContainer.add(1L);
		answerContainer.add(1L);

		if(n == 0 || n == 1){
			return true;
		}

		boolean found = false;
		int limit = 100;
		int currentPosition;
		while(!found){
			for(currentPosition = 3; currentPosition <= limit; currentPosition++){
				long value = answerContainer.get(currentPosition - 1) + answerContainer.get(currentPosition - 2) + answerContainer.get(currentPosition - 3);
				answerContainer.add(value);

				if(n == value){
					return true;
				}

				if(value > n){
					found = true;
					return false;
				}
			}
			currentPosition = limit;
			limit += 100;
		}

		return false;
		*/

		if (n == 0 || n == 1) {
			return true;
		}

		long a = 0;
		long b = 1;
		long c = 1;

		do {
			long next = a + b + c;

			// detect overflow
			if (next < 0) {
				return false;
			}

			a = b;
			b = c;
			c = next;
		} while (c < n);

		return c == n;
	}

	/**
	 * Gets if n is a penguin number
	 * 
	 * @param n the number that should be checked
	 * @return true if n is penguin number, false otherwise
	 */
	public static boolean isPenguNum(long n) {
		// edge cases
		if(n == -1 || n == 1 || n == 0){
			return true;
		}

		long new_N = n;
		if(n < 0){
			new_N *= -1;
		}

        boolean isValid = isPenguNumPositive(new_N);

		if(isValid){
			int index = penguNumIndex(new_N);
			long realValue = penguNum(index);

			if(realValue != n){
				if(index % 2 == 0){
					realValue *= -1;
					if(realValue != n){
						return false;
					}
					else {
						return true;
					}
				}
				else {
					return false;
				}
			}
			else {
				return true;
			}
		}
		else {
			return false;
		}
	}

}