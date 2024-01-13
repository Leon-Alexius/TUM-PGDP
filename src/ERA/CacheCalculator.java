package ERA;

public class CacheCalculator {
    private final String[] hexAddresses;
    private final long[] formattedAddresses;
    private final String[][] cacheMemory;

    public CacheCalculator(String[] hexAddresses, String[][] initialCacheMemory){
        this.hexAddresses = hexAddresses;
        this.formattedAddresses = new long[hexAddresses.length];
        this.cacheMemory = initialCacheMemory;
        for (int i = 0; i < hexAddresses.length; i++) {
            long address = Long.parseLong(hexAddresses[i].substring(2), 16);
            formattedAddresses[i] = address >> 6;
        }
    }

    public String formatHexAddress(long formattedAddress) {
        return "0x" + String.format("%X", formattedAddress);
    }

    public void memoryView() {
        System.out.printf("%-10s | %-15s | %-15s | %-15s%n", "Number", "Memory Address", "Cache Set Index", "Tag");
        System.out.println("---------------------------------------------------");

        for (int i = 0; i < hexAddresses.length; i++) {
            long address = Long.parseLong(hexAddresses[i].substring(2), 16);
            long cacheSetIndex = (address >> 3) & 7;
            String tag = formatHexAddress(formattedAddresses[i]);
            System.out.printf("%-10d | %-15s | %-15s | %-15s%n", i+1, hexAddresses[i], "0x" + String.format("%08X", cacheSetIndex), tag);
        }
        System.out.println();
    }

    public void cacheMemoryView(){
        for (int i = 0; i < hexAddresses.length; i++) {
            long address = Long.parseLong(hexAddresses[i].substring(2), 16);
            int index = (int)((address >> 3) & 7);

            // Shift the contents of the inner array to the next cell
            if (cacheMemory[index][0] != null) {
                for (int j = cacheMemory[index].length - 1; j > 0; j--) {
                    cacheMemory[index][j] = cacheMemory[index][j - 1];
                }
            }

            cacheMemory[index][0] = formatHexAddress(formattedAddresses[i]);
        }

        // Print the array in the grid format
        for (int i = 0; i < cacheMemory.length; i++) {
            System.out.printf("Set %d => | ", i);
            for (int j = 0; j < cacheMemory[i].length; j++) {
                System.out.printf("%-10s | ", (cacheMemory[i][j] != null ? cacheMemory[i][j] : ""));
            }
            System.out.println();
        }
    }

    // RUN HERE
    public static void main(String[] args) {
        String[] hexAddresses = {
                "0x706F8", "0x2FCB80", "0x150CF6", "0xE55289", "0xF68088", "0xD1740C",
                "0xAB7F93", "0xFD1AC2", "0xF8C132", "0x595308", "0x9DB773", "0x2728B",
                "0x9D2DDF", "0xF3D465", "0xB3FF07", "0x515BFC", "0x4D512A", "0x2CA60F",
                "0x8F466", "0x714BBA", "0x83FCB5", "0x2F60A", "0xF33618", "0x798015", "0x4717DC"
        };

        String[][] initialCacheMemory = {
                {"0x14EAAB", "0x9F30", "0x29D556", "0x126A"},
                {"0x55EF", "0x7A20A", "0x55D55", null},
                {"0x55EF", "0x33C19", "0xCFB28", "0x7521"},
                {"0x17B74", "0x55EF", null, null},
                {"0x1227", null, null, null},
                {"0xBDB", null, null, null},
                {"0x3D72A", null, null, null},
                {null, null, null, null}
        };

        CacheCalculator cacheCalculator = new CacheCalculator(hexAddresses, initialCacheMemory);
        cacheCalculator.memoryView();
        cacheCalculator.cacheMemoryView();
    }
}
