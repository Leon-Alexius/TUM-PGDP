package Klausur_3.AboutFunctional_Interface;

/**
 * A lambda expression is essentially an anonymous or unnamed method
 */
public class LambdaBasics{
    /**
     * let’s define a functional interface and then implement it using both a traditional anonymous class and a lambda expression.
     * <br> <br>
     * First, let’s define a simple functional interface Calculator that performs an operation on two integers:
     */
    @FunctionalInterface
    public interface Calculator {
        int calculate(int x, int y);
    }

    /**
     * Now, let’s implement this interface using an anonymous class:
     */
    private static void usingAnonymousClass(){
        Calculator additionAnonymous = new Calculator() {
            @Override
            public int calculate(int x, int y) {
                return x + y;
            }
        };
        System.out.println("Anonymous class addition: " + additionAnonymous.calculate(2, 3)); // Outputs 5
    }

    /**
     * And finally, let’s implement the same interface using a lambda expression:
     */
    private static void usingLambda(){
        Calculator additionLambda = (x, y) -> x + y;
        System.out.println("Lambda addition: " + additionLambda.calculate(2, 3)); // Outputs 5
    }

    /**
     * Test
     */
    public static void main(String[] args) {
        usingAnonymousClass();
        usingLambda();
    }
}
