package Klausur_3.AboutThreads;

public class Examples {
    public static class MyThread extends Thread {
        public void hello(String s) {
            System.out.println(s);
        }

        public void run() {
            hello("I'm running ...");
        }

        public static void main(String[] args) throws InterruptedException {
            MyThread t = new MyThread();
            t.start(); // triggers evaluation of t.run()
            System.out.println("Thread has been started ...");
            t.join(); // wait until t finished
        }
    }

    public static class MyRunnable implements Runnable {
        public void hello(String s) {
            System.out.println(s);
        }

        public void run() {
            hello("I'm running ...");
        }

        public static void main(String[] args) throws InterruptedException {
            Thread t = new Thread(new MyRunnable());
            t.start();
            System.out.println("Thread has been started ...");
            t.join();
        }
    }
}
