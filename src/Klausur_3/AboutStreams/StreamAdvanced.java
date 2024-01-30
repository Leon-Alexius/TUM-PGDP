package Klausur_3.AboutStreams;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamAdvanced<T> {
    /*
    ====================================================================================================================
                                                   OPTIONAL
    ====================================================================================================================
     */

    /**
     * Optional is a container for an Object
     */
    private static class OptionalExamples{
        // https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html
        // https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html

        /**
         * Example to creating Optional
         */
        private static <T> Optional<T> createOptional(T object){
            if(object == null){
                return Optional.empty();
            }
            else {
                return Optional.of(object);
            }
        }

        /**
         * Get the value of an Optional, or a default Human if Optional is empty or null
         */
        @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
        private static Human getValueSafely(Optional<Human> optional) {
            /*
            orElseGet(Supplier<? extends T> other)
            Return the value if present, otherwise invoke other and return the result of that invocation.
             */
            return optional.orElseGet(() -> new Human("default", 0));
            // see also: optional.orElse(T other)
        }

        /**
         * Example Usage of .max() of Java Stream
         */
        private static Optional<Human> getOldestHuman(Stream<Human> stream){
            if (stream == null){
                return Optional.empty();
            }
            return stream.max(Comparator.comparing(Human::getAge));
        }

        /**
         * Example Usage of .min() of Java Stream
         */
        private static Optional<Human> getYoungestHuman(Stream<Human> stream){
            if (stream == null) {
                return Optional.empty();
            }
            return stream.min(Comparator.comparing(Human::getAge));
        }

        /**
         * Get a random (usually first) Human using .findAny() that matches the criteria (or first Human using .findFirst())
         */
        private static Optional<Human> getOneHumanWithCriteria(Stream<Human> stream, Predicate<Human> predicate, boolean guaranteeFirst){
            if(guaranteeFirst){
                return stream.filter(predicate).findFirst();
            }
            else {
                return stream.filter(predicate).findAny();
            }
        }
    }

    /*
    ====================================================================================================================
                                            IntStream using .mapToInt()
    ====================================================================================================================
     */

    /**
     * IntStream is a special Stream made to handle Integer. For example, it has .sum() method
     */
    private static class IntegerStreamExample{
        // https://docs.oracle.com/javase/8/docs/api/java/util/stream/IntStream.html
        // https://docs.oracle.com/javase/8/docs/api/java/util/IntSummaryStatistics.html
        private final Stream<Integer> originalStream;
        private IntStream intStream;
        private IntegerStreamExample(Stream<Integer> stream){
            this.originalStream = stream;
            this.intStream = stream.mapToInt(i -> i); // .mapToInt()
        }

        /**
         * because stream can only be used once
         */
        private void resetIntStream(){
            this.intStream = originalStream.mapToInt(i -> i);
        }

        /**
         * Usage of .sum() is very easy
         */
        private long sumOfElement(){
            return intStream.sum();
        }

        /**
         * A very useful method for IntStream
         */
        private void getSummaryStatistic(){
            IntSummaryStatistics intSummaryStatistics = intStream.summaryStatistics();

            int maxValue = intSummaryStatistics.getMax();
            int minValue = intSummaryStatistics.getMin();
            long totalValue = intSummaryStatistics.getSum();
            double averageValue = intSummaryStatistics.getAverage();
            long elementCount = intSummaryStatistics.getCount();

            System.out.println("Highest number in List : " + maxValue);
            System.out.println("Lowest number in List : " + minValue);
            System.out.println("Sum of all numbers : " + totalValue);
            System.out.println("Average of all numbers : " + averageValue);
            System.out.println("Number of Elements in List : " + elementCount);
        }
    }


    /*
    ====================================================================================================================
                                                   TEST HERE
    ====================================================================================================================
     */
    public static void main(String[] args) {
        Human human = new Human("a", 10);
        Stream<Human> stream = Stream.of(human);
        Stream<Human> streamCopy = Stream.of(human);
        Stream<Human> biggerStream = StreamBasic.humanAndFrieds(stream);
        Stream<Human> biggerStreamCopy = StreamBasic.humanAndFrieds(streamCopy);

        // check the Stream
        biggerStream.forEach(System.out::println);

        // get Youngest
        Optional<Human> optional = OptionalExamples.getYoungestHuman(biggerStreamCopy);
        System.out.println(OptionalExamples.getValueSafely(optional));

        // IntStream Example
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        Stream<Integer> stream1 = numbers.stream();
        IntegerStreamExample integerStreamExample = new IntegerStreamExample(stream1);
        integerStreamExample.getSummaryStatistic();
    }
}
