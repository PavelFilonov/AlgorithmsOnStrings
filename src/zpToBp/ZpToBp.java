package zpToBp;

public class ZpToBp {

    public static int[] zpToBp(int[] zp, int n) {
        int[] bp = new int[n];
        for (int i = 0; i < n; i++) {
            bp[i] = 0; // Инициализация
        }
        for (int j = 1; j < n; j++) {
            for (int i = j + zp[j] - 1; i >= j; i--) {
                if (bp[i] != 0) {
                    break;
                }
                bp[i] = i - j + 1;
            }
        }
        return bp;
    }
}
