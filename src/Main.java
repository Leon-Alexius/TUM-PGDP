import Klausur_2.DoubleChainedList;

/**
 * For testing purposes only/**
 * @author Drago - Lie Leon Alexius (DarkRosaleen)
 */
public class Main {
    public static void main(String[] args) {
        DoubleChainedList doubleChainedList = new DoubleChainedList(); // [0, 1, 2, 3, 4, 5]
        for(int i = 0; i < 6; i++) {
            doubleChainedList.add_Recursive(i);
        }

        System.out.println(doubleChainedList.getElementByIndex_Recursive(6).getValue());
    }
}
