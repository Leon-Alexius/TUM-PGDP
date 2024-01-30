package Klausur_3.AboutThreads.ThreadSafeList.Self;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
    public static void main(String[] args) {
        // Create a new list
        List<Integer> list = new List<>();

        // Create a thread pool with 10 threads
        ExecutorService executor = Executors.newFixedThreadPool(10);

        // Create a random number generator
        Random random = new Random();

        // Submit 100 tasks to the executor
        for (int i = 0; i < 100; i++) {
            executor.submit(() -> {
                boolean flag = random.nextBoolean();
                // 50% chance to add a new element
                if (flag) {
                    list.add(random.nextInt(100));
                }
                // 50% chance to remove an element
                else {
                    list.remove();
                }
            });
        }

        System.out.println(list); // check list

        // Shutdown the executor
        executor.shutdown();
    }
}
