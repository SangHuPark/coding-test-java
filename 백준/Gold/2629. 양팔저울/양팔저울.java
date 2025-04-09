import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int chuCount, goalCount;
    static int[] chus, goals;
    static boolean[][] dp;

    public static void main(String[] args) throws IOException {
        init();

        for (int idx = 1; idx <= chuCount; idx++) {
            int chu = chus[idx-1];
            for (int weight = 0; weight < 15001; weight++) {
                if (!dp[idx-1][weight])
                    continue;

                dp[idx][weight] = true;
                dp[idx][chu + weight] = true;
                dp[idx][Math.abs(weight - chu)] = true;
            }
        }

        for (int goal : goals) {
            if (goal < 15001 && dp[chuCount][goal])
                sb.append("Y ");
            else
                sb.append("N ");
        }
        System.out.println(sb);
    }

    public static void init() throws IOException {
        chuCount = Integer.parseInt(br.readLine().trim());

        st = new StringTokenizer(br.readLine().trim());
        chus = new int[chuCount];
        for (int idx = 0; idx < chuCount; idx++) {
            chus[idx] = Integer.parseInt(st.nextToken());
        }

        goalCount = Integer.parseInt(br.readLine().trim());

        st = new StringTokenizer(br.readLine().trim());
        goals = new int[goalCount];
        for (int idx = 0; idx < goalCount; idx++) {
            goals[idx] = Integer.parseInt(st.nextToken());
        }

        dp = new boolean[chuCount + 1][15001];
        dp[0][0] = true;
    }
}