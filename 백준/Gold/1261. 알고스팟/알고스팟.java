import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Pos implements Comparable<Pos> {
        int row;
        int col;
        int wallCnt;

        Pos (int row, int col, int wallCnt) {
            this.row = row;
            this.col = col;
            this.wallCnt = wallCnt;
        }

        public int compareTo(Pos o1) {
            if (this.wallCnt < o1.wallCnt)
                return -1;
            else if (this.wallCnt > o1.wallCnt)
                return 1;

            return 0;
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static final int WALL = 1, INF = Integer.MAX_VALUE;

    static int colSize, rowSize;
    static int[][] map;
    static int[][] wallMap;

    static PriorityQueue<Pos> pq;

    static final int[] deltaRow = {0, 1, 0, -1};
    static final int[] deltaCol = {1, 0, -1, 0};

    public static void dijkstra(Pos start) {
        pq = new PriorityQueue<>();

        pq.add(start);
        wallMap[0][0] = 0;

        while (!pq.isEmpty()) {
            Pos curPos = pq.poll();

            if (curPos.row == rowSize-1 && curPos.col == colSize-1) {
                System.out.println(curPos.wallCnt);
                return;
            }

            for (int idx = 0; idx < 4; idx++) {
                int nextRow = curPos.row + deltaRow[idx];
                int nextCol = curPos.col + deltaCol[idx];

                if (isArrange(nextRow, nextCol))
                    continue;

                int nextWallCnt = curPos.wallCnt;

                // 벽이면 부순 횟수 증가
                if (map[nextRow][nextCol] == WALL) {
                   nextWallCnt++;
                }

                // 더 적은 벽을 부쉈을 때만 이동
                if (wallMap[nextRow][nextCol] > nextWallCnt) {
                    wallMap[nextRow][nextCol] = nextWallCnt;
                    pq.add(new Pos(nextRow, nextCol, nextWallCnt));
                }
            }
        }
    }

    public static boolean isArrange(int row, int col) {
        return row < 0 || row >= rowSize || col < 0 || col >= colSize;
    }

    public static void main(String[] args) throws IOException {
        init();

        dijkstra(new Pos(0, 0, 0));
    }

    public static void init() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        colSize = Integer.parseInt(st.nextToken());
        rowSize = Integer.parseInt(st.nextToken());

        map = new int[rowSize][colSize];
        wallMap = new int[rowSize][colSize];
        for (int row = 0; row < rowSize; row++) {
            String input = br.readLine().trim();
            for (int col = 0; col < colSize; col++) {
                map[row][col] = input.charAt(col) - '0';
                wallMap[row][col] = INF;
            }
        }

    }
}