package Klausur_3.AboutThreads;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThread {
    /**
     * A Thread Class that generates random Integer
     */
    private record ThreadUsingRunnable() implements Runnable {
        @Override
        public void run() {
            Random rand = new Random();
            int result = rand.nextInt(100);  // Returns a random integer between 0 and 99
            System.out.println(Thread.currentThread().getName() + " gets the value: " + result);
        }
    }

    /*
    ====================================================================================================================
                                              Normal Thread Initiation
    ====================================================================================================================
     */

    /**
     * Example of running multiple threads using the same Runnable object
     */
    private static void multiThread_sameObj(){
        ThreadUsingRunnable runnable = new ThreadUsingRunnable(); // create a Runnable object
        for(int i = 0; i < 5; i++){
            Thread thread = new Thread(runnable);
            thread.start(); // create a new thread with the same Runnable object and start it
        }
    }

    /**
     * Example of running multiple threads each with a different Runnable object
     */
    private static void multiThread_differentObj(){
        for(int i = 0; i < 5; i++){
            ThreadUsingRunnable runnable = new ThreadUsingRunnable(); // create a new Runnable object
            Thread thread = new Thread(runnable);
            thread.start(); // create a new thread with the new Runnable object and start it
        }
    }

    /*
    ====================================================================================================================
                                              Using ExecutorService
                                              (cleaner and better)
    ====================================================================================================================
     */

    /**
     * Example of ExecutorService running multiple threads using the same Runnable object
     */
    private static void executorMulti_sameObj(){
        try(ExecutorService executorService = Executors.newFixedThreadPool(5)){
            ThreadUsingRunnable runnable = new ThreadUsingRunnable(); // create a Runnable object
            for(int i = 0; i < 5; i++){
                executorService.submit(runnable); // submit the same Runnable object to the executor
            }
        }
    }

    /**
     * Example of ExecutorService running multiple threads each with a different Runnable object
     */
    private static void executorMulti_differentObj(){
        try(ExecutorService executorService = Executors.newFixedThreadPool(5)){
            for(int i = 0; i < 5; i++){
                ThreadUsingRunnable runnable = new ThreadUsingRunnable(); // create a new Runnable object
                executorService.submit(runnable); // submit the new Runnable object to the executor
            }
        }
    }

    /**
     * Test Here (sleep to wait for each test)
     */
    public static void main(String[] args) throws InterruptedException {
        // Test without ExecutorService
        System.out.println("Starting Test without ExecutorService");

        System.out.println("Test: multiThread_sameObj");
        multiThread_sameObj();
        Thread.sleep(500);

        System.out.println("\nTest: multiThread_differentObj");
        multiThread_differentObj();
        Thread.sleep(500);

        // Test ExecutorService
        System.out.println("\nStarting Test with ExecutorService");

        System.out.println("Test: executorMulti_sameObj");
        executorMulti_sameObj();
        Thread.sleep(500);

        System.out.println("\nTest: executorMulti_differentObj");
        executorMulti_differentObj();
    }
}
