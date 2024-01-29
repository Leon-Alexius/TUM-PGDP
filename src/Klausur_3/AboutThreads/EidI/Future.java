package Klausur_3.AboutThreads.EidI;

import java.util.concurrent.Callable;

/**
 * A simple implementation of Future (for educational purpose!)
 */
public class Future<T> implements Runnable {
    private T value = null;
    private Exception exc = null;
    private Callable<T> work;
    private Thread task;

    public Future(Callable<T> w) {
        work = w;
        task = new Thread (this);
        task.start();
    }
    public void run() {
        try {value = work.call();}
        catch (Exception e) { exc = e;}
    }
    public T get() throws Exception {
        task.join();
        if (exc != null) throw exc;
        return value;
    }
}
