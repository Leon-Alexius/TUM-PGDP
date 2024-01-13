package Klausur_3.AboutStreams;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class Examples {
    private static boolean isPrime(int number) {
        IntPredicate isDivisible = value -> number % value == 0;

        return number > 1 &&
                IntStream.range(2, number)
                        .noneMatch(isDivisible);
    }

    private static void run_isPrime(){
        System.out.println(isPrime(13));
    }

    public static int sumWithCondition(List<Integer> numbers, Predicate<Integer> predicate) {

        return numbers.stream() // a stream from a collection
                .filter(predicate) // intermediate
                .mapToInt(i -> i) // intermediate
                .sum(); // terminal defined for IntStream

        // needs conversion first via mapToInt
    }

    public static void run_sumWithCondition(){
        List<Integer> numbers = new ArrayList<>();
        for(int i = 1; i < 10; i++){
            numbers.add(i);
        }

        //sum of all numbers
        System.out.println(sumWithCondition(numbers, n -> true));
        //sum of all even numbers
        System.out.println(sumWithCondition(numbers, i -> i % 2 == 0));
        //sum of all numbers greater than 5
        System.out.println(sumWithCondition(numbers, i -> i > 5));
    }

    public static void main(String[] args) {
        run_isPrime();
        run_sumWithCondition();
    }
}
