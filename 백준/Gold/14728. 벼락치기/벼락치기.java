import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static class Class {
        int time;
        int cost;

        Class(int time, int cost) {
            this.time = time;
            this.cost = cost;
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M;
    static Class[] classes;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        init();

        for (int idx = 1; idx <= N; idx++) {
            Class subject = classes[idx-1];
            int time = subject.time;
            int cost = subject.cost;

            for (int dpIdx = 0; dpIdx <= M; dpIdx++) {
                if (time > dpIdx)
                    dp[idx][dpIdx] = dp[idx - 1][dpIdx];
                else
                    dp[idx][dpIdx] = Math.max(dp[idx - 1][dpIdx], dp[idx - 1][dpIdx - time] + cost);
            }
        }

        System.out.println(dp[N][M]);
    }

    public static void init() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        classes = new Class[N];
        for (int idx = 0; idx < N; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int time = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            classes[idx] = new Class(time, cost);
        }
        Arrays.sort(classes, (o1, o2) -> { return Integer.compare(o1.time, o2.time); });

        dp = new int[N + 1][M + 1];
    }
}