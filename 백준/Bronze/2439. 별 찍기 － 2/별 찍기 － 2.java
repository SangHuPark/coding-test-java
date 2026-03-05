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
        int N = Integer.parseInt(br.readLine().trim());

        for (int row = 0; row < N; row++) {

            for (int col = N - row - 1; col > 0; col--) {
                sb.append(" ");
            }

            for (int col = 0; col <= row; col++) {
                sb.append("*");
            }

            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.close();
    }
}