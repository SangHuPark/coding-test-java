import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node> {
        int row;
        int col;
        int wallCnt;

        Node (int row, int col, int wallCnt) {
            this.row = row;
            this.col = col;
            this.wallCnt = wallCnt;
        }

        public int compareTo(Node o1) {
            return Integer.compare(this.wallCnt, o1.wallCnt);
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static final int WALL = 0, INF = Integer.MAX_VALUE;

    static int mapSize;
    static int[][] map;

    static PriorityQueue<Node> pq;
    static int[][] wallMap;

    static final int[] deltaRow = {0, 1, 0, -1};
    static final int[] deltaCol = {1, 0, -1, 0};

    public static void dijkstra(Node start) {
        pq = new PriorityQueue<>();
        wallMap = new int[mapSize][mapSize];
        for (int row = 0; row < mapSize; row++) {
            Arrays.fill(wallMap[row], INF);
        }
        wallMap[0][0] = 0;

        pq.add(start);

        while (!pq.isEmpty()) {
            Node curNode = pq.poll();

            if (curNode.row == mapSize-1 && curNode.col == mapSize-1)
                break;

            if (wallMap[curNode.row][curNode.col] < curNode.wallCnt)
                continue;

            for (int idx = 0; idx < 4; idx++) {
                int nextRow = curNode.row + deltaRow[idx];
                int nextCol = curNode.col + deltaCol[idx];

                if (isArrange(nextRow, nextCol)) {
                    continue;
                }

                int newWallCnt = curNode.wallCnt;

                if (map[nextRow][nextCol] == WALL)
                    newWallCnt++;

                if (wallMap[nextRow][nextCol] > newWallCnt) {
                    wallMap[nextRow][nextCol] = newWallCnt;
                    pq.add(new Node(nextRow, nextCol, newWallCnt));
                }
            }
        }
    }

    public static boolean isArrange(int row, int col) {
        return row < 0 || row >= mapSize || col < 0 || col >= mapSize;
    }

    public static void main(String[] args) throws IOException {
        init();

        dijkstra(new Node(0, 0, 0));

        System.out.println(wallMap[mapSize-1][mapSize-1]);
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
    }
}