import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/**
 * 1. 동굴의 크기 N
 *  1-1. while 문을 통해 N 의 입력으로 0 이 나오면 종료
 * 2. 금액을 저장하는 rupeeMap
 * 3. [0, 0] 부터 4방으로 최소 값을 따라 이동하며 누적합을 갱신하는 dp 테이블
 *  3-1. 최소값 갱신을 위해 dp 테이블 MAX_VALUE 로 초기화
 */
public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    public static int N;
    public static int[][] rupeeMap;
    public static int[][] dp;

    public static int[] deltaRow = {0, 1, 0, -1};
    public static int[] deltaCol = {1, 0, -1, 0};

    public static void rupeeBFS(int startRow, int startCol) {
        ArrayDeque<int[]> bfsQueue = new ArrayDeque<>();

        bfsQueue.add(new int[] {startRow, startCol});

        dp[startRow][startCol] = rupeeMap[startRow][startCol];

        while(!bfsQueue.isEmpty()) {
            int[] curPos = bfsQueue.poll();
            int curRow = curPos[0];
            int curCol = curPos[1];

            for (int deltaIdx = 0; deltaIdx < 4; deltaIdx++) {
                int nextRow = curRow + deltaRow[deltaIdx];
                int nextCol = curCol + deltaCol[deltaIdx];

                if (isArrange(nextRow, nextCol)) {
                    continue;
                }

                if(dp[curRow][curCol] + rupeeMap[nextRow][nextCol] < dp[nextRow][nextCol]) {
                    dp[nextRow][nextCol] = dp[curRow][curCol] + rupeeMap[nextRow][nextCol];
                    bfsQueue.add(new int[] {nextRow, nextCol});
                }
            }

        }
    }

    public static boolean isArrange(int row, int col) {
        return row < 0 || col < 0 || row >= N || col >= N;
    }

    public static void main(String[] args) throws IOException {
        int problemNum = 1;
        while(true) {
            N = Integer.parseInt(br.readLine().trim());
            if(N == 0) {
                break;
            }

            rupeeMap = new int[N][N];

            for (int row = 0; row < N; row++) {
                st = new StringTokenizer(br.readLine().trim());
                for (int col = 0; col < N; col++) {
                    rupeeMap[row][col] = Integer.parseInt(st.nextToken());
                }
            }

            dp = new int[N][N];
            for (int row = 0; row < N; row++) {
                for (int col = 0; col < N; col++) {
                    dp[row][col] = Integer.MAX_VALUE;
                }
            }

            rupeeBFS(0, 0);

            sb.append("Problem ").append(problemNum++).append(": ").append(dp[N-1][N-1]).append("\n");
        }

        System.out.println(sb);
    }
}