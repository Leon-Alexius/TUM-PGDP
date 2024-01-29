package Klausur_3.AboutThreads;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Instead of doing: <br>
 * <code>
 *     Thread thread = new Thread(new ThreadUsingRunnable());
 *     <br>
 *     thread.start();
 * </code> <br><br>
 * We can now do: <br>
 * <code>
 *     ExecutorService executorService = Executors.newSingleThreadExecutor()
 *     <br>
 *     executorService.submit(new ThreadUsingRunnable())
 * </code> <br><br>
 * This will handle the <code>.start()</code> and what kind of Thread it is
 */
public class AboutExecutorService {
    /**
     * A Thread Class that generates random Integer
     */
    private static class ThreadUsingRunnable implements Runnable {
        private final int id;

        public ThreadUsingRunnable(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            Random rand = new Random();
            int result = rand.nextInt(100);  // Returns a random integer between 0 and 99
            System.out.println("Thread " + id + " has returned value " + result);
        }
    }

    /**
     * Example of ExecutorService running the Threads Concurrently
     */
    private static void executorConcurrent(int numberOfThreads){
        // using try-with, any resource will be closed regardless of whether the try statement completes normally or abruptly
        try(ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads)){
            for (int i = 0; i < numberOfThreads; i++) {
                executorService.submit(new ThreadUsingRunnable(i)); // submit i-th new Thread
            }
            // executorService.shutdown(); // redundant when using try-with
        }
    }

    /**
     * Example of ExecutorService running the Threads Sequentially
     */
    private static void executorSequential(int numberOfThreads){
        try(ExecutorService executorService = Executors.newSingleThreadExecutor()){
            for (int i = 0; i < numberOfThreads; i++) {
                executorService.submit(new ThreadUsingRunnable(i)); // submit i-th new Thread
            }
            // executorService.shutdown(); // redundant when using try-with
        }
    }

    /**
     * Test here to see Concurrent vs Sequential (Concurrent will result in random Thread Execution - Race Condition)
     */
    public static void main(String[] args) {
        System.out.println("Start Concurrent Test!");
        executorConcurrent(5);
        System.out.println("\nStart Sequential Test!");
        executorSequential(5);
    }
}
