import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int rowSize, colSize;
    static int[][] map;

    static int[][] dp;

    static final int[] deltaRow = {0, 1, 0, -1};
    static final int[] deltaCol = {1, 0, -1, 0};

    public static int dfs(int row, int col) {
        if (row == rowSize-1 && col == colSize-1) {
            return 1;
        }

        if (dp[row][col] != -1)
            return dp[row][col];

        dp[row][col] = 0;
        for (int idx = 0; idx < 4; idx++) {
            int nextRow = row + deltaRow[idx];
            int nextCol = col + deltaCol[idx];

            if (isArrange(nextRow, nextCol) || map[row][col] <= map[nextRow][nextCol])
                continue;

            dp[row][col] += dfs(nextRow, nextCol);
        }

        return dp[row][col];
    }

    public static boolean isArrange(int row, int col) {
        return row < 0 || col < 0 || row >= rowSize || col >= colSize;
    }

    public static void main(String[] args) throws IOException {
        init();

        dfs(0, 0);

        System.out.println(dp[0][0]);
    }

    public static void init() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());

        map = new int[rowSize][colSize];
        for (int row = 0; row < rowSize; row++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int col = 0; col < colSize; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[rowSize][colSize];
        for (int row = 0; row < rowSize; row++) {
            Arrays.fill(dp[row], -1);
        }
    }
}