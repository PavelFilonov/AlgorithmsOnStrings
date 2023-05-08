package prefixBorderArray;

import java.util.Arrays;
import java.util.Scanner;

public class PrefixBorderArray {

	public static void main(String[] args) {
		String example = "ABAABABAABAAB";
		System.out.printf("Исходный пример: %s%n", example);
		System.out.printf("Результат: %s%n%n", Arrays.toString(prefixBorderArray(example)));
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("Введите строку (exit - завершить): ");
			String s = scanner.nextLine();
			if ("exit".equalsIgnoreCase(s))
				break;
			System.out.printf("Результат: %s%n%n", Arrays.toString(prefixBorderArray(s)));
		}
	}

	public static int[] prefixBorderArray(String S) {
		int n = S.length();
		int[] bp = new int[n];
		bp[0] = 0;
		for (int i = 1; i < n; ++i) {
			int bpRight = bp[i - 1];
			while (bpRight > 0 && (S.charAt(i) != S.charAt(bpRight))) {
				bpRight = bp[bpRight - 1];
			}
			if (S.charAt(i) == S.charAt(bpRight)) {
				bp[i] = bpRight + 1;
			} else {
				bp[i] = 0;
			}
		}
		return bp;
	}

}
