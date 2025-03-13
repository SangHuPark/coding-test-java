import javax.swing.text.html.parser.Entity;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static char[] A, B;
    static int[][] dp;
    static StringBuilder lcs;

    public static void main(String[] args) throws IOException {
        A = br.readLine().trim().toCharArray();
        B = br.readLine().trim().toCharArray();

        dp = new int[A.length + 1][B.length + 1];
        lcs = new StringBuilder();
        for (int aIdx = 0; aIdx < A.length; aIdx++) {
            for (int bIdx = 0; bIdx < B.length; bIdx++) {
                if (A[aIdx] == B[bIdx]) {
                    dp[aIdx + 1][bIdx + 1] = dp[aIdx][bIdx] + 1;
                } else {
                    dp[aIdx + 1][bIdx + 1] = Math.max(dp[aIdx][bIdx + 1], dp[aIdx + 1][bIdx]);
                }
            }
        }

        int aIdx = A.length;
        int bIdx = B.length;
        while (aIdx != 0 && bIdx != 0) {
            if (A[aIdx-1] == B[bIdx-1]) {
                lcs.append(A[aIdx-1]);
                aIdx--;
                bIdx--;
            } else if (dp[aIdx-1][bIdx] > dp[aIdx][bIdx-1]) {
                aIdx--;
            }  else {
                bIdx--;
            }
        }

        System.out.println(dp[A.length][B.length]);
        System.out.println(lcs.reverse());
    }


}