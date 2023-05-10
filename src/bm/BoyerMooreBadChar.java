package bm;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class BoyerMooreBadChar {
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

        find(T, P);
    }

    static Map<Character, List<Integer>> badCharPreprocessing(String str) {
        Map<Character, List<Integer>> shiftTable = new HashMap<>(str.length());
        for (int i = str.length() - 1; i >= 0; i--) {
            List<Integer> positions = shiftTable.getOrDefault(str.charAt(i), new ArrayList<>());
            positions.add(i);
            shiftTable.put(str.charAt(i), positions);
        }
        return shiftTable;
    }

    static int getShift(Map<Character, List<Integer>> table, char bc, int posBadChar) {
        return table.getOrDefault(bc, Collections.<Integer>emptyList()).stream()
                .filter(pos -> pos < posBadChar)
                .findFirst()
                .orElse(-1);
    }

    static void find(String T, String P) {
        Map<Character, List<Integer>> table = badCharPreprocessing(P);
        int i = 0;
        int textLen = T.length(), patternLen = P.length();
        while (i <= textLen - patternLen) {
            int j = patternLen - 1;
            while (j >= 0 && P.charAt(j) == T.charAt(i + j))
                j--;
            if (j == -1) {
                System.out.println("Совпадение найдено в позиции " + (i + 1));
                i++;
            } else {
                int shift = getShift(table, T.charAt(i + j), j);
                i += Math.max(1, j - shift);
            }
        }
    }
}
