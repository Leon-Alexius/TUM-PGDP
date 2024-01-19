package Klausur_3.AboutStreams;

import java.util.Random;
import java.util.stream.Stream;

// Parallel Stream makes it faster, but its order is not guaranteed
public class StreamParallel {
    // https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html

    /**
     * Human Infinite Stream Generator
     */
    public static Stream<Human> humanInvasion() {
        Random random = new Random(22);
        return Stream.generate(() -> new Human(Human.getRandomName(), random.nextInt(1, 80)));
    }

    /**
     * Example usage of .parallel()
     */
    public static Double calculateWeirdTotalAge(Stream<Human> stream, int limit){
        return stream.limit(limit)
                .parallel()
                .mapToDouble(Human::getAge)
                .map(age -> {
                    double temp = Math.pow(age, 4) + 0.2;
                    return Math.pow(temp, 0.5) % 100;
                })
                .sum();
    }

    // test here
    public static void main(String[] args) {
        System.out.println(calculateWeirdTotalAge(humanInvasion(), 1000));
    }
}
