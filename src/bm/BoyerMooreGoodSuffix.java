package bm;

/**
 * <a href="https://www.geeksforgeeks.org/boyer-moore-algorithm-good-suffix-heuristic/">Правило хорошего суффикса</a>
 */
public class BoyerMooreGoodSuffix {
    public static void main(String[] args) {

    }

    private static void preprocessStrongSuffix(int[] shift, int[] bpos,
                                         char[] pat, int patternLen) {
        // patternLen is the length of pattern
        int i = patternLen, j = patternLen + 1;
        bpos[i] = j;

        while (i > 0) {
        /*if character at position i-1 is not
        equivalent to character at j-1, then
        continue searching to right of the
        pattern for border */
            while (j <= patternLen && pat[i - 1] != pat[j - 1]) {
            /* the character preceding the occurrence of t
            in pattern P is different than the mismatching
            character in P, we stop skipping the occurrences
            and shift the pattern from i to j */
                if (shift[j] == 0)
                    shift[j] = j - i;

                //Update the position of next border
                j = bpos[j];
            }
        /* p[i-1] matched with p[j-1], border is found.
        store the beginning position of border */
            i--;
            j--;
            bpos[i] = j;
        }
    }

    //Preprocessing for case 2
    private static void preprocessCase2(int[] shift, int[] bpos,
                                 char[] pat, int m) {
        int i, j;
        j = bpos[0];
        for (i = 0; i <= m; i++) {
        /* set the border position of the first character
        of the pattern to all indices in array shift
        having shift[i] = 0 */
            if (shift[i] == 0)
                shift[i] = j;

        /* suffix becomes shorter than bpos[0],
        use the position of next widest border
        as value of j */
            if (i == j)
                j = bpos[j];
        }
    }

    /*Search for a pattern in given text using
    Boyer Moore algorithm with Good suffix rule */
    public static void search(char[] text, char[] pat) {
        // s is shift of the pattern
        // with respect to text
        int s = 0, j;
        int m = pat.length;
        int n = text.length;

        int[] bpos = new int[m + 1];
        int[] shift = new int[m + 1];

        //do preprocessing
        preprocessStrongSuffix(shift, bpos, pat, m);
        preprocessCase2(shift, bpos, pat, m);

        while (s <= n - m) {
            j = m - 1;

        /* Keep reducing index j of pattern while
        characters of pattern and text are matching
        at this shift s*/
            while (j >= 0 && pat[j] == text[s + j])
                j--;

        /* If the pattern is present at the current shift,
        then index j will become -1 after the above loop */
            if (j < 0) {
                System.out.printf("pattern occurs at shift = %d\n", s);
                s += shift[0];
            } else

            /*pat[i] != pat[s+j] so shift the pattern
            shift[j+1] times */
                s += shift[j + 1];
        }
    }

}
