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
        st = new StringTokenizer(br.readLine().trim());
        int A = Integer.parseInt(st.nextToken());

        for (int row = 0; row < A; row++) {
            for (int col = 0; col < row + 1; col++) {
                sb.append("*");
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.close();
    }
}