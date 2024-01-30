package Klausur_3.AboutFunctional_Interface.Example;

import java.util.function.Function;

/**
 * https://de.wikipedia.org/wiki/Newtonverfahren
 */
public class NewtonVerfahren {
    private final Function<Double, Double> f;
    private final Function<Double, Double> fPrime;
    private double x;
    private int numberOfIterations;

    public NewtonVerfahren(Function<Double, Double> f, Function<Double, Double> fPrime, double x0) {
        this.f = f;
        this.fPrime = fPrime;
        this.x = x0;
        this.numberOfIterations = 0;
    }

    public NewtonVerfahren(Function<Double, Double> f, double x0) {
        this(f, derivativeFunction(f, 0.0000001), x0);
    }

    private void nextStep() {
        x = x - f.apply(x) / fPrime.apply(x);
        numberOfIterations++;
    }

    /**
     * Same as derive() in LambdaAsFunction
     */
    private static Function<Double, Double> derivativeFunction(Function<Double, Double> f, double epsilon) {
        return x -> (f.apply(x + epsilon) - f.apply(x)) / epsilon;
    }

    protected double approximateRoot(double epsilon, int maxIterations) {
        while (Math.abs(f.apply(x)) > epsilon && numberOfIterations < maxIterations) {
            nextStep();
        }
        return x;
    }

    /**
     * Test Here
     */
    public static void main(String[] args) {
        NewtonVerfahren nv = new NewtonVerfahren(Math::sin, 1);
        System.out.println(nv.approximateRoot(0.0000001, 10000));

        NewtonVerfahren nv1 = new NewtonVerfahren(Math::sin, 3);
        System.out.println(nv1.approximateRoot(0.0000001, 10000));
    }
}
