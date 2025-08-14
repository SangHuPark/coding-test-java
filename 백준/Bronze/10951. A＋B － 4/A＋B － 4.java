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
        String isEOF;

        while (Objects.nonNull(isEOF = br.readLine())) {
            st = new StringTokenizer(isEOF);

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            sb.append(A + B).append("\n");
        }

        bw.write(sb.toString());
        bw.close();
    }
}