package Klausur_3.AboutThreads;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The Consumer Producer problem is:
 * <br>
 * 1. What should Consumer do when Buffer is empty -> wait() and notify() or notifyAll()
 * <br>
 * 2. What should Producer do when Buffer is full -> wait() and notify() or notifyAll()
 * <br><br>
 * Producer-Consumer problem with a single shared buffer:
 * <ol>
 *      <li>Only one Consumer at a time</li>
 *      <li>Only one Producer at a time</li>
 *      <li>Producer and Consumer can’t run simultaneously</li>
 * </ol>
 */
public class ProducerConsumer {
    /**
     * A simple Buffer Class
     */
    private static class Buffer{
        private final int[] data = new int[3];
        private int freeSpace = 3;
        private int lastItemIndex = -1;
    }

    /**
     * The consumer can notify() the Producer after consuming something, vice versa.
     * <br>
     * The Problem is that this can be problematic if there are multiple Producers and Consumers using same Buffer
     * <br> <br>
     * Here: only one Consumer, one Producer
     */
    private static class Notify{
        private static final Buffer buffer = new Buffer();
        private static class Consumer implements Runnable{
            /**
             * synchronized on buffer
             */
            private void consume() throws InterruptedException {
                synchronized(buffer){
                    while(buffer.freeSpace == 3){
                        buffer.wait();
                    }
                    System.out.println("Item at index: " + buffer.lastItemIndex +
                            " with value: " + buffer.data[buffer.lastItemIndex] + " is consumed.");
                    buffer.freeSpace += 1;
                    buffer.lastItemIndex -= 1;
                    buffer.notify();
                }
            }

            /**
             * run 6 times
             */
            @Override
            public void run() {
                for(int i = 0; i < 6; i++){
                    try {
                        consume();
                    }
                    catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        private static class Producer implements Runnable{
            Random random = new Random();
            /**
             * synchronized on buffer
             */
            private void produce() throws InterruptedException {
                synchronized(buffer){
                    while(buffer.freeSpace == 0){
                        buffer.wait();
                    }
                    buffer.lastItemIndex += 1;
                    buffer.data[buffer.lastItemIndex] = random.nextInt(100);
                    System.out.println("Item at index: " + buffer.lastItemIndex + " with value: " +
                            buffer.data[buffer.lastItemIndex] + " is produced.");
                    buffer.freeSpace -= 1;
                    buffer.notify();
                }
            }

            /**
             * run 6 times
             */
            @Override
            public void run() {
                for(int i = 0; i < 6; i++){
                    try {
                        produce();
                        // Thread.sleep(10); // to force the Scheduler to let Consumer run
                    }
                    catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        /**
         * Test here
         */
        public static void main(String[] args) throws InterruptedException {
            try(ExecutorService executorService = Executors.newFixedThreadPool(2)){
                executorService.submit(new Consumer());
                executorService.submit(new Producer());
            }

            Thread.sleep(5000); // main() is waiting for simulation
        }
    }

    /**
     * To fix this problem, we can use notifyAll and check the condition first for each Thread Type.
     * <br>
     * Producer (Buffer not full?) and Consumer (Buffer not empty?)
     * <br> <br>
     * Here: 5 Consumer, 5 Producer, each running 5 times (run to see how multiple Threads run on same Object)
     */
    private static class NotifyAll{
        private static final Buffer buffer = new Buffer();
        private static class Consumer implements Runnable{
            /**
             * synchronized on buffer
             */
            private void consume() throws InterruptedException {
                synchronized(buffer){
                    while(buffer.freeSpace == 3){
                        buffer.wait();
                    }
                    System.out.println("Item at index: " + buffer.lastItemIndex +
                            " with value: " + buffer.data[buffer.lastItemIndex] + " is consumed by: " +
                            Thread.currentThread().getName() + ".");
                    buffer.freeSpace += 1;
                    buffer.lastItemIndex -= 1;
                    buffer.notifyAll();
                }
            }

            /**
             * run 5 times
             */
            @Override
            public void run() {
                for(int i = 0; i < 5; i++){
                    try {
                        consume();
                    }
                    catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        private static class Producer implements Runnable{
            Random random = new Random();
            /**
             * synchronized on buffer
             */
            private void produce() throws InterruptedException {
                synchronized(buffer){
                    while(buffer.freeSpace == 0){
                        buffer.wait();
                    }
                    buffer.lastItemIndex += 1;
                    buffer.data[buffer.lastItemIndex] = random.nextInt(100);
                    System.out.println("Item at index: " + buffer.lastItemIndex + " with value: " +
                            buffer.data[buffer.lastItemIndex] + " is produced by: " +
                            Thread.currentThread().getName() + ".");
                    buffer.freeSpace -= 1;
                    buffer.notifyAll();
                }
            }

            /**
             * run 5 times
             */
            @Override
            public void run() {
                for(int i = 0; i < 5; i++){
                    try {
                        produce();
                    }
                    catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        /**
         * Test here
         */
        public static void main(String[] args) throws InterruptedException {
            try(ExecutorService executorService = Executors.newFixedThreadPool(10)){
                executorService.submit(new Consumer());
                executorService.submit(new Consumer());
                executorService.submit(new Consumer());
                executorService.submit(new Consumer());
                executorService.submit(new Consumer());

                executorService.submit(new Producer());
                executorService.submit(new Producer());
                executorService.submit(new Producer());
                executorService.submit(new Producer());
                executorService.submit(new Producer());
            }

            Thread.sleep(5000); // main() is waiting for simulation
        }
    }
}
