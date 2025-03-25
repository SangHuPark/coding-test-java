import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int rowSize, colSize;
    static int[][] map, dp;

    public static void main(String[] args) throws IOException {
        init();

        for (int row = 2; row <= rowSize; row++) {
            int[] leftToRight = new int[colSize+1];
            // 아래에서 내려왔을 때와 왼쪽에서 오른쪽 왔을 시
            leftToRight[1] = dp[row-1][1] + map[row][1]; // 가장 왼쪽은 아래에서 내려오는 것 밖에 없으므로
            for (int col = 2; col <= colSize; col++) {
                leftToRight[col] = Math.max(leftToRight[col - 1], dp[row - 1][col]) + map[row][col];
            }

            int[] rightToLeft = new int[colSize + 1];
            // 아래에서 내려왔을 때와 오른쪽에서 왼쪽 왔을 시
            rightToLeft[colSize] = dp[row-1][colSize] + map[row][colSize]; // 가장 오른쪽은 내려오는 것 밖에 없으므로
            for (int col = colSize-1; col >= 1; col--) {
                rightToLeft[col] = Math.max(rightToLeft[col + 1], dp[row - 1][col]) + map[row][col];
            }

            // 두 경우의 최대값으로 갱신
            for (int col = 1; col <= colSize; col++) {
                dp[row][col] = Math.max(leftToRight[col], rightToLeft[col]);
            }
        }

//        for (int row = 1; row <= rowSize; row++) {
//            for (int col = 1; col <= colSize; col++) {
//                System.out.print(dp[row][col] + " ");
//            }
//            System.out.println();
//        }

        System.out.println(dp[rowSize][colSize]);
    }

    public static void init() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());

        map = new int[rowSize+1][colSize+1];
        for (int row = 1; row <= rowSize; row++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int col = 1; col <= colSize; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[rowSize + 1][colSize + 1];
        for (int col = 1; col <= colSize; col++) {
            dp[1][col] = dp[1][col - 1] + map[1][col];
        }
    }
}