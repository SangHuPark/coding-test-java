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
        char[] nums = br.readLine().trim().toCharArray();

        int answer = 0;
        for (int idx = 0; idx < N; idx++) {
            answer += nums[idx] - '0';
        }
        System.out.println(answer);
    }
}