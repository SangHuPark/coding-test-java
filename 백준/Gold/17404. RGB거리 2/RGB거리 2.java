import java.io.*;
import java.util.*;

/**
 * BOJ_문제번호_문제명
 * @author tkdgn407
 *
 * 1. 기본 로직은 RGB 거리 1과 같음:
 *  - `dp[i][color] = i번째 집까지 칠했을 때 color로 끝나는 최소 비용`
 *  - 단, 연속된 집은 같은 색을 칠할 수 없음.
 * 2. 추가 제약:
 *  - 첫 번째 집의 색을 R/G/B 중 하나로 고정하고 시작
 *  - 마지막 집은 첫 집과 색이 달라야 함
 *  - → 이를 위해 총 3번 DP 수행, 매번 첫 번째 집의 색을 고정시킴
 * 3. 답은: 세 번의 경우 중 최솟값
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static final int MAX_VALUE = 1_000_001;

    static int N;
    static int[][] cost, dp;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine().trim());

        cost = new int[N][3];
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int col = 0; col < 3; col++) {
                cost[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = MAX_VALUE;
        for (int fixed = 0; fixed < 3; fixed++) {
            dp = new int[N][3];

            // dp 초기화
            for (int color = 0; color < 3; color++) {
                if (color == fixed)
                    dp[0][color] = cost[0][color];
                else
                    dp[0][color] = MAX_VALUE;
            }

            // dp 갱신
            for (int idx = 1; idx < N; idx++) {
                dp[idx][0] = Math.min(dp[idx - 1][1], dp[idx - 1][2]) + cost[idx][0];
                dp[idx][1] = Math.min(dp[idx - 1][0], dp[idx - 1][2]) + cost[idx][1];
                dp[idx][2] = Math.min(dp[idx - 1][0], dp[idx - 1][1]) + cost[idx][2];
            }

            // N번째 집은 1번째 집의 색상(fixed)과 달라야 한다.
            for (int lastColor = 0; lastColor < 3; lastColor++) {
                if (lastColor == fixed) continue;

                answer = Math.min(answer, dp[N - 1][lastColor]);
            }
        }

        System.out.println(answer);
    }
}