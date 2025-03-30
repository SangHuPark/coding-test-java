import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static String input;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        input = br.readLine().trim();

        arr = new int[26];
        Arrays.fill(arr, -1);
        for (int idx = 0; idx < input.length(); idx++) {
            int chIdx = input.charAt(idx) - 'a';
            if (arr[chIdx] != -1)
                continue;

            arr[chIdx] = idx;
        }

        for (int idx = 0; idx < 26; idx++) {
            System.out.print(arr[idx] + " ");
        }
    }

    public static void init() throws IOException {

    }
}