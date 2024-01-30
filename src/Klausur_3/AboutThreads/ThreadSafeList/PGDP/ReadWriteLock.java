package Klausur_3.AboutThreads.ThreadSafeList.PGDP;

/**
 * A Very simple implementation of ReadWriteLock
 */
public class ReadWriteLock {
    private int countReaders = 0;
    public synchronized void startRead() throws InterruptedException {
        while (countReaders < 0) {
            wait();
        }
        countReaders++;
    }

    public synchronized void endRead() {
        countReaders--;
        if (countReaders == 0) {
            notify();
        }
    }

    public synchronized void startWrite() throws InterruptedException {
        while (countReaders != 0) {
            wait();
        }
        countReaders = -1;
    }

    public synchronized void endWrite() {
        countReaders = 0;
        notifyAll();
    }
}
