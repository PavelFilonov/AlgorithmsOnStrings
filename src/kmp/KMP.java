package kmp;

import static prefixBorderArray.PrefixBorderArray.prefixBorderArray;

public class KMP {

    public static void main(String[] args) {
        String text = "abcabeabcabcabd";
        String pattern = "abcabd";
        kmp(pattern, text);
    }

    public static void kmp(String pattern, String text) {
        int[] bpm = prefixBorderArray(pattern);
        int m = pattern.length();
        int n = text.length();
        bpm = bpToBpm(bpm, m);
        int k = 0;
        for (int i = 0; i < n; i++) {
            while (k != 0 && pattern.charAt(k) != text.charAt(i)) {
                k = bpm[k - 1];
            }
            if (pattern.charAt(k) == text.charAt(i)) {
                k++;
            }
            if (k == m) {
                System.out.println("Вхождение с позиции " + (i - k + 1) + "\n");
                k = bpm[k - 1];
            }
        }
    }

    private static int[] bpToBpm(int[] bp, int n) {
        int[] bpm = new int[n];
        bpm[n - 1] = bp[n - 1];
        for (int i = 1; i < n - 1; i++) {
            if (bp[i] != 0 && bp[i] + 1 == bp[i + 1]) {
                bpm[i] = bpm[bp[i] - 1];
            } else {
                bpm[i] = bp[i];
            }
        }
        return bpm;
    }
}
