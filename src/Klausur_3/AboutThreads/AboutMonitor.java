package Klausur_3.AboutThreads;

public class AboutMonitor {
    /**
     * A Counter Class with a <code>synchronized</code> method and starting <code>value=0</code>
     */
    protected static class SharedResource {
        private int value = 0;
        /**
         * Synchronized method to increment the counter
         */
        protected synchronized void synchronizedIncrement() {
            value++;
            try {
                // .sleep() doesn't release the lock! (hold the lock longer)
                Thread.sleep(1);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        protected int getValue() {
            return value;
        }
    }

    /**
     * Simple Thread that attempts to increment the given Resource 100-times
     */
    private record MyThread(SharedResource sharedResource) implements Runnable {
        public void run() {
            for (int i = 0; i < 100; i++) {
                sharedResource.synchronizedIncrement();
            }
        }
    }

    /**
     * Shows how Monitor blocking works
     */
    private static void doSimulation(int i) throws InterruptedException {
        while(i > 0){
            System.out.println("Starting Experiment: " + i--);
            SharedResource sharedResource = new SharedResource(); // create a single Resource object

            // 5 Threads using same Resource
            Thread t1 = new Thread(new MyThread(sharedResource));
            Thread t2 = new Thread(new MyThread(sharedResource));
            Thread t3 = new Thread(new MyThread(sharedResource));
            Thread t4 = new Thread(new MyThread(sharedResource));
            Thread t5 = new Thread(new MyThread(sharedResource));

            // start all threads
            t1.start();
            t2.start();
            t3.start();
            t4.start();
            t5.start();

            // check the States of the Threads (can't do check inside run() if blocked!)
            while (t1.isAlive() || t2.isAlive() || t3.isAlive() || t4.isAlive() || t5.isAlive()) {
                System.out.println("State of t1: " + t1.getState());
                System.out.println("State of t2: " + t2.getState());
                System.out.println("State of t3: " + t3.getState());
                System.out.println("State of t4: " + t4.getState());
                System.out.println("State of t5: " + t5.getState());
                System.out.println();
                Thread.sleep(500); // check each 500ms (sonst too much)
            }

            // Expected: 5 * 100 = 500
            System.out.println("Final count: " + sharedResource.getValue() + "\n");
        }
    }

    /**
     * Example of State: <br>
     * <code>
     * State of t1: RUNNABLE <br>
     * State of t2: BLOCKED <br>
     * State of t3: BLOCKED <br>
     * State of t4: BLOCKED <br>
     * State of t5: BLOCKED
     * </code>
     * <br>
     * or: <br>
     * <code>
     * State of t1: BLOCKED <br>
     * State of t2: BLOCKED <br>
     * State of t3: TIMED_WAITING <br>
     * State of t4: BLOCKED <br>
     * State of t5: TERMINATED
     * </code>
     */
    public static void main(String[] args) throws InterruptedException {
        try {
            doSimulation(1);
        }
        catch (InterruptedException interruptedException){
            System.out.println(interruptedException.getMessage());
        }
    }
}
