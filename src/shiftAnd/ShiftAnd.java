package shiftAnd;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static java.lang.StrictMath.log;

public class ShiftAnd {

    public static void main(String[] args) {
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out), true, StandardCharsets.UTF_8));

        Scanner in = new Scanner(System.in);
        System.out.print("Введите текст: ");
        String T = in.nextLine();

        System.out.print("Введите шаблон: ");
        String P = in.nextLine();

        System.out.print("\n-----------------------------------\n");

        System.out.printf("Введенный текст: %s \n", T);
        System.out.printf("Введенный шаблон: %s \n", P);

        in.close();

        shiftAnd(T, P);
    }

    private static Map<Character, Integer> getBitMap(String pattern, int patternLen) {
        Map<Character, Integer> bitMap = new HashMap<>(patternLen);
        for (int j = 0; j < patternLen; j++) {
            int finalJ = j;
            bitMap.compute(pattern.charAt(j), (character, bitmap) -> Optional.ofNullable(bitmap).orElse(0) | 1 << patternLen - finalJ - 1);
        }
        return bitMap;
    }

    public static void shiftAnd(String text, String pattern) {
        int textLen = text.length(), patternLen = pattern.length();
        Map<Character, Integer> bitMap = getBitMap(pattern, patternLen);
        int uHigh = 1 << (patternLen - 1);

        int M = 0;
        for (int i = 0; i < textLen; i++) {
            M = ((M >> 1 | uHigh) & bitMap.getOrDefault(text.charAt(i), 0));
            if (M == 1) {
                System.out.println("Совпадение найдено в позиции " + (i - patternLen + 1));
            }
        }
    }
}
