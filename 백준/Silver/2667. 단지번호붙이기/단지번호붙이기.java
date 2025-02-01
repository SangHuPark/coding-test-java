import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int mapSize;
    static int[][] map;
    static PriorityQueue<Integer> homeQ;
    static int maxDepth;

    static final int[] deltaRow = {0, 1, 0, -1};
    static final int[] deltaCol = {1, 0, -1, 0};

    public static void dfs(int depth, int row, int col) {
        map[row][col] = 0;

        for (int idx = 0; idx < 4; idx++) {
            int nextRow = row + deltaRow[idx];
            int nextCol = col + deltaCol[idx];

            if (isArrange(nextRow, nextCol) || map[nextRow][nextCol] == 0)
                continue;

            maxDepth++;
            dfs(depth + 1, nextRow, nextCol);
        }
    }

    public static boolean isArrange(int row, int col) {
        return row < 0 || col < 0 || row >= mapSize || col >= mapSize;
    }

    public static int findHome() {
        int totalHomeCnt = 0;
        for (int row = 0; row < mapSize; row++) {
            for (int col = 0; col < mapSize; col++) {
                if (map[row][col] == 1) {
                    maxDepth = 1;
                    dfs(maxDepth, row, col);
                    totalHomeCnt++;
                    homeQ.add(maxDepth);
                }
            }
        }

        return totalHomeCnt;
    }

    public static void main(String[] args) throws IOException {
        init();

        int totalHomeCnt = findHome();

        sb.append(totalHomeCnt).append("\n");
        while (!homeQ.isEmpty()) {
            int curHome = homeQ.poll();
            sb.append(curHome).append("\n");
        }
        System.out.println(sb);
    }

    public static void init() throws IOException {
        mapSize = Integer.parseInt(br.readLine().trim());

        map = new int[mapSize][mapSize];
        for (int row = 0; row < mapSize; row++) {
            String input = br.readLine().trim();
            for (int col = 0; col < mapSize; col++) {
                map[row][col] = input.charAt(col) - '0';
            }
        }

        homeQ = new PriorityQueue<>();
    }

}