package Klausur_3.AboutStreams.EIdI;

import java.util.function.Predicate;

public class Test {
    public static void main(String[] args) {
        Stream<Integer> stream = Stream.of(new Integer[]{1,2,3,4});

        // run .findFirst()
        // Integer i = stream.findFirst();
        // System.out.println(i);

        // run .forEach()
        // <Integer> consumer = a -> System.out.println(a*3);
        // stream.forEach(consumer);

        // run .filter()
        Predicate<Integer> predicate = a -> a % 2 == 0;
        Consumer<Integer> consumer1 = System.out::println;
        stream.filter(predicate).forEach(consumer1);
    }
}
