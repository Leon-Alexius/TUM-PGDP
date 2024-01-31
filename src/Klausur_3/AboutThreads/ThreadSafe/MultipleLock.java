package Klausur_3.AboutThreads.ThreadSafe;


import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


/**
 * To allow both Producer and Consumer run at the same time, we need to modify the Buffer
 * <br>
 * Although at the end, it's still a Consumer, then a Producer, then a Consumer, etc.
 * <br><br>
 * https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/locks/ReentrantLock.html <br>
 * https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/locks/Condition.html
 */
public class MultipleLock {
    private static final RingBuffer ringBuffer = new RingBuffer();
    private static final ReentrantLock consumerLock = new ReentrantLock(); // only one lock = one Consumer at the given time
    private static final Condition notEmpty = consumerLock.newCondition(); // Condition for consumer to be allowed to run
    private static final ReentrantLock producerLock = new ReentrantLock(); // only one lock = one Producer at the given time
    private static final Condition notFull = producerLock.newCondition(); // Condition for producer to be allowed to run

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

    /**
     * Only one consumer is allowed to consume the Buffer (<code>consumerLock</code>)
     * <br> the Consumer will wait until Buffer is not empty if needed (<code>notEmpty.await()</code>)
     */
    private static class Consumer extends Thread{
        private void consume() throws InterruptedException {
            int storedData;

            consumerLock.lock(); // try to obtain consumerLock (check if this Consumer-Thread can run)
            try {
                // wait until buffer not empty
                while (ringBuffer.isEmpty()) {
                    notEmpty.await();
                }

                // consume the data
                System.out.println("Item at index: " + ringBuffer.outputPointer +
                        " with value: " + ringBuffer.memory[ringBuffer.outputPointer] + " is consumed by: " +
                        Thread.currentThread().getName() + ".");

                // update the outputPointer and dataCount
                ringBuffer.outputPointer = (ringBuffer.outputPointer + 1) % ringBuffer.memory.length;
                storedData = ringBuffer.dataCount.decrementAndGet();

                // self summon (if buffer not empty)
                if(!ringBuffer.isEmpty()){
                    notEmpty.signal();
                }
            }
            finally {
                consumerLock.unlock(); // release the lock
            }

            // tell that the buffer is not full (currently empty) - (useful when all Producer is waiting)
            if(storedData == 0){
                producerLock.lock();
                try{
                    notFull.signal();
                }
                finally {
                    producerLock.unlock();
                }
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
     * Only one producer is allowed to produce to the Buffer (producerLock)
     * <br>
     * the Producer will wait until Buffer is not full if needed (notFull.await())
     */
    private static class Producer extends Thread{
        Random random = new Random();
        private void produce() throws InterruptedException {
            int storedData;

            producerLock.lock(); // try to obtain producerLock (check if this Producer-Thread can run)
            try {
                // wait until buffer not full
                while (ringBuffer.isFull()) {
                    notFull.await();
                }

                // produce the data
                ringBuffer.memory[ringBuffer.inputPointer] = random.nextInt(100);
                System.out.println("Item at index: " + ringBuffer.inputPointer + " with value: " +
                        ringBuffer.memory[ringBuffer.inputPointer] + " is produced by: " +
                        Thread.currentThread().getName() + ".");

                // update the inputPointer and dataCount
                ringBuffer.inputPointer = (ringBuffer.inputPointer + 1) % ringBuffer.memory.length;
                storedData = ringBuffer.dataCount.incrementAndGet();

                // self summon (if buffer is not full)
                if(!ringBuffer.isFull()){
                    notFull.signal();
                }
            }
            finally {
                producerLock.unlock(); // release the lock
            }

            // tell that the buffer is not empty (currently full) - (useful when all Consumer is waiting)
            if(storedData == ringBuffer.memory.length){
                consumerLock.lock();
                try{
                    notEmpty.signal();
                }
                finally {
                    consumerLock.unlock();
                }
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

    public static void main(String[] args) throws InterruptedException {
        try(ExecutorService executorService = Executors.newFixedThreadPool(10)){
            for(int i = 0; i < 5; i++){
                executorService.submit(new Consumer());
                executorService.submit(new Producer());
            }

            /*
            shutdown and wait until everything finish (redundant for Java19+)
            executorService.shutdown();
            executorService.awaitTermination(60, TimeUnit.SECONDS);
            */
        }
    }
}
