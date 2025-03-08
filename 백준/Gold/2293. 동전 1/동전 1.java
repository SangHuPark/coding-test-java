import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int coinCnt, target;
    static int[] coinList;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        init();

        // 초기값 설정: 0원을 만드는 방법은 항상 1가지 (아무 동전도 사용하지 않는 경우)
        for (int i = 0; i < coinCnt; i++) {
            dp[i][0] = 1;
        }

        // 첫 번째 동전만 사용할 경우 초기화
        for (int j = 0; j <= target; j++) {
            if (j % coinList[0] == 0) {
                dp[0][j] = 1;
            }
        }

        // DP 테이블 채우기
        for (int i = 1; i < coinCnt; i++) {
            for (int j = 1; j <= target; j++) {
                // 이전 동전까지 고려한 경우의 수 유지
                dp[i][j] = dp[i - 1][j];

                // 현재 동전을 사용할 경우 추가
                if (j >= coinList[i]) {
                    dp[i][j] += dp[i][j - coinList[i]];
                }
            }
        }

        System.out.println(dp[coinCnt-1][target]);
    }

    public static void init() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        coinCnt = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());

        coinList = new int[coinCnt];
        for (int idx = 0; idx < coinCnt; idx++) {
            coinList[idx] = Integer.parseInt(br.readLine().trim());
        }

        dp = new int[coinCnt][target + 1];
    }

}