package suffixArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShiftSort {
    public static List<Integer> generateAndSortSuffixArray(String s) {
        int n = s.length();
        final int alphabet = 256;

        List<Integer> suffixArray = new ArrayList<>(n);
        int[] c = new int[n];
        int[] count = new int[alphabet];

        for (int i = 0; i < n; i++)
            count[s.charAt(i)]++;

        for (int i = 1; i < alphabet; i++)
            count[i] += count[i - 1];

        for (int i = 0; i < n; i++)
            suffixArray.add(0);
        for (int i = 0; i < n; i++)
            suffixArray.set(--count[s.charAt(i)], i);

        c[suffixArray.get(0)] = 0;
        int rank = 1;
        for (int i = 1; i < n; i++) {
            if (s.charAt(suffixArray.get(i)) != s.charAt(suffixArray.get(i - 1)))
                rank++;

            c[suffixArray.get(i)] = rank - 1;
        }

        List<Integer> pn = new ArrayList<>(n);
        int[] cn = new int[n];
        for (int k = 0; (1 << k) < n; k++) {
            for (int i = 0; i < n; i++) {
                pn.add(0);
                pn.set(i, suffixArray.get(i) - (1 << k));
                if (pn.get(i) < 0)
                    pn.set(i, pn.get(i) + n);
            }

            Arrays.fill(count, 0, rank, 0);

            for (int i = 0; i < n; i++)
                count[c[pn.get(i)]]++;
            for (int i = 1; i < rank; i++)
                count[i] += count[i - 1];
            for (int i = n - 1; i >= 0; i--)
                suffixArray.set(--count[c[pn.get(i)]], pn.get(i));
            cn[suffixArray.get(0)] = 0;

            rank = 1;
            for (int i = 1; i < n; i++) {
                Pair<Integer, Integer> cur = new Pair<>(c[suffixArray.get(i)], c[(suffixArray.get(i) + (1 << k)) % n]);
                Pair<Integer, Integer> prev = new Pair<>(c[suffixArray.get(i - 1)], c[(suffixArray.get(i - 1) + (1 << k)) % n]);
                if (!cur.equals(prev))
                    ++rank;
                cn[suffixArray.get(i)] = rank - 1;
            }

            int[] temp = c;
            c = cn;
            cn = temp;
        }

        return suffixArray;
    }

    public static List<Integer> buildSuffixArray(String s) {
        s += "$";
        List<Integer> suffixArray = generateAndSortSuffixArray(s);
        suffixArray.remove(0);
        return suffixArray;
    }

    public static void main(String[] args) {
        String text = "abacaba";
        List<Integer> suffixArray = buildSuffixArray(text);
        System.out.println(suffixArray);
    }
}