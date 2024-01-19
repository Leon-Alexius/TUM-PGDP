package Klausur_3.AboutThreads;

/**
 * The <code>produce()</code> and <code>consume()</code> methods are both synchronized, which means only one can be executing at a time.
 * <br> <br>
 * The <code>wait()</code> method is used to make the current thread wait until another thread invokes the <code>notify()</code> method on the same object. This is used to ensure that the producer doesn't produce new data until the consumer has consumed the old data, and vice versa.
 * <br> <br>
 * The <code>notify()</code> method is used to wake up a single thread that is waiting on the object’s monitor. This is used to let the consumer know when new data has been produced, and to let the producer know when the data has been consumed.
 */
class SharedResource {
    private String data;
    private boolean empty = true;

    public synchronized void produce(String newData) {
        // Wait until the resource is empty
        while (!empty) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        // Produce new data
        data = newData;
        empty = false;
        System.out.println("Produced: " + data);
        // Notify the consumer
        notify();
    }

    public synchronized String consume() {
        // Wait until the resource is filled
        while (empty) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        // Consume the data
        empty = true;
        System.out.println("Consumed: " + data);
        // Notify the producer
        notify();
        return data;
    }

    public static void main(String[] args) {
        SharedResource resource = new SharedResource();

        // Producer thread
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                resource.produce("Data " + i);
            }
        }).start();

        // Consumer thread
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                resource.consume();
            }
        }).start();
    }
}
