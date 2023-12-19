package Klausur_1;

import java.util.Arrays;

public class ArrayTools {
    /*
    ====================================================================================================================
                                                ARRAYS 1D-Input
    ====================================================================================================================
     */

    /**
     * remove target element based on value, with option: first/ all occurrences
     * <br>
     * returns <code>null</code> if remove is unsuccessful
     * @param arr array to be removed from
     * @param num target element value
     * @param occurrences true = all / false = first
     * @return new array
     */
    public static int[] remove(int[] arr, int num, boolean occurrences) {
        // edge cases
        if (arr == null) {
            return null;
        }

        int count = 0;
        for (int i : arr) {
            if (i == num) {
                count++;
            }
        }

        // no element to be removed
        if (count == 0) {
            return null;
        }

        int[] newArr;
        if (occurrences) {
            newArr = new int[arr.length - count];
            int j = 0;
            for (int i : arr) {
                if (i != num) {
                    newArr[j++] = i;
                }
            }
        } else {
            newArr = new int[arr.length - 1];
            int j = 0;
            boolean removed = false;
            for (int i : arr) {
                if (i != num || removed) {
                    newArr[j++] = i;
                } else {
                    removed = true;
                }
            }
        }
        return newArr;
    }

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
     * Add an element to the array iff the array doesn't have said element
     * <br>
     * return original array if element is already inside the array
     * @param arr the array
     * @param num the element
     * @return new array
     */
    public static int[] addDistinctElement(int[] arr, int num) {
        if (arr == null) {
            return new int[]{num};
        }

        for (int i : arr) {
            if (i == num) {
                return arr;
            }
        }

        int[] newArr = new int[arr.length + 1];
        System.arraycopy(arr, 0, newArr, 0, arr.length);
        newArr[arr.length] = num;
        return newArr;
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
     * returns new array, literally only the copy of the input array
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
        }

        // copy result to array input
        System.arraycopy(result, 0, array, 0, result.length);
    }

    /**
     * Search for the first occurrence of a value inside an array, return the index or -1 (if none)
     * @param array array input
     * @param value value to be searched for
     * @return index of the value (first occurrence) or <code>-1</code> (if none)
     */
    public static int searchValue(Integer[] array, Integer value) {
        for (int i = 0; i < array.length; i++) {
            if ((array[i] == null && value == null) || (array[i] != null && array[i].equals(value))) {
                return i;
            }
        }
        return -1;
    }

    public static int searchValueRecursive(Integer[] array, Integer value, int index) {
        if (index >= array.length) {
            return -1;
        }
        if ((array[index] == null && value == null) || (array[index] != null && array[index].equals(value))) {
            return index;
        }
        return searchValueRecursive(array, value, index + 1);
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
                for (int i : array) {
                    result[filledIndex] = i;
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

    /**
     * Swaps two Position inside a 2D Array (x1, y1) <-> (x2, y2), the Array will be upsized if needed
     * @param array 2D Array
     * @param oldX current coordinate X
     * @param oldY current coordinate Y
     * @param newX target Coordinate X
     * @param newY target Coordinate Y
     * @return new Array (Object Type) if upsized
     * @param <T> inputArray dataType (any)
     */
    @SuppressWarnings("unchecked")
    public static <T> T[][] superSwap(T[][] array, int oldX, int oldY, int newX, int newY) {
        // Ensure the outer array is large enough
        int size = Math.max(Math.max(oldX, oldY), Math.max(newX, newY)) + 1;
        T[][] newArray;
        if (size > array.length) {
            newArray = (T[][]) new Object[size][size];
            for (int i = 0; i < array.length; i++) {
                newArray[i] = Arrays.copyOf(array[i], size);
            }
        } else {
            newArray = array;
        }

        // Ensure the inner arrays are large enough
        if (newArray[oldY].length <= oldX) {
            newArray[oldY] = Arrays.copyOf(newArray[oldY], oldX + 1);
        }
        if (newArray[newY].length <= newX) {
            newArray[newY] = Arrays.copyOf(newArray[newY], newX + 1);
        }

        // Swap the elements
        T temp = newArray[oldY][oldX];
        newArray[oldY][oldX] = newArray[newY][newX];
        newArray[newY][newX] = temp;

        return newArray;
    }

    /*
    ====================================================================================================================
                                                   Others
    ====================================================================================================================
     */

    /**
     * {1, 2, 3} -> [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 2, 1], [3, 1, 2]]
     * @param array array to be permuted
     * @return 2D Array permutation of the input array
     */
    public static int[][] permutations(int[] array) {
        int n = array.length;
        int fact = 1;
        for (int i = 1; i <= n; i++) {
            fact *= i;
        }
        int[][] result = new int[fact][n];
        permutations(array, 0, result, 0);
        return result;
    }

    private static int permutations(int[] array, int index, int[][] result, int count) {
        if (index == array.length - 1) {
            result[count] = array.clone();
            return count + 1;
        }

        for (int i = index; i < array.length; i++) {
            swap(array, i, index);
            count = permutations(array, index + 1, result, count);
            swap(array, i, index);
        }
        return count;
    }

    /**
     * Normal array swap Function
     * @param a array
     * @param firstPos position 1
     * @param secondPos position 2
     */
    public static void swap(int[] a, int firstPos, int secondPos) {
        // normal swap function
        int temp = a[firstPos];
        a[firstPos] = a[secondPos];
        a[secondPos] = temp;
    }
}
