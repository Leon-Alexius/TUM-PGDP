package Klausur_3.AboutThreads.EidI;

public class MultiThreading {
    public static class Counter {
        private int count = 0;

        // Synchronized method to increment the counter
        public synchronized void synchronizedIncrement() {
            count++;
        }

        // Non-Synchronized method to increment the counter
        public void nonSynchronizedIncrement(){
            count++;
        }

        // Method to get the current count
        public int getCount() {
            return count;
        }
    }

    public static class MyThread extends Thread {
        private final Counter counter;

        public MyThread(Counter counter) {
            this.counter = counter;
        }

        public void run() {
            for(int i = 0; i < 1000; i++) {
                counter.synchronizedIncrement();
                // counter.nonSynchronizedIncrement(); // test this method to see the difference
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        MyThread thread1 = new MyThread(counter);
        MyThread thread2 = new MyThread(counter);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Final count: " + counter.getCount());
    }
}
