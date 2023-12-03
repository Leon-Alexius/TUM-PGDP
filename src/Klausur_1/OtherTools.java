package Klausur_1;

import java.util.Scanner;

public class OtherTools {
    /*
    ====================================================================================================================
                                                      Math
    ====================================================================================================================
     */
    /**
     * <ol>
     *      <li>Return a % b in always positive value</li>
     *      <li>Handles all possible scenario (negative a || b || a && b)</li>
     * </ol>
     *
     * <ul>
     *      <li>a = 2, b = 3, returns 2</li>
     *      <li>a = -2, b = 3, returns 1</li>
     *      <li>a = 2, b = -3, returns 2</li>
     *      <li>a = -2, b = -3, returns 1</li>
     * </ul>
     * @param a dividend
     * @param b divisor
     * @return a % b (always positive)
     */
    public static int positiveModulo(int a, int b){
        int result = a % b;
        if (result < 0) {
            if (b < 0) {
                result -= b;
            } else {
                result += b;
            }
        }
        return result;
    }

    /**
     * This is implementation of Math.ceil()
     * <ul>
     *     <li>round to the nearest integer -> Math.round()</li>
     *     <li>always round up -> Math.ceil()</li>
     * </ul>
     * @param num number to be divided
     * @param divisor divisor
     * @return result always rounded up
     */
    public static int roundUp(int num, int divisor) {
        if (num % divisor == 0) {
            return num / divisor;
        } else {
            return num / divisor + 1;
        }
    }

    /**
     * This is implementation of Math.round()
     * @param num number to be rounded
     * @return rounded number
     */
    public static long round(double num) {
        if (num >= 0) {
            return (long) (num + 0.5);
        } else {
            return (long) (num - 0.5);
        }
    }

    /*
    ====================================================================================================================
                                                     OTHERS
    ====================================================================================================================
     */
    /**
     * <ul>
     *      <li>When n is a power of two, it has only one bit set to 1 in its binary representation.
     *      For example, the binary representation of 8 (2^3) is 1000, and the binary representation of 16 (2^4) is 10000. </li>
     *
     *      <li>When we subtract 1 from n, all the bits to the right of the rightmost 1-bit are flipped to 1.
     *      For example, if n is 8, then n - 1 is 7, whose binary representation is 0111. Similarly, if n is 16, then n - 1 is 15, whose binary representation is 1111. </li>
     *
     *      <li>When we perform a bitwise AND operation between n and n - 1, the rightmost 1-bit in n is set to 0.
     *      For example, if n is 8, then n & (n - 1) is 0, and if n is 16, then n & (n - 1) is also 0. </li>
     *
     *      <li>If n is not a power of two, then it has more than one bit set to 1 in its binary representation.
     *      In this case, the bitwise AND operation between n and n - 1 will result in a number that is not equal to 0. </li>
     * </ul>
     *
     * @param n value to check
     * @return boolean
     */
    public static boolean isPowerOfTwo(int n) {
        if (n <= 0) {
            return false;
        }
        return (n & (n - 1)) == 0;
    }

    public static int calculateNumberOfDigits(int n) {
        // when n = 0, it is counted as length = 1
        return Integer.toString(n).length();
    }

    public static int reverseNumber(int n) {
        // reverse a number
        if(calculateNumberOfDigits(n) == 1){
            return n;
        }
        else {
            char[] inputFormatted = Integer.toString(n).toCharArray();
            StringBuilder output = new StringBuilder();
            int maxIndex = inputFormatted.length - 1;

            for(int i = 0; i <= maxIndex; i++){
                output.append(inputFormatted[maxIndex - i]);
            }

            return Integer.parseInt(output.toString());
        }
    }

    public static boolean isPalindrome(int n) {
        // is an integer a palindrome?
        return calculateNumberOfDigits(n) == 1 || reverseNumber(n) == n;
    }

    public static boolean isItPrime(int input){
        // negatives, 0, 1 is not prime
        if (input <= 1) {
            return false;
        }
        // 2 is prime
        if (input == 2) {
            return true;
        }
        // if able to be divided by 2, is not prime
        if (input % 2 == 0) {
            return false;
        }
        // last: check divisibility by odd numbers
        for (int divider = 3; divider * divider <= input; divider += 2){
            if (input % divider == 0) {
                return false;
            }
        }
        // if no problem, return true
        return true;
    }

    public static String hexRepresentation(int input){
        // 10 + 0x111 (Hexadecimal will be auto converted), result will be in decimal
        return Integer.toHexString(input);
    }

    /**
     * Splitting integer input to String Array
     * @param num integer input
     * @return String Array
     */
    public static String[] splitDigits(int num) {
        String numStr = Integer.toString(num);
        return numStr.split("");
    }

    /*
   ====================================================================================================================
                                                   SIMPLE NOTES
   ====================================================================================================================
    */
    public static void enhancedSwitchFunction(int input){
        // enhanced switch example
        switch (input){
            case 1, 2, 3 -> {
                input *= input;
                System.out.println(input);
            }
            case 4 -> {
                input++;
                System.out.println(input);
            }
            case 5 -> {
                input--;
                System.out.println(input);
            }
            default -> {
                input >>= 3;
                System.out.println(input);
            }
        }
    }

    public static void shortHandIfElse(int input){
        /*
        variable = (condition) ? expressionTrue :  expressionFalse;

        if (input < 18) {
            System.out.println("Good day.");
        } else {
            System.out.println("Good evening.");
        }
         */
        String result = (input < 18) ? "Good day." : "Good evening.";
        System.out.println(result);
    }

    /**
     * <ul>
     * <li>The scanner.nextLine() method reads the input until the end of the line.
     * It consumes the newline character that follows the line of input.
     * This means that if you call scanner.nextLine(), it will read all input until it encounters a newline character,
     * which signifies the end of a line of input.
     * </li>
     *
     * <li>On the other hand, the scanner.next() method reads the input until it encounters a space.
     * It does not consume the newline character.
     * This means that if you call scanner.next(), it will read all input until it encounters a space, and then stop.
     * </li>
     * </ul>
     * @param scanner placeHolder
     */
    public static void javaScanners(Scanner scanner){
        // import java.util.Scanner;
        int integerInput = scanner.nextInt();

        // .nextLine();
        System.out.println("Enter a line of text:");
        String line = scanner.nextLine();
        System.out.println("You entered: " + line);

        // .next();
        System.out.println("Enter two words:");
        String word1 = scanner.next();
        String word2 = scanner.next();
        System.out.println("You entered: " + word1 + " and " + word2);
    }

    // lazy people uses printArray() - Leon
    public static void printArray(int[] array) {
        for (int element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }
    public static void printArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print("[");
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j]);
                if (j < array[i].length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.print("]");
            if (i < array.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }
}
