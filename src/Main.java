import java.util.Arrays;

import static Klausur_2.Recursion.*;
import static Klausur_1.SortTools.*;

/**
 * For testing purposes only/**
 * @author Drago - Lie Leon Alexius (DarkRosaleen)
 */
public class Main {
    public static void main(String[] args) {
        int[] a = {3, 4, 9, 2, 5, 0, 2, 1, 6, 4, -3};
        System.out.println(Arrays.toString(mergeSort_Recursive(a)));

        mergeSort(a);
        System.out.println(Arrays.toString(a));
    }
}
