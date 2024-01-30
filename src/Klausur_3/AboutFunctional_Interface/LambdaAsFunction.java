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

    /**
     * Take an array of coefficient and return a Function of Polynom <br>
     * <code>{3, 2, 0, 4}</code> = 4x^3 + 0x^2 + 2x + 3
     */
    public static Function<Double, Double> polynom(double[] coefficients) {
        return x -> {
            double result = 0;
            for (int i = 0; i < coefficients.length; i++) {
                result += coefficients[i] * Math.pow(x, i);
            }
            return result;
        };
    }

    /**
     * Return the 1st derivative of the Coefficient
     * <br>
     * <code>{3, 2, 0, 4}</code> = 12x^2 + 0x + 2
     */
    public static Function<Double, Double> polynomDerivative(double[] coefficients) {
        return x -> {
            double result = 0;
            for (int i = 1; i < coefficients.length; i++) {
                result += coefficients[i] * i * Math.pow(x, i - 1);
            }
            return result;
        };
    }

    /**
     * Return the derivative of the given function (polynom()); subject to inaccuracy
     * <br> <br>
     * <code>
     * Function<Double, Double> function = polynom(coefficients); <br>
     * Function<Double, Double> firstDerivative = derive(function); <br>
     * Function<Double, Double> secondDerivative = derive(firstDerivative); <br>
     * </code>
     */
    public static Function<Double, Double> derive(Function<Double, Double> function) {
        double dx = 1e-7; // small delta x
        return x -> (function.apply(x + dx) - function.apply(x)) / dx;
    }

    /**
     * Test
     */
    public static void main(String[] args) {
        Integer integer = 2;
        exampleA(integer);
        exampleB(integer);
        exampleC(integer);

        double[] coefficients = {1.0, 2.0, 3.0};
        Function<Double, Double> function = polynom(coefficients);
        System.out.println(function.apply(2.0));

        Function<Double, Double> firstDerivative1 = polynomDerivative(coefficients);
        System.out.println(firstDerivative1.apply(2.0));
        Function<Double, Double> firstDerivative = derive(function);
        System.out.println(firstDerivative.apply(2.0));
    }
}
