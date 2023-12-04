package Klausur_2;

import java.util.function.BiFunction;

public class Recursion {
    /*
    ====================================================================================================================
                                   Fibonacci Recursion - Memoization vs Iterative
    ====================================================================================================================
     */

    /**
     * Adjust size array as needed (ex: Integer.Max_Value)
     * @param n position
     * @return fibonacci number at position n
     */
    public static long fibonacci(int n) {
        long[] memo = new long[n + 1];
        return fibonacci(n, memo);
    }

    /**
     * if result is in memo, return the corresponding memo, if not, calculate
     * @param n position
     * @param memo memoization using array
     * @return fibonacci number at position n
     */
    public static long fibonacci(int n, long[] memo){
        if (n <= 2) {
            return 1;
        } else if (memo[n] != 0) {
            return memo[n];
        } else {
            long result = fibonacci(n - 1, memo) + fibonacci(n - 2, memo);
            memo[n] = result;
            return result;
        }
    }

    /**
     * fibonacci max optimization - using iterative
     * @param n position
     * @return fibonacci number at position n
     */
    public static long fibonacciIterativeOptimized(int n) {
        if (n <= 1) {
            return n;
        }
        long a = 0;
        long b = 1;
        for (int i = 2; i <= n; i++) {
            long c = a + b;
            a = b;
            b = c;
        }
        return b;
    }

    /*
    ====================================================================================================================
                                   Binomial coefficient - Memoization vs Iterative
                                  https://en.wikipedia.org/wiki/Binomial_coefficient
    ====================================================================================================================
     */

    /**
     * Binomial Coefficient Recursive using Memoization - no need to adjust anything
     * @param n number 1
     * @param k number 2
     * @return n! / k! (n-k)!
     */
    public static long binomialCoefficient(int n, int k){
        long[][] dp = new long[n+1][k+1];
        return binomialCoefficient(n, k, dp);
    }

    public static long binomialCoefficient(int n, int k, long[][] dp) {
        // Base Cases
        if (k == 0 || k == n)
            return 1;
        if (dp[n][k] != 0) // return stored result if available
            return dp[n][k];

        // Recur and store the result
        return dp[n][k] = binomialCoefficient(n - 1, k - 1, dp) + binomialCoefficient(n - 1, k, dp);
    }

    /**
     * Binomial Coefficient Optimized - Dynamic Programming
     * <ul>
     *     <li>time complexity of O(n*k)</li>
     *     <li>space complexity of O(k)</li>
     * </ul>
     * @param n number 1
     * @param k number 2
     * @return n! / k! (n-k)!
     */
    public static long binomialCoefficientOptimized(int n, int k) {
        long[] C = new long[k + 1];

        // Initialize the first value as 1
        C[0] = 1;

        for (int i = 1; i <= n; i++) {
            // Compute next row of pascal triangle using the previous row
            for (int j = Math.min(i, k); j > 0; j--)
                C[j] = C[j] + C[j-1];
        }
        return C[k];
    }

    /*
    ====================================================================================================================
                                                    Others
    ====================================================================================================================
     */

    /**
     * uses recursion to iterate over an int array and return the sum of all elements
     * @param arr array input
     * @return the sum of all elements
     */
    public static int arraySum(int[] arr) {
        return arraySum(arr, arr.length);
    }

    public static int arraySum(int[] arr, int n) {
        if (n <= 0)
            return 0;
        else
            return (arraySum(arr, n - 1) + arr[n - 1]);
    }

    /**
     * Find index of largest element in the array recursively from index 0 to index limit (inclusive)
     * <ul>
     *     <li>If there are multiple occurrences of the largest value, it will return the first occurrence.</li>
     *     <li>If the array is empty or the limit is less than 0, the behavior is undefined</li>
     * </ul>
     * @param array array to be searched
     * @param limit max index (inclusive)
     * @return largest element in the array
     */
    public static int findIndexOfLargest(int[] array, int limit) {
        return findIndexOfLargest(array, 0, 0, array[0], limit);
    }

    public static int findIndexOfLargest(int[] array, int index, int maxIndex, int maxValue, int limit) {
        if (index > limit) {
            // end of recursion (last element checked)
            return maxIndex;
        }
        else if (array[index] > maxValue) {
            // if current element > max value, then update current max value to "array[index]"
            return findIndexOfLargest(array, index + 1, index, array[index], limit);
        }
        else {
            // if current element <= max value, then just continue
            return findIndexOfLargest(array, index + 1, maxIndex, maxValue, limit);
        }
    }


}
