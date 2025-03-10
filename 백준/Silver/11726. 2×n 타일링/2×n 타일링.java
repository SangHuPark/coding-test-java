import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static final int MOD = 10_007;

    static int size;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        size = Integer.parseInt(br.readLine().trim());

        dp = new int[size+1];

        dp[1] = 1;
        if (size > 1) {
            dp[2] = 2;
        }

        if (size > 2) {
            for (int tile = 3; tile <= size; tile++) {
                dp[tile] = (dp[tile - 2] + dp[tile - 1]) % MOD;
            }
        }

        System.out.println(dp[size]);
    }

}