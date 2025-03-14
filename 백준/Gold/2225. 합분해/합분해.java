import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static final int MOD = 1_000_000_000;

    static int N, K;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new int[N + 1][K + 1];

        // 초기값 세팅
        // 1. N을 0개, 1개로 만들 경우의 수는 고정
        for (int idx = 0; idx <= N; idx++) {
            dp[idx][0] = 0;
            dp[idx][1] = 1;
        }
        // 2. N이 1일 경우, 각 K개 만큼 만들 수 있음
        //  - ex) N=1, K=3: (0+0+1) (0+1+0) (1+0+0)
        for (int idx = 0; idx <= K; idx++) {
            dp[1][idx] = idx;
        }

        // 입력값이 몇이던 K=1 or N=1 의 경우의 수는 고정이므로 2부터
        for (int row = 2; row <= N; row++) {
            for (int col = 2; col <= K; col++) {
                dp[row][col] = (dp[row - 1][col] + dp[row][col - 1]) % MOD;
            }
        }

        System.out.println(dp[N][K]);
    }


}