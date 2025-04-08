import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int coinCount, goal;
    static int[] coins;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        int testCase = Integer.parseInt(br.readLine().trim());

        for (int T = 0; T < testCase; T++) {
            init();

            for (int idx = 1; idx <= coinCount; idx++) {
                for (int money = 0; money <= goal; money++) {
                    dp[idx][money] = dp[idx-1][money];

                    int useCurCoinIdx = money - coins[idx];
                    if (useCurCoinIdx >= 0)
                        dp[idx][money] += dp[idx][useCurCoinIdx];
                }
            }

            sb.append(dp[coinCount][goal] + "\n");
        }
        System.out.println(sb);

    }

    public static void init() throws IOException {
        coinCount = Integer.parseInt(br.readLine().trim());

        st = new StringTokenizer(br.readLine().trim());
        coins = new int[coinCount+1];
        for (int idx = 1; idx <= coinCount; idx++) {
            coins[idx] = Integer.parseInt(st.nextToken());
        }

        goal = Integer.parseInt(br.readLine().trim());

        dp = new int[coinCount+1][goal + 1];
        dp[0][0] = 1;
    }
}