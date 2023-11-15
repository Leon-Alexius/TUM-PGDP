import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Drago - Lie Leon Alexius (DarkRosaleen)
 */
public class ToolOne {

    public static void main(String[] args) {
        int[] anArray = {0, -3, 2, 7, -7, 9, -10, 11};
        System.out.println(Arrays.toString(injectArray(anArray, 99, 2)));

        int[] array0 = {0, 0, 1, 1, 1, 2, 2, 3, 4, 5, 6, 7};
        System.out.println(Arrays.toString(binarySearch(array0, 0)));

        int[] array1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(Arrays.toString(filterNumbers(array1, true)));  // prints [2, 4, 6, 8, 10]
        System.out.println(Arrays.toString(filterNumbers(array1, false))); // prints [1, 3, 5, 7, 9]

        int[] array2 = {0, 1, 2, 2, 1, 3, 2, 4, 5, 1, 0, 0, 1};
        System.out.println(Arrays.deepToString(getDistinctElements2(array2)));

        int[][] test = {{1, 2, 3, 4}, {5, 4, 3, 2}, {1, -2, 4, 3, 3}};
        System.out.println(Arrays.deepToString(findMinMax(test)));

        int[][] input = {{1, 2, 3, 4}, {5, 6, 7}};
        int[][] output = transpose(input);
        System.out.println(Arrays.deepToString(output));

        int[][] test3 = {{1, 3}, {25}, {7, 4, 6, 9}};
        System.out.println(Arrays.toString(zipManySorted(test3)));
    }

    /*
    ====================================================================================================================
                                                ARRAYS 1D-Input
    ====================================================================================================================
     */

    /**
     * Remove elements of certain indices <br>
     * For elements of certain range, see the other removeElement()
     *
     * @param array array from the element which to be removed
     * @param indices indices of to be removed elements
     * @return a new shorter array
     */
    public static int[] removeArrayElement_Indices(int[] array, int... indices) {
        // assume indices is random, not sorted
        Arrays.sort(indices);
        int[] newArray = new int[array.length - indices.length];
        for (int i = 0, j = 0, k = 0; i < array.length; i++) {
            if (k < indices.length && i == indices[k]) {
                k++;
                continue;
            }
            newArray[j++] = array[i];
        }
        return newArray;
    }

    /**
     * Remove elements inside a certain range (inclusive start && end)
     *
     * @param array array from the element which to be removed
     * @param start start index of to be removed elements
     * @param end end index of to be removed elements
     * @return a new shorter array
     */
    public static int[] removeArrayElement_InRange(int[] array, int start, int end) {
        end += 1; // inclusive
        if (start < 0 || end > array.length || start > end) {
            return array;
        }
        int[] newArray = new int[array.length - (end - start)];
        for (int i = 0, j = 0; i < array.length; i++) {
            if (i >= start && i < end) {
                continue;
            }
            newArray[j++] = array[i];
        }
        return newArray;
    }

    /**
     * Injects an element to the current array at given index <br>
     * int[] anArray = {0, -3, 2, 7, -7, 9, -10};
     * <ol>
     *     <li>
     *         injectArray(anArray, 99, 7) -> [0, -3, 2, 7, -7, 9, -10, 99]
     *     </li>
     *     <li>
     *         injectArray(anArray, 99, 0) -> [99, 0, -3, 2, 7, -7, 9, -10]
     *     </li>
     *     <li>
     *         injectArray(anArray, 99, 5) -> [0, -3, 2, 7, -7, 99, 9, -10]
     *     </li>
     * </ol>
     *
     * @param array array to be injected to
     * @param element new element
     * @param index target index
     * @return new bigger array
     */
    public static int[] injectArray(int[] array, int element, int index) {
        if (index < 0 || index > array.length) {
            return array;
        }
        int[] newArray = new int[array.length + 1];
        for (int i = 0, j = 0; i < array.length + 1; i++, j++) {
            if (i == index) {
                newArray[j] = element;
                j++;
            }
            if (i < array.length) {
                newArray[j] = array[i];
            }
        }
        return newArray;
    }

    /**
     * int[] anArray = {0, -3, 2, 7, -7, 9, -10};
     * <ul>
     *     <li>
     *         moveArray(anArray, 3, 2, false) -> [0, -3, 2, 9, -10]
     *     </li>
     *     <li>
     *         moveArray(anArray, 3, 2, true) -> [0, -3, 2, 0, 0, 7, -7, 9, -10]
     *     </li>
     * </ul>
     *
     * @param array array input
     * @param index starting index to be moved (inclusive)
     * @param n how far to be moved
     * @param condition true (right), false (left)
     * @return a new array
     */
    public static int[] moveArrayElements(int[] array, int index, int n, boolean condition) {
        if (index < 0 || index >= array.length || n < 0) {
            return array;
        }
        int[] newArray;
        if (condition) {
            newArray = new int[array.length + n];
            System.arraycopy(array, 0, newArray, 0, index);
            System.arraycopy(array, index, newArray, index + n, array.length - index);
        } else {
            newArray = new int[array.length - n];
            System.arraycopy(array, 0, newArray, 0, index);
            System.arraycopy(array, index + n, newArray, index, array.length - index - n);
        }
        return newArray;
    }

    /**
     * if length <= 0, returns {}
     * <ul>
     *     <li> resize(new int[] {1, 2, 3}, 2) -> {1, 2} </li>
     *     <li> resize(new int[] {1, 2, 3}, 5) -> {1, 2, 3, 0, 0} </li>
     * </ul>
     * @param array original array
     * @param length new length
     * @return new array
     */
    public static int[] resize(int[] array, int length) {
        if(length <= 0){
            return new int[]{};
        }
        int[] result = new int[length];
        for(int i = 0; i < array.length; i++){
            if(i < length){
                result[i] = array[i];
            }
            else {
                break;
            }
        }
        return result;
    }

    /**
     *
     * @param array array to be copied
     * @return a copy of the array (new array)
     */
    public static int[] copyArray(int[] array){
        /*
        array:        The source array that you want to copy.
        srcPos:       The starting index of the source array that you want to copy from.
        output:       The destination array that you want to copy the source array to.
        destPos:      The starting index of the destination array that you want to copy to.
        array.length: The number of elements that you want to copy from the source array to the destination array.
         */
        int[] output = new int[array.length];
        System.arraycopy(array, 0, output, 0, array.length);

        return output;
    }

    /**
     * @param array array to be reversed
     * @return new reversed array
     */
    public static int[] reverseArray_returnNew(int[] array) {
        int[] reversedArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            reversedArray[i] = array[array.length - i - 1];
        }
        return reversedArray;
    }

    // same as reverseArray but original array is not preserved
    public static void reverseArray_editOriginal(int[] array) {
        // Edit Array input
        int frontElement;

        // Ignore element in middle if it is odd
        for (int i = 0; i < array.length / 2; i++) {
            frontElement = array[i];
            array[i] = array[array.length - 1 - i]; // front element = back element
            array[array.length - 1 - i] = frontElement; // back element = front element
        }
    }

    /**
     * condition:
     * <ul>
     *     <li>false = extra element added consecutively</li>
     *     <li>true = extra element added only to specified block</li>
     * </ul>
     *
     * @param input array to be split
     * @param blocks how many blocks (inner Array)
     * @param condition false or true
     * @param target specify block, only useful for true condition
     * @return new 2D Array
     */
    public static int[][] splitArrays(int[] input, int blocks, boolean condition, int target) {
        if (blocks == 0) {
            int[][] result = new int[1][];
            result[0] = input;
            return result;
        }

        int n = input.length;
        int size = n / blocks;
        int remainder = n % blocks;

        int[][] result = new int[blocks][];

        int start = 0;
        for (int i = 0; i < blocks; i++) {
            int end = start + size;
            if (remainder > 0 && (!condition || i == target - 1)) {
                end++;
                remainder--;
            }
            if (condition && i == target - 1) {
                end += remainder;
                remainder = 0;
            }
            result[i] = new int[end - start];
            for (int j = start; j < end; j++) {
                result[i][j - start] = input[j];
            }
            start = end;
        }

        return result;
    }

    /**
     * input = 12345, output = {1, 2, 3, 4, 5}
     *
     * @param input single int input
     * @return array of the input's components
     */
    public static int[] intToIntegerArray(int input){
        String formattedInput = Integer.toString(input);

        int[] result = new int[formattedInput.length()];
        for (int i = 0; i < formattedInput.length(); i++) {
            result[i] = Integer.parseInt(String.valueOf(formattedInput.charAt(i)));
        }

        return result;
    }

    /**
     * int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
     * <ul>
     *     <li>
     *         filterNumbers(array, true) -> [2, 4, 6, 8, 10]
     *     </li>
     *     <li>
     *         filterNumbers(array, false) -> [1, 3, 5, 7, 9]
     *     </li>
     * </ul>
     * @param array input array
     * @param keepEven true (buang ganjil), false (buang genap)
     * @return new array
     */
    public static int[] filterNumbers(int[] array, boolean keepEven) {
        int count = 0;
        for (int num : array) {
            if ((keepEven && num % 2 == 0) || (!keepEven && num % 2 != 0)) {
                count++;
            }
        }

        int[] result = new int[count];
        int index = 0;
        for (int num : array) {
            if ((keepEven && num % 2 == 0) || (!keepEven && num % 2 != 0)) {
                result[index++] = num;
            }
        }

        return result;
    }

    /**
     * int[] array2 = {0, 1, 2, 2, 1, 3, 2, 4, 5, 1, 0, 0, 1};
     * <ul>
     *     <li> getDistinctElements(array2) -> [0, 1, 2, 3, 4, 5] </li>
     * </ul>
     * @param array input array
     * @return new array, same order, distinct elements
     */
    public static int[] getDistinctElements(int[] array) {
        int[] temp = new int[array.length];
        int count = 0;

        for (int i = 0; i < array.length; i++) {
            boolean isDistinct = true;
            for (int j = 0; j < i; j++) {
                if (array[i] == array[j]) {
                    isDistinct = false;
                    break;
                }
            }
            if (isDistinct) {
                temp[count++] = array[i];
            }
        }

        int[] result = new int[count];
        for (int i = 0; i < count; i++) {
            result[i] = temp[i];
        }

        return result;
    }

    /**
     * Same as getDistinctElements, but returns a 2D-Array, distinct elements and its count
     * int[] array2 = {0, 1, 2, 2, 1, 3, 2, 4, 5, 1, 0, 0, 1};
     * <ul>
     *      <li> getDistinctElements(array2) -> [[0, 3], [1, 4], [2, 3], [3, 1], [4, 1], [5, 1]] </li>
     * </ul>
     * @param array input Array
     * @return 2D Array
     */
    public static int[][] getDistinctElements2(int[] array) {
        int[] temp = new int[array.length];
        int[] count = new int[array.length];
        int distinctCount = 0;

        for (int i = 0; i < array.length; i++) {
            boolean isDistinct = true;
            for (int j = 0; j < i; j++) {
                if (array[i] == array[j]) {
                    isDistinct = false;
                    count[j]++;
                    break;
                }
            }
            if (isDistinct) {
                temp[distinctCount] = array[i];
                count[distinctCount] = 1;
                distinctCount++;
            }
        }

        int[][] result = new int[distinctCount][2];
        for (int i = 0; i < distinctCount; i++) {
            result[i][0] = temp[i];
            result[i][1] = count[i];
        }

        return result;
    }

    /**
     * this is a weird method
     * <ul>
     *     <li> [1, 2, 3, 4, 5] rotate 2 -> [4, 5, 1, 2, 3] </li>
     *     <li> [1, 2, 3, 4, 5] rotate -1 -> [2, 3, 4, 5, 1]</li>
     * </ul>
     * @param array input 1D-Array
     * @param amount how much to "rotate"
     */
    public static void rotate(int[] array, int amount) {
        int inputArrayLength = array.length;
        if(inputArrayLength == 0){
            return;
        }
        int[] result = new int[inputArrayLength];

        // check negative values
        boolean toRight = true;
        if(amount < 0){
            toRight = false;
            amount = -(amount);
        }

        // re-format to cycle
        amount %= inputArrayLength;

        // start process
        if (toRight) {
            for(int i = 0; i < inputArrayLength; i++){
                int currentElement = array[i];
                if(i + amount >= inputArrayLength){
                    int indexPointer = i + amount - inputArrayLength;
                    result[indexPointer] = currentElement;
                }
                else {
                    result[i + amount] = currentElement;
                }
            }

            System.out.println(Arrays.toString(result));
        }
        else {
            for(int i = 0; i < inputArrayLength; i++){
                int currentElement = array[i];
                if(i - amount < 0){
                    int indexPointer = i - amount + inputArrayLength;
                    result[indexPointer] = currentElement;
                }
                else {
                    result[i - amount] = currentElement;
                }
            }

            System.out.println(Arrays.toString(result));
        }

        // copy result to array input
        System.arraycopy(result, 0, array, 0, result.length);
    }

    /*
    ====================================================================================================================
                                                ARRAYS 2D-Input
    ====================================================================================================================
     */

    /**
     * Returns 2D Array, with the inner Array contains the min, max value of the previous inner Array
     * int[][] test = {{1, 2, 3, 4}, {5, 4, 3, 2}, {1, -2, 4, 3, 3}};
     * <ul>
     *     <li> findMinMax(test) -> [[1, 4], [2, 5], [-2, 4]] </li>
     * </ul>
     * @param inputArray input Array
     * @return new 2D-Array
     */
    public static int[][] findMinMax(int[][] inputArray) {
        int[][] output = new int[inputArray.length][2];

        for (int i = 0; i < inputArray.length; i++) {
            int min = inputArray[i][0];
            int max = inputArray[i][0];

            for (int j = 1; j < inputArray[i].length; j++) {
                if (inputArray[i][j] < min) {
                    min = inputArray[i][j];
                }
                if (inputArray[i][j] > max) {
                    max = inputArray[i][j];
                }
            }

            output[i][0] = min;
            output[i][1] = max;
        }

        return output;
    }

    /**
     * Fills the transpose with 0 if original array is not "square"
     * int[][] input = {{1, 2, 3, 4}, {5, 6, 7}};
     * <ul>
     *     <li> transpose(input) -> [[1, 5], [2, 6], [3, 7], [4, 0]] </li>
     * </ul>
     * @param inputArray Array to be transposed
     * @return new 2D Transposed Array
     */
    public static int[][] transpose(int[][] inputArray) {
        // error checking
        if (inputArray == null || inputArray.length == 0 || (inputArray.length == 1 && inputArray[0].length == 0)) {
            return new int[][]{{}};
        }
        // start work
        int rows = inputArray.length;
        int cols = inputArray[0].length;
        for (int i = 1; i < rows; i++) {
            if (inputArray[i].length > cols) {
                cols = inputArray[i].length;
            }
        }
        int[][] output = new int[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                output[j][i] = i < inputArray.length && j < inputArray[i].length ? inputArray[i][j] : 0;
            }
        }
        return output;
    }

    /**
     * int[][] test3 = {{1, 3}, {25}, {7, 4, 6, 9}};
     * <br>
     * linearize(test3) -> [1, 3, 25, 7, 4, 6, 9]
     * @param a input of 2D-Array, with inner Array of random size
     * @return new 1D-Array
     */
    public static int[] linearize(int[][] a) {
        // count total element
        int totalElement = 0;
        for(int[] innerArray : a){
            for(int ignored : innerArray){
                totalElement += 1;
            }
        }

        // start & check input
        int[] output = new int[totalElement];
        if (totalElement == 0){
            return output;
        }

        // start working
        int index = 0;
        for(int[] innerArray : a){
            for(int element : innerArray){
                output[index++] = element;
            }
        }

        return output;
    }

    // The Methods zip() and zipMany() has similar concept as linearize()
    /**
     * zip(new int[]{1, 3, 5, 7, 9}, new int[]{2, 4, 6, 8}) ->[1, 2, 3, 4, 5, 6, 7, 8, 9]
     * @param a 1D Array
     * @param b 1D Array
     * @return new 1D Array, elements of a and b
     */
    public static int[] zip(int[] a, int[] b) {
        int[] result = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;

        // switch between elements from "a" and "b"
        while (i < a.length && j < b.length) {
            result[k++] = a[i++];
            result[k++] = b[j++];
        }

        // add extra elements from "a" and "b"
        while (i < a.length) {
            result[k++] = a[i++];
        }
        while (j < b.length) {
            result[k++] = b[j++];
        }

        return result;
    }

    /**
     * int[][] test = {{1, 3, 20, 30}, {2, 4, -5}, {-4}};
     * <br>
     * zipMany(test) = [1, 2, -4, 3, 4, 20, -5, 30]
     * @param arrays 2D Array
     * @return new 1D Array
     */
    public static int[] zipMany(int[][] arrays) {
        try{
            // get data
            int totalElementCount = 0;
            int maximumElementCount_onOneOuterArray = arrays[0].length;
            for(int[] outerArray : arrays){
                if(outerArray.length > maximumElementCount_onOneOuterArray){
                    maximumElementCount_onOneOuterArray = outerArray.length;
                }

                for(int ignored : outerArray){
                    totalElementCount += 1;
                }
            }

            // create result array
            int[] result = new int[totalElementCount];
            int filledIndex = 0;

            int j = 0;
            while(filledIndex < totalElementCount){
                for (int[] array : arrays) {
                    if(j > array.length - 1){
                        continue;
                    }
                    result[filledIndex] = array[j];
                    filledIndex += 1;
                }
                j += 1;
            }

            return result;
        }
        catch (Exception e){
            // if input {} or others
            return new int[]{};
        }
    }

    /**
     * Same as zipMany() but with extra bubbleSort
     * <br>
     * int[][] test3 = {{1, 3}, {25}, {7, 4, 6, 9}};
     * <br>
     * zipManySorted(test3) -> [1, 3, 4, 6, 7, 9, 25]
     * @param arrays input 2D-Array
     * @return new 1D-Array
     */
    public static int[] zipManySorted(int[][] arrays) {
        try {
            // get data
            int totalElementCount = 0;
            for (int[] outerArray : arrays) {
                totalElementCount += outerArray.length;
            }

            // create result array
            int[] result = new int[totalElementCount];
            int filledIndex = 0;

            for (int[] array : arrays) {
                for (int j = 0; j < array.length; j++) {
                    result[filledIndex] = array[j];
                    filledIndex += 1;
                }
            }

            // sort the result array using bubble sort
            for (int i = 0; i < result.length - 1; i++) {
                for (int j = 0; j < result.length - i - 1; j++) {
                    if (result[j] > result[j + 1]) {
                        // swap result[j] and result[j + 1]
                        int temp = result[j];
                        result[j] = result[j + 1];
                        result[j + 1] = temp;
                    }
                }
            }

            return result;
        } catch (Exception e) {
            // if input {} or others
            return new int[]{};
        }
    }

    /*
    ====================================================================================================================
                                                 SORTING ARRAYS
    ====================================================================================================================
     */

    /**
     * Array Sort Algorithm using Bubble Sort
     * @param arr Array to be sorted
     */
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                if (arr[j] > arr[j+1]) {
                    // using method swap()
                    swap(arr, j+1, j);
                }
            }
        }
    }

    private static void swap(int[] array, int i, int j) {
        // normal Array Element swap method
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * Sorting a 2D-Array while maintaining its structure
     * int[][] arr = { {5, -4, 2}, {1}, {13, 9} };
     * <br>
     * sort2DArray(arr); -> [[-4, 1, 2], [5], [9, 13]]
     * @param inputArray input Array
     */
    public static void sort2DArray(int[][] inputArray) {
        int n = inputArray.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < inputArray[i].length; j++) {
                for (int k = 0; k < n; k++) {
                    for (int l = 0; l < inputArray[k].length; l++) {
                        if (inputArray[i][j] < inputArray[k][l]) {
                            int temp = inputArray[i][j];
                            inputArray[i][j] = inputArray[k][l];
                            inputArray[k][l] = temp;
                        }
                    }
                }
            }
        }
    }

    /*
    ====================================================================================================================
                                                 BINARY SEARCH
    ====================================================================================================================
     */

    /**
     * Search for an element inside an array, returns the indices inside an Array <br>
     * int[] array = {0, 0, 1, 1, 1, 2, 2, 3, 4, 5, 6, 7};
     * <ol>
     *     <li>
     *         [] result = binarySearch(array, 0); -> {0, 1}
     *     </li>
     * </ol>
     *
     * @param array array to search in
     * @param target element to search for
     * @return array of the element indices
     */
    public static int[] binarySearch(int[] array, int target) {
        int first = findFirst(array, target);
        int last = findLast(array, target);

        if (first == -1 || last == -1) {
            return new int[0];  // target not found
        }

        int[] result = new int[last - first + 1];
        for (int i = 0; i < result.length; i++) {
            result[i] = first + i;
        }
        return result;
    }

    private static int findFirst(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;
        int result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (array[mid] == target) {
                result = mid;
                right = mid - 1;
            } else if (array[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }

    private static int findLast(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;
        int result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (array[mid] == target) {
                result = mid;
                left = mid + 1;
            } else if (array[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }

    /*
    ====================================================================================================================
                                                     OTHERS
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