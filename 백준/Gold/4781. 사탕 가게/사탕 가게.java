import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static class Candy {
        int weight;
        int cost;

        Candy(int weight, int cost) {
            this.weight = weight;
            this.cost = cost;
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M;
    static Candy[] candies;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        while (true) {
            if(!init())
                break;

            int current = 0, before = 0;
            for (int idx = 0; idx < N; idx++) {
                int weight = candies[idx].weight;
                int cost = candies[idx].cost;

                current = idx % 2;
                before = (idx + 1) % 2;

                for (int dpIdx = cost; dpIdx <= M; dpIdx++) {
                    if (dpIdx < cost)
                        dp[current][dpIdx] = dp[before][dpIdx];
                    else
                        dp[current][dpIdx] = Math.max(dp[current][dpIdx - cost] + weight, Math.max(dp[before][dpIdx], dp[before][dpIdx - cost] + weight));

                }
            }

            System.out.println(dp[current][M]);
        }
    }

    public static boolean init() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        if (N == 0)
            return false;

        M = (int) (Double.parseDouble(st.nextToken()) * 100 + 0.5);

        candies = new Candy[N];
        for (int idx = 0; idx < N; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int weight = Integer.parseInt(st.nextToken());
            int cost = (int) (Double.parseDouble(st.nextToken()) * 100 + 0.5);

            candies[idx] = new Candy(weight, cost);
        }
        Arrays.sort(candies, (o1, o2) -> { return Integer.compare(o1.cost, o2.cost); });

        dp = new int[2][M + 1];

        return true;
    }
}