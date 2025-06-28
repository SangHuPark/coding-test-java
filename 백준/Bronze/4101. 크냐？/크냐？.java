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

    static int A, B;

    public static void main(String[] args) throws IOException {
        while (true) {
            st = new StringTokenizer(br.readLine().trim());
            A = Integer.parseInt(st.nextToken());
            if (A == 0) break;
            B = Integer.parseInt(st.nextToken());

            if (A > B) sb.append("Yes").append("\n");
            else sb.append("No").append("\n");
        }
        System.out.println(sb);
    }
}