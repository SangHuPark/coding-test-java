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
        int num = Integer.parseInt(br.readLine().trim());

        for (int idx = 1; idx <= 9; idx++) {
            sb.append(num).append(" * ").append(idx).append(" = ").append(num*idx).append("\n");
        }

        bw.write(sb.toString());
        bw.close();
    }
}