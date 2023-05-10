package kr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KarpRabin {
    public static List<Integer> findSubstringsKR(int[] substring, int[] text, int q) {
        List<Integer> results = new ArrayList<>();
        int n = text.length;
        text = Arrays.copyOf(text, text.length + 1);
        //2^(m-1) mod q
        int p2m = 1;
        for (int i = 0; i < substring.length - 1; i++) {
            p2m = (p2m * 2) % q;
        }

        int substringHash = gorner2Mod(substring, substring.length, q);
        int textSampleHash = gorner2Mod(text, substring.length, q);
        for (int j = 0; j <= n - substring.length; j++) {
            if (substringHash == textSampleHash) {
                int k = 0;
                while (k < substring.length && substring[k] == text[j + k]) {
                    k++;
                }
                if (k == substring.length) {
                    results.add(j);
                }
            }
            textSampleHash = ((textSampleHash - p2m * text[j]) * 2 + text[j + substring.length]) % q;
            if (textSampleHash < 0) {
                textSampleHash += q;
            }
        }
        return results;
    }


    private static int gorner2Mod(int[] s, int m, int q) {
        int res = 0;
        for (int i = 0; i < m; i++) {
            res = (res * 2 + s[i]) % q;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] byteText = {0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0};
        int[] byteSubstring = {0, 0, 1, 1, 0, 0};
        findSubstringsKR(byteSubstring, byteText, 9973).forEach(System.out::println);
    }
}
