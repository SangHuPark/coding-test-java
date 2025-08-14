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

        if (num >= 90)
            sb.append("A");
        else if (num >= 80)
            sb.append("B");
        else if (num >= 70)
            sb.append("C");
        else if (num >= 60)
            sb.append("D");
        else
            sb.append("F");

        bw.write(sb.toString());
        bw.close();
    }
}