import Klausur_2_Part2.Tree.Order;
import Klausur_2_Part2.Tree.Tree;

import java.util.Random;

/**
 * For testing purposes only/**
 * @author Drago - Lie Leon Alexius (DarkRosaleen)
 */
public class Main {
    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>(Order.IN);
        tree.insert(15);

        tree.insert(10);
        tree.insert(20);
        tree.insert(5);
        tree.insert(3);
        tree.insert(21);
        tree.insert(17);

        for(Integer e : tree){
            System.out.println(e);
        }
    }
}
