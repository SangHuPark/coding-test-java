import java.io.*;
import java.util.*;

/**
 * 1. dp[i][j]는 j원을 만들 때, 1부터 i까지의 수(=코인)만 사용하여 만들 수 있는 경우의 수를 의미한다.
 * 2. dp[0][0] = 1로 초기화하고, 나머지는 0으로 둔다.
 * 3. 바깥 반복은 coin(=1, 2, 3), 안쪽 반복은 amount(=1 ~ n)이다.
 * 4. 점화식은 다음과 같다:
 *    dp[coin][amount] = dp[coin-1][amount]      (이번 coin을 사용하지 않는 경우)
 *                     + dp[coin][amount - coin] (이번 coin을 사용하는 경우)
 * 5. 최종 정답은 dp[3][n] (1, 2, 3으로 n을 만드는 모든 조합의 수)
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int amount;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        int testCase = Integer.parseInt(br.readLine().trim());

        for (int T = 0; T < testCase; T++) {
            amount = Integer.parseInt(br.readLine().trim());
            dp = new int[4][amount + 1];
            dp[0][0] = 1;

            for (int coin = 1; coin < 4; coin++) {
                for (int dpIdx = 0; dpIdx <= amount; dpIdx++) {
                    dp[coin][dpIdx] = dp[coin-1][dpIdx];
                    if (dpIdx >= coin)
                        dp[coin][dpIdx] += dp[coin][dpIdx - coin];
                }
            }

            sb.append(dp[3][amount]).append("\n");
        }

        System.out.println(sb);
    }

}