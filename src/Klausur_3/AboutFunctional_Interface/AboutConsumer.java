package Klausur_3.AboutFunctional_Interface;

import java.util.function.Consumer;

/**
 * Represents an operation that accepts a single input argument and returns no result.
 * Unlike most other functional interfaces, Consumer is expected to operate via side effects.
 */
public class AboutConsumer {
    // https://docs.oracle.com/javase/8/docs/api/java/util/function/Consumer.html

    /**
     * .accept() to consume a value; .andThen() to chain Consumer instances
     */
    public static void main(String[] args) {
        // Define three Consumers
        Consumer<String> print = System.out::println;
        Consumer<String> printUpperCase = x -> System.out.println(x.toUpperCase());
        Consumer<String> printLowerCase = x -> System.out.println(x.toLowerCase());

        // Use accept() to consume a value
        print.accept("Hello, World!"); // Outputs "Hello, World!"

        // Use andThen() to chain Consumers
        print.andThen(printUpperCase).andThen(printLowerCase).accept("Hello, World!");
        // Outputs "Hello, World!", "HELLO, WORLD!", "hello, world!"
    }
}
