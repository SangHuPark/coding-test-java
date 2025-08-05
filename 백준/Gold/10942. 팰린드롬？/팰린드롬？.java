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

    static int N, M;
    static int[] origin;
    static boolean[][] dp;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine().trim());

        st = new StringTokenizer(br.readLine().trim());
        origin = new int[N+1];
        for (int idx = 1; idx <= N; idx++) {
            origin[idx] = Integer.parseInt(st.nextToken());
        }

        dp = new boolean[N+1][N+1];
        
        // 길이가 1일 때는 자기 자신이므로 항상 참
        for (int idx = 1; idx <= N; idx++) {
            dp[idx][idx] = true;
        }

        // 길이가 2일 때는 다음과 같을 때만 참
        for (int idx = 1; idx < N; idx++) {
            if (origin[idx] == origin[idx+1])
                dp[idx][idx+1] = true;
        }

        // 3 이상은 양 끝이 같고 dp에서 구간이 참이면 참
        for (int len = 3; len <= N; len++) {
            for (int s = 1; s <= N - len + 1; s++) {
                int e = s + len - 1;
                if (origin[s] == origin[e] && dp[s + 1][e - 1]) {
                    dp[s][e] = true;
                }
            }
        }

        M = Integer.parseInt(br.readLine().trim());
        for (int idx = 0; idx < M; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            if (dp[S][E])
                sb.append(1).append("\n");
            else
                sb.append(0).append("\n");
        }
        System.out.println(sb);
    }
}