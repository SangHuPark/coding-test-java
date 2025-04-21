import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int max = 0;
        int transLower = 'A' - 'a';
        Map<Character, Integer> map = new HashMap<>();

        String input = br.readLine().trim();
        for (int idx = 0; idx < input.length(); idx++) {
            char key = input.charAt(idx);
            if (key >= 'a')
                key += transLower;

            int value = map.getOrDefault(key, 0) + 1;
            max = Math.max(max, value);
            map.put(key, value);
        }

        boolean flag = false;
        char answer = '?';
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() == max) {
                if (flag) {
                    System.out.print("?");
                    return;
                }

                answer = entry.getKey();
                flag = true;
            }
        }

        System.out.println(answer);
    }

}