package Klausur_3.AboutThreads;

import java.util.Random;
import java.util.concurrent.*;

public class AboutFuture {
    /**
         * A Thread Class That generates Random Integer
         */
    private record ThreadUsingCallable(int id) implements Callable<Integer> {
        @Override
        public Integer call() {
            Random rand = new Random();
            return rand.nextInt(100);
        }
    }

    /**
     * We can get the Value of a Thread without using Future, since the return value is <code>Integer</code>
     */
    private static void simpleGet(){
        ThreadUsingCallable threadUsingCallable = new ThreadUsingCallable(100);
        Integer value = threadUsingCallable.call();
        System.out.println("Thread with ID: " + threadUsingCallable.id + " returns value: " + value);
    }

    /**
     * Here we must use Future, since the <code>submit(Callable&lt;T&gt; task)</code> returns <code>Future&lt;T&gt;</code>
     * <br><br>
     * The problem is that <code>.get()</code> will block the current Thread, waiting for result, and can generate Exceptions.
     * <br>
     * To maximize the efficiency of a Thread, we can check <code>.isDone()</code>, if not Done, then we can tell the Thread to do something else
     */
    private static void usingExecutor(){
        try(ExecutorService executorService = Executors.newSingleThreadExecutor()){
            int id = 123;
            Future<Integer> future = executorService.submit(new ThreadUsingCallable(id));

            // future.get() can throw an InterruptedException or ExecutionException
            // this will also block the current Thread, waiting for the other Thread to finish its calculation
            // thus, we can use .isDone() so that current Thread can do something else
            while(!future.isDone()){
                System.out.println("Waiting... Doing Something else!");
                Thread.sleep(500); // Assume this is doing something else for 500ms
            }
            Integer value = future.get();
            System.out.println("Thread with ID: " + id + " returns value: " + value);
        }
        catch (ExecutionException e) {
            // ExecutionException if the Callable threw an exception.
            System.out.println("ExecutionException!\n" + e.getMessage());
        }
        catch (InterruptedException e) {
            // InterruptedException if the current thread was interrupted while waiting for the result
            System.out.println("InterruptedException!\n" + e.getMessage());
        }
    }

    /**
     * Test Here
     */
    public static void main(String[] args) {
        simpleGet();
        usingExecutor();
    }
}
