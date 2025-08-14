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

        if ((num % 4 == 0 && num % 100 != 0) || num % 400 == 0)
            sb.append(1);
        else
            sb.append(0);

        bw.write(sb.toString());
        bw.close();
    }
}