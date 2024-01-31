package Klausur_3.AboutThreads.MultiStackThreadSafe;

public class Test {
    public static void main(String[] args) {
        MultiStack m = new MultiStack();
        for (int i = 0; i < 20; i++) {
            m.push(i);
            System.out.println(m + " - " + m.getSize());
        }

        for (int i = 20; i >= 0; i--) {
            System.out.println(i + ": " + m.exist(i));
        }
    }
}
