package Klausur_3.AboutThreads;

import java.util.concurrent.Semaphore;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * There are two types of semaphores:
 * <br>
 * 1. <b>Binary Semaphore</b>: Similar to a mutex lock, it can have only two values – 0 and 1. It is used to implement the solution of the critical section problem with multiple processes.
 * <br>
 * 2. <b>Counting Semaphore</b>: Its value can range over an unrestricted domain. It is used to control access to a resource that has multiple instances1.
 * <br> <br>
 * In the context of the Producer-Consumer problem, we need two counting semaphores – Full and Empty. <br>
 * Full keeps track of the number of items in the buffer at any given time, and Empty keeps track of the number of unoccupied slots1.
 * <br> <br>
 * https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Semaphore.html#acquire--
 */
public class AboutSemaphore {
    /**
     * A more simplified Buffer (compare to ProducerConsumer.java)
     * <br>
     * Here we don't need to know if it is empty or full
     */
    private static class Buffer {
        private final int[] data = new int[3];
        private int lastItemIndex = -1;
    }

    /**
     * mutex = ensures that only one producer or consumer accesses the buffer at a time (permits = 1)
     * <br>
     * full = keeps track of the number of items in the buffer (permit count = 0)
     * <br>
     * empty = keeps track of the number of empty/ free spaces in the buffer (permit count = buffer size)
     */
    private static final Buffer buffer = new Buffer();
    private static final Semaphore mutex = new Semaphore(1);
    private static final Semaphore full = new Semaphore(0); // start = 0 permit
    private static final Semaphore empty = new Semaphore(buffer.data.length);

    /**
     * .acquire() = Acquires a permit from this semaphore, blocking until one is available <br>
     * .release() = Releases a permit, returning it to the semaphore.
     */
    private static class Consumer implements Runnable {
        private void consume() throws InterruptedException {
            full.acquire(); // get 1 permit from full
            mutex.acquire(); // get 1 permit from mutex
            System.out.println("Item at index: " + buffer.lastItemIndex +
                    " with value: " + buffer.data[buffer.lastItemIndex] + " is consumed by: " +
                    Thread.currentThread().getName() + ".");
            buffer.lastItemIndex -= 1;
            mutex.release(); // give mutex back its permit
            empty.release(); // increase the empty permit count
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                try {
                    consume();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * Take one permit from <code>empty</code>, give the permit to <code>full</code>
     * <br><br>
     * <strong>Notes!</strong><br>
     * One Thread don't need to have a permit to call <code>.release()</code>, this can produce a bug if you forgot
     * to <code>.acquire()</code> first (total permit increased = bad), and vice versa.
     */
    private static class Producer implements Runnable {
        Random random = new Random();

        private void produce() throws InterruptedException {
            empty.acquire(); // get 1 permit from empty (check if any cell is empty)
            mutex.acquire(); // get permit from mutex (can I run?)
            buffer.lastItemIndex += 1;
            buffer.data[buffer.lastItemIndex] = random.nextInt(100);
            System.out.println("Item at index: " + buffer.lastItemIndex + " with value: " +
                    buffer.data[buffer.lastItemIndex] + " is produced by: " +
                    Thread.currentThread().getName() + ".");
            mutex.release(); // give back permit to mutex
            full.release(); // increase full permit
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                try {
                    produce();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * Test Here
     */
    public static void main(String[] args) throws InterruptedException {
        try(ExecutorService executorService = Executors.newFixedThreadPool(10)){
            executorService.submit(new Consumer());
            executorService.submit(new Consumer());
            executorService.submit(new Consumer());

            executorService.submit(new Producer());
            executorService.submit(new Producer());
            executorService.submit(new Producer());
        }

        Thread.sleep(5000); // main() is waiting for simulation
    }
}
