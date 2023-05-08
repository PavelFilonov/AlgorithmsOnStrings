package prefixZValues;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class PrefixZValues {

	public static void main(String[] args) {
		String example = "AABCAABXAAZ";
		System.out.printf("Исходный пример: %s%n", example);
		System.out.printf("Результат: %s%n%n", Arrays.toString(prefixZValues(example)));
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("Введите строку (exit - завершить): ");
			String s = scanner.nextLine();
			if ("exit".equalsIgnoreCase(s))
				break;
			System.out.printf("Результат: %s%n%n", Arrays.toString(prefixZValues(s)));
		}
	}

	private static int[] prefixZValues(String s) {
		int n = s.length();
		int l = 0;
		int r = 0;
		int[] zp = new int[n];
		zp[0] = 0;
		for (int i = 1; i < n; i++) {
			zp[i] = 0;
			if (i >= r) {
				zp[i] = strComp(s, n, 0, i);
				l = i;
				r = l + zp[i];
			} else {
				int j = i - l;
				if (zp[j] < r - i)
					zp[i] = zp[j];
				else {
					zp[i] = r - i + strComp(s, n, r - i, r);
					l = i;
					r = l + zp[i];
				}
			}
		}
		return zp;
	}

	private static int strComp(String s, int n, int i1, int i2) {
		int eqLen = 0;
		while (i1 < n && i2 < n && Objects.equals(s.split("")[i1++], s.split("")[i2++])) {
			eqLen++;
		}
		return eqLen;
	}

}