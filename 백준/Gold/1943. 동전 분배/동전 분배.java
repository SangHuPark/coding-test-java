import java.io.*;
import java.util.*;

public class Main {
    static class Coin {
        int cost;
        int cnt;

        Coin(int cost, int cnt) {
            this.cost = cost;
            this.cnt = cnt;
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int coinCnt, targetCost;
    static List<Coin> coins;

    public static void main(String[] args) throws IOException {
        for (int T = 0; T < 3; T++) {
            init();

            // 짝수가 아니라면 동일한 분배 불가
            if (targetCost % 2 != 0) {
                sb.append(0).append("\n");
                continue;
            }

            // 절반으로 분리
            targetCost = targetCost >> 1;

            int size = coins.size();
            boolean[] dp = new boolean[100_001];
            dp[0] = true; // 0원은 만들 수 있으므로
            for (int idx = 0; idx < size; idx++) {
                Coin coin = coins.get(idx);
                // 각 동전은 개수가 정해져있으므로 현재 동전은 한 번만 쓰기 위해 역방향 진행
                for (int dpIdx = targetCost; dpIdx >= 0; dpIdx--) {
                    if (!dp[dpIdx])
                        continue;

                    for (int cnt = 1; cnt <= coin.cnt; cnt++) {
                        int cost = dpIdx + coin.cost * cnt;
                        dp[cost] = true;
                    }
                }
            }

            if (dp[targetCost])
                sb.append(1).append("\n");
            else
                sb.append(0).append("\n");
        }

        System.out.println(sb);
    }

    public static void init() throws IOException {
        coinCnt = Integer.parseInt(br.readLine().trim());

        coins = new ArrayList<>();
        targetCost = 0;
        for (int idx = 0; idx < coinCnt; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int cost = Integer.parseInt(st.nextToken());
            int cnt = Integer.parseInt(st.nextToken());

            targetCost += cost * cnt;

            coins.add(new Coin(cost, cnt));
        }
    }

}