package Klausur_3.AboutFunctional_Interface;

import java.util.function.Predicate;

/**
 * Represents a predicate (boolean-valued function) of one argument.
 */
public class AboutPredicate {
    // https://docs.oracle.com/javase/8/docs/api/java/util/function/Predicate.html

    /**
     * This will negate a result of a predicate
     */
    private static <T> boolean negation(Predicate<T> predicate, T input){
        Predicate<T> negated = predicate.negate();
        return negated.test(input);
    }

    /**
     * example usage of .and()
     */
    private static <T> boolean and(Predicate<T> predicate1, Predicate<T> predicate2, T input){
        Predicate<T> combined_AND = predicate1.and(predicate2);
        return combined_AND.test(input);
    }

    /**
     * Example usage of .or()
     */
    private static <T> boolean or(Predicate<T> predicate1, Predicate<T> predicate2, T input){
        Predicate<T> combined_OR = predicate1.or(predicate2);
        return combined_OR.test(input);
    }

    /**
     * Example usage of static Method Predicate.isEqual()
     */
    private static <T> boolean isEqual(T obj1, T obj2){
        Predicate<T> isEqual = Predicate.isEqual(obj1);
        return isEqual.test(obj2);
    }

    /**
     * in Java, a Predicate is a functional interface that <b>takes only one argument</b> and returns a boolean.
     * <br><br>
     * If you want to use a lambda function with three parameters, you can define your own functional interface.
     */
    @FunctionalInterface
    interface ThreeParameterPredicate<X, Y, Z> {
        boolean test(X x, Y y, Z z);
    }

    /**
     * Test
     */
    public static void main(String[] args) {
        Predicate<Integer> isEven = x -> x % 2 == 0;
        Predicate<Integer> greaterThanFive = (x) -> x > 5;

        System.out.println(negation(isEven, 5)); // true
        System.out.println(and(isEven, greaterThanFive, 6)); // true
        System.out.println(or(isEven, greaterThanFive, 11)); // true

        AboutPredicate aboutPredicate = new AboutPredicate();
        AboutPredicate aboutPredicate1 = new AboutPredicate();
        System.out.println(isEqual(aboutPredicate, aboutPredicate1)); // false

        // but you can actually just do this (direct use of ||)
        Predicate<Integer> isMultipleOf2Or7 = num -> num % 2 == 0 || num % 7 == 0;

        // Three Parameter Predicate (W09H01 - Warmup)
        ThreeParameterPredicate<Double, Double, Double> predicate = (x, y, z) -> (z != 0) &&
                ((z > 0 && x < y) || (z < 0 && x > y));
        System.out.println(predicate.test(4.0, 2.0, 1.0));
    }
}
