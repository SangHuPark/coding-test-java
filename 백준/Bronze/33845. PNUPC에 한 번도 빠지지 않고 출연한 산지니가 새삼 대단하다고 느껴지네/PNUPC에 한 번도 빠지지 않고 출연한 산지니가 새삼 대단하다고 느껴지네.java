import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static boolean[] checked;

    public static void main(String[] args) throws IOException {
        checked = new boolean[26];

        String input = br.readLine().trim();
        for (int idx = 0; idx < input.length(); idx++) {
            checked[input.charAt(idx) - 'a'] = true;
        }

        input = br.readLine().trim();
        for (int idx = 0; idx < input.length(); idx++) {
            int tmp = input.charAt(idx) - 'a';
            if (checked[tmp])
                continue;

            System.out.print(input.charAt(idx));
        }
    }
}