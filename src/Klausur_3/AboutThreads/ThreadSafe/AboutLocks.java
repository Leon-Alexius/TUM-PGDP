package Klausur_3.AboutThreads.ThreadSafe;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This is a modification from Semaphore, here we use <code>ReentrantLock</code> and <code>Condition</code>
 */
public class AboutLocks {
    private static final Buffer buffer = new Buffer();
    private static final ReentrantLock lock = new ReentrantLock(); // only one lock = one thread allowed at a time
    private static final Condition notEmpty = lock.newCondition(); // Buffer is not empty
    private static final Condition notFull = lock.newCondition(); // Buffer is not full

    /**
     * Buffer implementation doesn't support parallel Consumer or Producer at a time
     */
    private static class Buffer {
        private final int[] data = new int[100];
        private int lastItemIndex = -1;
    }

    /**
     * A Consumer that uses <code>lock, notEmpty, notFull</code> instead of Semaphores
     */
    private static class Consumer implements Runnable {
        private void consume() throws InterruptedException {
            lock.lock(); // try to obtain lock
            try {
                while (buffer.lastItemIndex < 0) {
                    notEmpty.await(); // wait until buffer not empty
                }
                System.out.println("Item at index: " + buffer.lastItemIndex +
                        " with value: " + buffer.data[buffer.lastItemIndex] + " is consumed by: " +
                        Thread.currentThread().getName() + ".");
                buffer.lastItemIndex -= 1;
                notFull.signal(); // tell that the buffer is not full
            } finally {
                lock.unlock(); // release the lock
            }
        }

        @Override
        public void run() {
            for (int i = 0; i < 200; i++) {
                try {
                    consume();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * A producer that uses <code>lock, notEmpty, notFull</code> instead of Semaphores
     */
    private static class Producer implements Runnable {
        Random random = new Random();

        private void produce() throws InterruptedException {
            lock.lock(); // obtain the lock to run on the Buffer
            try {
                while (buffer.lastItemIndex == buffer.data.length - 1) {
                    notFull.await(); // wait until buffer is not full
                }
                buffer.lastItemIndex += 1;
                buffer.data[buffer.lastItemIndex] = random.nextInt(100);
                System.out.println("Item at index: " + buffer.lastItemIndex + " with value: " +
                        buffer.data[buffer.lastItemIndex] + " is produced by: " +
                        Thread.currentThread().getName() + ".");
                notEmpty.signal(); // notify that the buffer is not empty
            } finally {
                lock.unlock(); // return the lock of the Buffer
            }
        }

        @Override
        public void run() {
            for (int i = 0; i < 200; i++) {
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
    public static void main(String[] args) {
        new Thread(new Producer()).start();
        new Thread(new Consumer()).start();
    }
}
