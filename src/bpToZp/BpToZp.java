package bpToZp;

public class BpToZp {

    private static int[] bpToZp(int[] bp, int n) {
        int l = 0;
        int r = 0;
        int[] zp = new int[n];
        zp[0] = 0;
        for (int i = 1; i < n; i++) {
            zp[i] = 0;
            if (i >= r) {
                zp[i] = valGrow(bp, n, i, 1);
                l = i;
                r = l + zp[i];
            } else {
                int j = i - l;
                if (zp[j] < r - i) {
                    zp[i] = zp[j];
                } else {
                    zp[i] = r - i + valGrow(bp, n, r, r - i + 1);
                    l = i;
                    r = l + zp[i];
                }
            }
        }
        return zp;
    }

    private static int valGrow(int[] nArr, int n, int nPos, int nVal) {
        int nSeqLen = 0;
        while (nPos < n && nArr[nPos++] == nVal++) {
            ++nSeqLen;
        }
        return nSeqLen;
    }
}
