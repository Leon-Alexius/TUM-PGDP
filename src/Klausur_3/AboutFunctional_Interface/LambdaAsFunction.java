package Klausur_3.AboutFunctional_Interface;

import java.util.function.Function;

/**
 *  A lambda expression can be used as a function in Java. It can be assigned to a variable whose type is a functional interface
 */
public class LambdaAsFunction{
    /**
     * A way to write multiple block of Lambda
     * @param integer Integer
     */
    private static void exampleA(Integer integer){
        // This is a Function that takes an Integer as input and returns (2 * Integer)^2
        Function<Integer, Integer> TwiceSquare = (Integer x) -> {
            x = x * 2;
            return x * x;
        };

        // Shorthand version:
        Function<Integer, Integer> twiceSquare = x -> 2*x*x;

        // run
        System.out.println("The Twice Square of " + integer + " is " + TwiceSquare.apply(integer));
    }

    /**
     * A way to write single Line Lambda
     * @param integer Integer
     */
    private static void exampleB(Integer integer){
        // This is a Function that takes an Integer as input and returns (2 * Integer)^2
        Function<Integer, Integer> TwiceSquare = x -> 4 * x * x;

        // run
        System.out.println("The Twice Square of " + integer + " is " + TwiceSquare.apply(integer));
    }

    /**
     * You can also chain these functions together using compose and andThen
     * @param integer Integer
     */
    private static void exampleC(Integer integer){
        Function<Integer, Integer> triple = x -> x * 3;
        Function<Integer, Double> powerOfThree = x -> Math.pow(x, 3);
        Function<Double, Double> squareRoot = Math::sqrt; // lambda using method reference

        // (x*3)^3
        System.out.println("(x*3)^3 is: " + powerOfThree.compose(triple).apply(integer));
        // (x*3)^3
        System.out.println("(x*3)^3 is: " + triple.andThen(powerOfThree).apply(integer));

        // sqrt((x*3)^3)
        System.out.println("sqrt((x*3)^3) is: " + squareRoot.compose(powerOfThree.compose(triple)).apply(integer));
        // sqrt((x*3)^3)
        System.out.println("sqrt((x*3)^3) is: " + triple.andThen(powerOfThree).andThen(squareRoot).apply(integer));
    }

    // test
    public static void main(String[] args) {
        Integer integer = 2;
        exampleA(integer);
        exampleB(integer);
        exampleC(integer);
    }
}
