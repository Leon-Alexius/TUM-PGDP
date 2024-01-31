package Klausur_3.AboutThreads.ThreadSafe;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * A modified Semaphore approach to achieve parallel run (both Consumer and Producer) - <strong>not successful</strong>
 */
public class MultipleSemaphore {
    private static final Semaphore semaphore = new Semaphore(2); // 2 permits for the lock
    private static final RingBuffer ringBuffer = new RingBuffer();

    /**
     * This is a modified RingBuffer with capacity of 100 <br>
     * <code>dataCount</code> is <code>AtomicInteger</code> since both Consumer and Producer will access at same time
     */
    private static class RingBuffer {
        private final int[] memory = new int[100]; // initialize with size 100
        private int inputPointer = 0; // always point to the next cell, where the data is to be put
        private int outputPointer = 0; // the oldest data that is available
        private final AtomicInteger dataCount = new AtomicInteger(0);

        private boolean isEmpty() {
            return dataCount.get() == 0;
        }

        private boolean isFull() {
            return dataCount.get() == memory.length;
        }
    }

    private static class Consumer extends Thread{
        private void consume() throws InterruptedException {
            semaphore.acquire(); // acquire a permit
            try {
                // with this, we can be assured that only one Consumer runs in this block, with reference to ringBuffer
                synchronized (ringBuffer) {
                    // wait until buffer not empty
                    while (ringBuffer.isEmpty()) {
                        ringBuffer.wait();
                    }

                    // consume the data
                    System.out.println("Item at index: " + ringBuffer.outputPointer +
                            " with value: " + ringBuffer.memory[ringBuffer.outputPointer] + " is consumed by: " +
                            Thread.currentThread().getName() + ".");

                    // update the outputPointer and dataCount
                    ringBuffer.outputPointer = (ringBuffer.outputPointer + 1) % ringBuffer.memory.length;
                    ringBuffer.dataCount.decrementAndGet();

                    // tell that the buffer is not full to everyone
                    ringBuffer.notifyAll();
                }
            }
            catch (Exception e){
                synchronized (ringBuffer){
                    ringBuffer.notifyAll();
                }
            }
            finally {
                semaphore.release(); // release the permit
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

    private static class Producer extends Thread{
        Random random = new Random();
        private void produce() throws InterruptedException {
            semaphore.acquire(); // acquire a permit
            try {
                // with this, we can be assured that only one Producer runs in this block, with reference to ringBuffer
                synchronized (ringBuffer) {
                    // wait until buffer not full
                    while (ringBuffer.isFull()) {
                        ringBuffer.wait();
                    }

                    // produce the data
                    ringBuffer.memory[ringBuffer.inputPointer] = random.nextInt(100);
                    System.out.println("Item at index: " + ringBuffer.inputPointer + " with value: " +
                            ringBuffer.memory[ringBuffer.inputPointer] + " is produced by: " +
                            Thread.currentThread().getName() + ".");

                    // update the inputPointer and dataCount
                    ringBuffer.inputPointer = (ringBuffer.inputPointer + 1) % ringBuffer.memory.length;
                    ringBuffer.dataCount.incrementAndGet();

                    // tell that the buffer is not empty to everyone
                    ringBuffer.notifyAll();
                }
            }
            catch (Exception e){
                synchronized (ringBuffer){
                    ringBuffer.notifyAll();
                }
            }
            finally {
                semaphore.release(); // release the permit
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
    public static void main(String[] args) throws InterruptedException {
        try(ExecutorService executorService = Executors.newFixedThreadPool(10)){
            for(int i = 0; i < 5; i++){
                executorService.submit(new Consumer());
                executorService.submit(new Producer());
            }
        }
    }
}
