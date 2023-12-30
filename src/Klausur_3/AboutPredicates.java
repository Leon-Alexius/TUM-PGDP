package Klausur_3;

import java.util.function.Predicate;

public class AboutPredicates {
    public static void main(String[] args) {
        // This is a Predicate that tests if an Integer is even
        Predicate<Integer> isEven = (Integer x) -> {
            return x % 2 == 0;
        };

        // Now you can use this predicate with any integer
        Integer a = 5;
        Integer b = 6;
        System.out.println(a + " is even: " + isEven.test(a));
        System.out.println(b + " is even: " + isEven.test(b));
    }
}
