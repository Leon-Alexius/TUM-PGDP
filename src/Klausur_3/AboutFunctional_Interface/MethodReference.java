package Klausur_3.AboutFunctional_Interface;

import Klausur_3.AboutFunctional_Interface.LambdaBasics.Calculator;

/**
 * Method references are a shorthand notation of a lambda expression that executes just ONE method
 */
public class MethodReference {
    // First, let’s define a static method multiply in a class MathUtil
    private static class MathUtil {
        public static int multiply(int x, int y) {
            return x * y;
        }
    }

    // Now, let’s implement the Calculator interface using a lambda expression:
    private static void usingLambda(){
        Calculator multiplicationLambda = (x, y) -> MathUtil.multiply(x, y);
        System.out.println("Lambda multiplication: " + multiplicationLambda.calculate(2, 3)); // Outputs 6
    }

    // And finally, let’s implement the same interface using a method reference:
    private static void usingMethodReference(){
        Calculator multiplicationMethodRef = MathUtil::multiply;
        System.out.println("Method reference multiplication: " + multiplicationMethodRef.calculate(2, 3)); // Outputs 6
    }

    /*
    Both the lambda expression and the method-reference achieve the same result.
    However, the method reference is even more concise than the lambda expression.
    So, when your lambda expression is doing nothing but calling an existing method, it’s better to use method references.
     */
}
