package suffixBorderArray;

import java.util.Arrays;
import java.util.Scanner;

public class SuffixBorderArray {

    public static void main(String[] args) {
        String example = "ABAAABAСBСAABAAAB";
        System.out.printf("Исходный пример: %s%n", example);
        System.out.printf("Результат: %s%n%n", Arrays.toString(suffixBorderArray(example)));
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Введите строку (exit - завершить): ");
            String s = scanner.nextLine();
            if ("exit".equalsIgnoreCase(s))
                break;
            System.out.printf("Результат: %s%n%n", Arrays.toString(suffixBorderArray(s)));
        }
    }

    private static int[] suffixBorderArray(String s) {
        int n = s.length();
        int[] bs = new int[n];
        bs[n - 1] = 0;
        for (int i = n - 2; i >= 0; i--) {
            int bsLeft = bs[i + 1];
            while (bsLeft > 0 && s.charAt(i) != s.charAt(n - bsLeft - 1)) {
                bsLeft = bs[n - bsLeft];
            }
            if (s.charAt(i) == s.charAt(n - bsLeft - 1)) {
                bs[i] = bsLeft + 1;
            } else {
                bs[i] = 0;
            }
        }
        return bs;
    }
}
