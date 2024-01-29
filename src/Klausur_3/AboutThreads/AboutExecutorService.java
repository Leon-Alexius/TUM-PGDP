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
    private record ThreadUsingRunnable(int id) implements Runnable {
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
     * An example of waiting for all Threads in a pool (ExecutorService) to finish.
     * <br>
     * We can handle the case where not all Threads finished executing using simple <code>if-else</code>
     */
    private static void safeExecutor(){
        try(ExecutorService executorService = Executors.newFixedThreadPool(5)){
            for(int i = 0; i < 5; i++){
                executorService.submit(new ThreadUsingRunnable(i));
            }

            // Initiates an orderly shutdown, previously submitted tasks are executed, but no new tasks will be accepted.
            executorService.shutdown();

            // wait for all tasks to finish
            // true if this executor terminated and false if the timeout elapsed before termination
            boolean flag;
            try {
                flag = executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // handle here
            if(flag){
                System.out.println("Process finished completely");
            }
            else{
                System.out.println("The Process doesn't finish completely");
            }
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
