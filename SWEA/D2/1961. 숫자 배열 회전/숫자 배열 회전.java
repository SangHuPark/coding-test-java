import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());

            String[][] input = new String[N][N];
            for (int i = 0; i < N; i++) {
                input[i] = br.readLine().split(" ");
            }

            String[][] answer = new String[N][3];
            for (int i = 0; i < N; i++) {
                StringBuilder turn90 = new StringBuilder();
                for (int j = N - 1; j >= 0; j--) {
                    turn90.append(input[j][i]);
                }
                answer[i][0] = turn90.toString();
            }

            int idx = 0;
            for (int i = N - 1; i >= 0; i--) {
                StringBuilder turn180 = new StringBuilder();
                for (int j = N - 1; j >= 0; j--) {
                    turn180.append(input[i][j]);
                }
                answer[idx++][1] = turn180.toString();
            }

            idx = 0;
            for (int i = N - 1; i >= 0; i--) {
                StringBuilder turn270 = new StringBuilder();
                for (int j =0; j < N; j++) {
                    turn270.append(input[j][i]);
                }
                answer[idx++][2] = turn270.toString();
            }

            System.out.printf("#%d\n", t+1);
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < 3; j++) {
                    System.out.printf("%s ", answer[i][j]);
                }
                System.out.print("\n");
            }

        }
    }
}