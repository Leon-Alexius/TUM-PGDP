package Klausur_3.AboutThreads;

import java.util.Objects;

/**
 * There are several Thread-States:
 * <ul>
 *     <li>NEW: A thread that has not yet started is in this stat</li>
 *     <li>RUNNABLE: A thread executing in the Java virtual machine is in this state.</li>
 *     <li>TIMED_WAITING: A thread that is waiting for another thread to perform an action for up to a specified waiting time is in this state.</li>
 *     <li>WAITING: A thread that is waiting indefinitely for another thread to perform a particular action is in this state.</li>
 *     <li>BLOCKED: A thread that is blocked waiting for a monitor lock is in this state.</li>
 *     <li>TERMINATED: A thread that has exited is in this state.</li>
 * </ul>
 * https://docs.oracle.com/javase/8/docs/api/java/lang/Thread.State.html
 */
public class States extends Thread {
    private static Thread t1;
    private static Thread t2;
    public void run() {
        try {
            // Getting Thread-0 States
            if(Objects.equals(Thread.currentThread().getName(), "Thread-0")){
                System.out.println("State of thread t1 while running - " + t1.getState());
            }
            if(Objects.equals(Thread.currentThread().getName(), "Thread-1")){
                System.out.println("State of thread t1 while t2 is running - " + t1.getState());
            }

            // Make current Thread Sleep
            Thread.sleep(3000);

            // delay Thread-1 (t2) and check t1 (t1 is supposed to have called .join())
            if(Objects.equals(Thread.currentThread().getName(), "Thread-1")){
                Thread.sleep(3000);
                System.out.println("State of thread t1 after calling t2.join() - " + t1.getState());
            }

            // call .join() to make Thread-0 (t1) waits for t2 to finish
            if(Objects.equals(Thread.currentThread().getName(), "Thread-0")){
                t2.join();
            }
        }
        catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Run to see many kind of States (except Blocked)
     */
    public static void main(String[] args) throws InterruptedException {
        // 2 Thread object
        States states = new States();
        States states1 = new States();

        // t1 -> states; t2 -> states1
        t1 = states;
        t2 = states1;
        System.out.println("State of thread t1 after creating it - " + t1.getState());

        t1.start();
        Thread.sleep(300); // delay main to get precise result

        t2.start();

        // delay main to wait until both thread dies
        t1.join();
        t2.join();
        System.out.println("State of thread t1 when it has finished it's execution - " + t1.getState());
    }
}
