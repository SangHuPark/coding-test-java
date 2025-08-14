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
        int validNum = 0;

        for (int idx = 0; idx < 5; idx++) {
            int num = Integer.parseInt(st.nextToken());
            validNum += num*num;
        }
        validNum %= 10;

        sb.append(validNum);
        bw.write(sb.toString());
        bw.close();
    }
}