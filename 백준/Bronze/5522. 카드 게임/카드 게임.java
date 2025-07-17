import java.io.*;
import java.util.*;

/**
 * BOJ_문제번호_문제명
 * @author tkdgn407
 *
 * 1.
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int sum = 0;
        for (int idx = 0; idx < 5; idx++) {
            sum += Integer.parseInt(br.readLine().trim());
        }
        System.out.println(sum);
    }
}