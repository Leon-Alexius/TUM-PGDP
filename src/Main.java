import Klausur_2_Part2.MemoryRing;
import Klausur_2_Part2.MultiStack.MultiStack;

/**
 * For testing purposes only/**
 * @author Drago - Lie Leon Alexius (DarkRosaleen)
 */
public class Main {
    public static void main(String[] args) {
        MultiStack multiStack = new MultiStack();
        multiStack.push(100);

        multiStack.push(-1);
        multiStack.push(33);

        multiStack.push(999);
        multiStack.push(1234);
        multiStack.push(323);
        multiStack.push(111);

        multiStack.push(-9999999);

        System.out.println(multiStack);

        multiStack.pop();
        System.out.println(multiStack.getTopStackValue());
    }
}
