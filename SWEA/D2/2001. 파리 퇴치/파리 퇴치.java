import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            List<Integer> nm = Arrays.stream(br.readLine().split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            int N = nm.get(0);
            int M = nm.get(1);

            String[][] input = new String[N][N];
            for (int i = 0; i < N; i++) {
                input[i] = br.readLine().split(" ");
            }

            int max = 0;
            for (int i = 0; i < N - M + 1; i++) {
                for (int j = 0; j < N - M + 1; j++) {
                    int total = 0;
                    for (int n = 0; n < M; n++) {
                        for (int m = 0; m < M; m++) {
                            total += Integer.parseInt(input[n+i][m+j]);
                        }
                    }
                    max = Math.max(max, total);
                }
            }

            System.out.printf("#%d %d\n", t+1, max);

        }
    }
}