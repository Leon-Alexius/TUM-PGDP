package DiscreteStructure;

/**
 * HA 11 Aufgabe 11.2
 */
public class TruthTable {
    public static void main(String[] args) {
        int[] signal = new int[6];
        System.out.printf("%-5s %-5s %-5s %-5s %-5s %-5s %-5s %-9s\n", "No.", "p", "q", "r", "x", "y", "z", "Expression");
        int count = 1;
        do {
            boolean p = signal[0] == 1;
            boolean q = signal[1] == 1;
            boolean r = signal[2] == 1;
            boolean x = signal[3] == 1;
            boolean y = signal[4] == 1;
            boolean z = signal[5] == 1;
            boolean result = (p && ((q && (r || (!r && x && !z))) || (!q && ((r && x && z) || (!r && (x || (!x && !y && !z))))))) || (!p && ((q && ((r && x) || (!r && z))) || (!q && ((r && (x || (!x && (y || (!y && z))))) || (!r && x && z)))));
            System.out.printf("%-5d %-5s %-5s %-5s %-5s %-5s %-5s %-9s\n", count++, p, q, r, x, y, z, result);
        } while (incrementSignal(signal));
    }

    public static boolean incrementSignal(int[] signal) {
        for (int i = signal.length - 1; i >= 0; i--) {
            if (signal[i] == 0) {
                signal[i] = 1;
                return true;
            } else {
                signal[i] = 0;
            }
        }
        return false;
    }
}
