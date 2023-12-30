package Klausur_3;

import java.util.function.Function;

public class AboutLambdas {
    public static void main(String[] args) {
        // This is a Function that takes an Integer as input and returns its square
        Function<Integer, Integer> square = (Integer x) -> {
            return x * x;
        };

        // This is another Function that takes an Integer as input and returns its double
        Function<Integer, Integer> doubleValue = (Integer x) -> {
            return x * 2;
        };

        // Now you can use these functions with any integer
        Integer a = 5;
        System.out.println("The square of " + a + " is " + square.apply(a));
        System.out.println("The double of " + a + " is " + doubleValue.apply(a));

        // You can also chain these functions together using compose and andThen
        // compose will pass the result of doubleValue function to square function
        System.out.println("The square of the double of " + a + " is " + square.compose(doubleValue).apply(a));
        // andThen will pass the result of square function to doubleValue function
        System.out.println("The double of the square of " + a + " is " + square.andThen(doubleValue).apply(a));
    }
}
