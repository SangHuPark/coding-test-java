import java.io.*;
import java.util.*;

/**
 * BOJ_문제번호_문제명
 * @author
 *
 * 1.
 */
public class Main {
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int max = -1;
        int maxIdx = -1;

        for (int idx = 1; idx <= 9; idx++) {
            int num = Integer.parseInt(br.readLine().trim());

            if (num > max) {
                max = num;
                maxIdx = idx;
            }
        }

        sb.append(max).append("\n").append(maxIdx);

        bw.write(sb.toString());
        bw.close();
    }
}