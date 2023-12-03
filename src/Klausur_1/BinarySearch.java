package Klausur_1;

public class BinarySearch {
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
}
