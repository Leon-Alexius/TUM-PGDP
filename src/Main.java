import Klausur_2.List;
import Klausur_2.ListElement;

import java.util.Arrays;

/**
 * For testing purposes only/**
 * @author Drago - Lie Leon Alexius (DarkRosaleen)
 */
public class Main {
    public static void main(String[] args) {
        List list = new List(); // [0, 1, 2, 3, 4, 5]
        for(int i = 0; i < 6; i++) {
            list.add(i);
        }

        List list1 = new List(); // [10, 11, 12, 13, 14]
        for(int i = 10; i < 15; i++){
            list1.add(i);
        }

        List list2 = new List(); // [-77, -100]
        list2.add(-77);
        list2.add(-100);

        List ex = List.zipMultipleLists(list, list1, list2);
        System.out.println(ex); // [0, 10, -77, 1, 11, -100, 2, 12, 3, 13, 4, 14, 5]
    }
}
