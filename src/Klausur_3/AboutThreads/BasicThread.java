package Klausur_3.AboutThreads;

import java.util.Random;
import java.util.concurrent.*;

public class BasicThread {
    /**
     * We can mark a Class as Thread-able using "extends Thread"
     * <br>
     * However, this Class can't inherit any other Parent Class
     * <br> <br>
     * <strong>Important!</strong> <br>
     * <code>ThreadUsingThread thread = new ThreadUsingThread();</code> <br>
     * <code>thread.start();</code>
     * <br>
     * https://docs.oracle.com/javase/8/docs/api/java/lang/Thread.html
     */
    private static class ThreadUsingThread extends Thread {
        // This method will be executed when the Thread is given CPU Power by the Scheduler
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ", which uses \"extends Thread\", is running");
        }

        public static void main(String[] args) {
            ThreadUsingThread thread = new ThreadUsingThread(); // create a thread object of that class
            thread.start(); // create a new thread of execution and invoke run()
        }
    }

    /**
     * Alternatively, we can mark a Class as Thread-able using "implements Runnable"
     * <br>
     * With this, the Class can still inherit a parent Class
     * <br> <br>
     * <strong>Important!</strong> <br>
     * <code>Thread thread = new Thread(new ThreadUsingRunnable());</code>
     * <br>
     * <code>thread.start();</code>
     * <br>
     * https://docs.oracle.com/javase/8/docs/api/java/lang/Runnable.html
     */
    private static class ThreadUsingRunnable implements Runnable {
        // This method will be executed when the Thread is given CPU Power by the Scheduler
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ", which uses \"implements Runnable\", is running");
        }

        public static void main(String[] args) {
            Thread thread = new Thread(new ThreadUsingRunnable()); // create a thread object of that class
            thread.start(); // create a new thread of execution and invoke run()
        }
    }

    /**
     * Another Alternative is "implements Callable".
     * <br>
     * Callable can return a result and is able to throw an Exception. (Runnable can't)
     * <br>
     * https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Callable.html
     */
    private static class ThreadUsingCallable implements Callable<Integer> {
        // Computes a result, or throws an exception if unable to do so.
        @Override
        public Integer call() throws Exception {
            System.out.println(Thread.currentThread().getName() + ", which uses \"implements Callable\", is running");
            Random rand = new Random();
            return rand.nextInt(100);  // Returns a random integer between 0 and 99
        }

        public static void main(String[] args) throws Exception {
            try(ExecutorService executor = Executors.newSingleThreadExecutor()){
                // See more about ExecutorService in AboutExecutorService.java
                Future<Integer> future = executor.submit(new ThreadUsingCallable());
                System.out.println("Returned value: " + future.get());
                executor.shutdown();
            }
        }
    }

    /**
     * Test Here to see that the result is inconsistent (Race-Condition)
     */
    public static void main(String[] args) {
        try{
            ThreadUsingThread.main(args);
            ThreadUsingRunnable.main(args);
            ThreadUsingCallable.main(args); // Exception thrown by Callable must be handled (checked Exception)
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }
}
