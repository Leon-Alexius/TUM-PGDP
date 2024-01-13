package Klausur_3.AboutFunctional_Interface;

import java.util.function.Supplier;

/**
 * There is no requirement that a new or distinct result be returned each time the supplier is invoked.
 */
public class AboutSupplier {
    // https://docs.oracle.com/javase/8/docs/api/java/util/function/Supplier.html
    public static void main(String[] args) {
        // a Supplier that generates random numbers
        Supplier<Double> randomValue = () -> Math.random();

        // to invoke a Supplier, we use .get()
        System.out.println(randomValue.get());
    }
}
