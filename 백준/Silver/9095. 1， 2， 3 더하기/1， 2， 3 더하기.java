import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static final int SIZE = 11;

    static int target;
    static int[] coinList;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        coinList = new int[] {0, 1, 2, 3};
        dp = new int[SIZE];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        for (int idx = 4; idx < SIZE; idx++) {
            dp[idx] = dp[idx - 1] + dp[idx - 2] + dp[idx - 3];
        }

        int testCase = Integer.parseInt(br.readLine().trim());

        for (int T = 0; T < testCase; T++) {
            target = Integer.parseInt(br.readLine().trim());

            sb.append(dp[target] + "\n");
        }

        System.out.println(sb);
    }

}