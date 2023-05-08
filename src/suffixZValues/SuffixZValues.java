package suffixZValues;

import java.util.Arrays;
import java.util.Scanner;

public class SuffixZValues {

    public static void main(String[] args) {
        String example = "abaababa";
        System.out.printf("Исходный пример: %s%n", example);
        System.out.printf("Результат: %s%n%n", Arrays.toString(suffixZValues(example)));
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Введите строку (exit - завершить): ");
            String s = scanner.nextLine();
            if ("exit".equalsIgnoreCase(s))
                break;
            System.out.printf("Результат: %s%n%n", Arrays.toString(suffixZValues(s)));
        }
    }

    private static int[] suffixZValues(String s) {
        int n = s.length();
        int l = n - 1;
        int r = n - 1;
        int[] zs = new int[n];
        zs[n - 1] = 0;
        for (int i = n - 2; i >= 0; i--) {
            zs[i] = 0;
            if (i <= l) {
                zs[i] = strCompBack(s, i, n - 1);
                r = i;
                l = r - zs[i];
            } else {
                int j = n - (r + 1 - i);
                if (zs[j] < i - l) {
                    zs[i] = zs[j];
                } else {
                    zs[i] = i - l + strCompBack(s, l, n - i + l);
                    r = i;
                    l = r - zs[i];
                }
            }
        }
        return zs;
    }

    private static int strCompBack(String s, int i1, int i2) {
        int eqLen = 0;
        while (i1 >= 0 && i2 >= 0 && s.charAt(i1--) == s.charAt(i2--)) {
            eqLen++;
        }
        return eqLen;
    }
}
