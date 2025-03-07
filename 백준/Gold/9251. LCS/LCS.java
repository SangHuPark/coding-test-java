import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static char[] inputA, inputB;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        init();

        for (int aIdx = 0; aIdx < inputA.length; aIdx++) {

            for (int bIdx = 0; bIdx < inputB.length; bIdx++) {
                if (inputA[aIdx] == inputB[bIdx])
                    dp[aIdx+1][bIdx+1] += dp[aIdx][bIdx] + 1;
                else
                    dp[aIdx + 1][bIdx + 1] = Math.max(dp[aIdx][bIdx + 1], dp[aIdx + 1][bIdx]);
            }
        }

        System.out.println(dp[inputA.length][inputB.length]);
    }

    public static void init() throws IOException {
        inputA = br.readLine().trim().toCharArray();
        inputB = br.readLine().trim().toCharArray();

        dp = new int[inputA.length+1][inputB.length+1];
    }

}