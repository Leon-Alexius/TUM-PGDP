package Klausur_1;

import static Klausur_2_Part2.Recursion.findIndexOfLargest;

public class SortTools {
    /*
    ====================================================================================================================
                                               SORTING 1-D ARRAYS
    ====================================================================================================================
     */

    /**
     * Array Sort Algorithm using Bubble Sort
     * @param arr Array to be sorted
     */
    public static void bubbleSort_Recursive(int[] arr) {
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
     * Array Sort Algorithm using Quick Sort
     * <br>
     * Usage:
     * <code>
     *     quickSort(ArrayToBeSorted, 0, ArrayToBeSorted.length - 1);
     * </code>
     * @param arr Array to be sorted
     * @param low start index
     * @param high max index
     */
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    /**
     * Array Sort Algorithm using Gnome Sort
     * @param arr Array to be sorted
     */
    public static void gnomeSort(int[] arr) {
        int index = 0;
        while (index < arr.length) {
            if (index == 0)
                index++;
            if (arr[index] >= arr[index - 1])
                index++;
            else {
                int temp = 0;
                temp = arr[index];
                arr[index] = arr[index - 1];
                arr[index - 1] = temp;
                index--;
            }
        }
    }

    /**
     * Array Sort Algorithm using stoogeSort
     * <br>
     * This code sorts an array by recursively dividing it into thirds:
     * <ol>
     *     <li>sorting the first two-thirds,</li>
     *     <li>sorting the last two-thirds,</li>
     *     <li>and then sorting the first two-thirds again</li>
     * </ol>
     * @param arr array to be sorted
     */
    public static void stoogeSort(int[] arr){
        stoogeSort(arr, 0, arr.length - 1);
    }

    public static void stoogeSort(int[] arr, int l, int h) {
        if (l >= h)
            return;

        if (arr[l] > arr[h]) {
            int t = arr[l];
            arr[l] = arr[h];
            arr[h] = t;
        }

        if (h - l + 1 > 2) {
            int t = (h - l + 1) / 3;
            stoogeSort(arr, l, h - t);
            stoogeSort(arr, l + t, h);
            stoogeSort(arr, l, h - t);
        }
    }

    /**
     * Array Sort Algorithm using Merge Sort
     * <br>
     * Usage for mergeSort(int[] arr, int l, int r)
     * <code>
     *     mergeSort(ArrayToBeSorted, 0, ArrayToBeSorted.length - 1);
     * </code>
     * @param arr Array to be sorted
     */
    public static void mergeSort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    public static void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;

            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);

            merge(arr, l, m, r);
        }
    }

    public static void merge(int[] arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        int[] L = new int[n1];
        int[] R = new int[n2];

        System.arraycopy(arr, l, L, 0, n1);
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        int i = 0, j = 0;

        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    /**
     * Array Sort Algorithm using Selection Sort
     * <ol>
     *     <li>Array index 0 ~ 5, search max value, put at the end, continue</li>
     *     <li>Array index 0 ~ 4, search max value, put at the end, continue</li>
     *     <li>...</li>
     *     <li>Array index 0 ~ 1, search max value, put at the end, done</li>
     * </ol>
     * @param array array to be sorted
     */
    public static void selectionSortRec(int[] array) {
        selectionSortRec(array, array.length - 1);
    }

    public static void selectionSortRec(int[] array, int toIncl) {
        if (toIncl > 0) {
            // 1. get index of the biggest element
            int maxIndex = findIndexOfLargest(array, toIncl);

            // 2. move the biggest element to the end of the array
            swap(array, maxIndex, toIncl);

            // 3. we ignore the last index since it has been sorted
            // 4. do step 1 to 3 again and again until all has been sorted
            selectionSortRec(array, toIncl - 1);
        }
    }

    /*
    ====================================================================================================================
                                               SORTING 2-D ARRAYS
    ====================================================================================================================
     */

    /**
     * Sorting a 2D-Array while maintaining its structure with movable element
     * int[][] arr = { {5, -4, 2}, {1}, {13, 9} };
     * <br>
     * sort2DArray(arr); -> [[-4, 1, 2], [5], [9, 13]]
     * @param inputArray input Array
     */
    public static void sort2DArray_Type1(int[][] inputArray) {
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

    /**
     * Sorting a 2D-Array while maintaining its structure with fixed element
     * int[][] arr = { {5, -4, 2}, {1}, {13, 9} };
     * <br>
     * sort2DArray(arr); -> [[-4, 2, 5], [1], [9, 13]]
     * @param inputArray input Array
     */
    public static void sort2DArray_Type2(int[][] inputArray) {
        for (int[] array : inputArray) {
            bubbleSort_Recursive(array);
        }
    }


    /*
    ====================================================================================================================
                                               Recursive Versions
    ====================================================================================================================
     */

    /**
     * The for loop is still present because it’s necessary to compare and swap adjacent elements.
     * @param arr Array to be sorted
     * @param n arr.length
     */
    public static void bubbleSort_Recursive(int[] arr, int n) {
        if (n == 1)
            return;
        for (int i = 0; i < n - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                int temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }
        }
        bubbleSort_Recursive(arr, n - 1);
    }

    /**
     * MergeSort using recursion
     * @param arr Array To Be Sorted
     * @return new sorted Array
     */
    public static int[] mergeSort_Recursive(int[] arr) {
        if (arr.length <= 1) {
            return arr;
        }

        int mid = arr.length / 2;

        int[] left = new int[mid];
        int[] right = new int[arr.length - mid];

        System.arraycopy(arr, 0, left, 0, mid);
        System.arraycopy(arr, mid, right, 0, arr.length - mid);

        left = mergeSort_Recursive(left);
        right = mergeSort_Recursive(right);

        return mergeRecursive(left, right);
    }

    public static int[] mergeRecursive(int[] left, int[] right) {
        int[] merged = new int[left.length + right.length];
        int nextPositionFirst = 0;
        int nextPositionSecond = 0;
        int nextPositionMerged = 0;

        while(!(nextPositionFirst >= left.length) && !(nextPositionSecond >= right.length)) {
            if(left[nextPositionFirst] < right[nextPositionSecond]) {
                merged[nextPositionMerged++] = left[nextPositionFirst++];
            } else {
                merged[nextPositionMerged++] = right[nextPositionSecond++];
            }
        }

        while(nextPositionFirst < left.length) {
            merged[nextPositionMerged++] = left[nextPositionFirst++];
        }
        while(nextPositionSecond < right.length) {
            merged[nextPositionMerged++] = right[nextPositionSecond++];
        }

        return merged;
    }

    /*
    ====================================================================================================================
                                               MERGE SORT CUSTOM
    ====================================================================================================================
     */

    /**
     * Using Merge Sort algorithm, but you can set the division of the elements
     * @param array array to be sorted
     * @param div element divided by div-parts
     * @return sorted Array
     */
    public int[] customMergeSort(int[] array, int div) {
        return customMergeSort(array, div, 0, array.length);
    }

    public int[] customMergeSort(int[] array, int div, int from, int to) {
        if (to - from < 1) {
            return new int[0];
        }
        if (to - from == 1) {
            return new int[] { array[from] };
        }
        int chunkSize = (to - from) / div;
        int nrOversizedChunks = (to - from) % div;
        int nrOfChunks = chunkSize == 0 ? nrOversizedChunks: div;
        int[][] outs = new int[nrOfChunks][];
        int offsetFrom = from;
        for (int chunkIndex=0; chunkIndex<nrOfChunks; chunkIndex++) {
            int offsetTo = offsetFrom + chunkSize;
            if (chunkIndex < nrOversizedChunks) {
                offsetTo++;
            }
            outs[chunkIndex] = customMergeSort(array, div, offsetFrom, offsetTo);
            offsetFrom = offsetTo;
        }
        return customMergeSort_Merge(outs, 0, outs.length);
    }

    public int[] customMergeSort_Merge(int[][] arrays, int from, int to) {
        if (to - from < 1) {
            return new int[0];
        }
        if (to - from == 1) {
            return arrays[from];
        }
        return customMergeSort_Merge(arrays[from], customMergeSort_Merge(arrays, from + 1, to));
    }

    public int[] customMergeSort_Merge(int[] array1, int[] array2) {
        int array1NextIndex = 0;
        int array2NextIndex = 0;
        int[] result = new int[array1.length + array2.length];
        for (int i = 0; i < result.length; i++) {
            if (array1NextIndex == array1.length) {
                result[i] = array2[array2NextIndex++];
            } else if (array2NextIndex == array2.length) {
                result[i] = array1[array1NextIndex++];
            } else if (array1[array1NextIndex] < array2[array2NextIndex]) {
                result[i] = array1[array1NextIndex++];
            } else {
                result[i] = array2[array2NextIndex++];
            }
        }
        return result;
    }
}
