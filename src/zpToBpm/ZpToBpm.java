package zpToBpm;

public class ZpToBpm {

    private static int[] zpToBpm(int[] zp, int n) {
        int[] bpm = new int[n];
        for (int i = 0; i < n; i++) {
            bpm[i] = 0; // Инициализация
        }
        for (int j = n - 1; j > 0; j--) {
            int i = j + zp[j] - 1;
            bpm[i] = zp[j];
        }
        return bpm;
    }
}
