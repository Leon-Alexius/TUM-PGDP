import Klausur_2_Part2.ListEx.List;
import Klausur_2_Part2.ListEx.List2D;
import Klausur_2_Part2.ListEx.List2DIterator;

import java.util.Iterator;

/**
 * For testing purposes only/**
 * @author Drago - Lie Leon Alexius (DarkRosaleen)
 */
public class Main {
    public static void main(String[] args) {
        List<String> list = new List<>();
        list.add("A");
        list.add("B");

        List<String> list1 = new List<>();
        list.add("C");
        list.add("D");

        List2D<String> list2D = new List2D<>();
        list2D.add(new List<>());
        list2D.add(list);
        list2D.add(list1);


        for(String e : list2D){
            System.out.println(e);
        }
    }
}
