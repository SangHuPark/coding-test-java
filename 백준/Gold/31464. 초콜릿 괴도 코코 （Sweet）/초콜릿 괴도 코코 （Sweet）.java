import java.io.*;
import java.util.*;

public class Main {
    static class Pos implements Comparable<Pos> {
        int row;
        int col;

        Pos (int row, int col) {
            this.row = row;
            this.col = col;
        }

        public int compareTo(Pos o1) {
            if (this.row == o1.row)
                return Integer.compare(this.col, o1.col);

            return Integer.compare(this.row, o1.row);
        }

        @Override
        public String toString() {
            return row + " " + col + "\n";
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static boolean CHOCOLATE = true, EMPTY = false;

    static int mapSize;
    static boolean[][] map;
    static int chocolateCount;

    static Deque<Pos> q;
    static boolean[][] checked;

    static final int[] deltaRow = {0, 1, 0, -1};
    static final int[] deltaCol = {1, 0, -1, 0};

    public static boolean BFS() {
        q = new ArrayDeque<>();
        checked = new boolean[mapSize + 1][mapSize + 1];

        // 시작 위치 찾기
        Pos start = findStartPos();
        if (Objects.isNull(start))
            return false;

        q.add(start);
        checked[start.row][start.col] = CHOCOLATE;

        int nodeCount = 1;
        int edgeCount = 0;
        while (!q.isEmpty()) {
            Pos pos = q.poll();

            for (int idx = 0; idx < 4; idx++) {
                int nextRow = pos.row + deltaRow[idx];
                int nextCol = pos.col + deltaCol[idx];

                if (isArrange(nextRow, nextCol) || !map[nextRow][nextCol])
                    continue;

                edgeCount++;

                if (checked[nextRow][nextCol]) {
                    continue;
                }

                q.add(new Pos(nextRow, nextCol));
                checked[nextRow][nextCol] = CHOCOLATE;
                nodeCount++;
            }
        }

        // 하나의 조각이 아니라면 false
        if(chocolateCount != nodeCount)
            return false;

        // checked 기준 싸이클이 있는지 확인
        return edgeCount == (nodeCount - 1) << 1;
    }

    public static Pos findStartPos() {
        for (int row = 1; row <= mapSize; row++) {
            for (int col = 1; col <= mapSize; col++) {
                if (map[row][col]) {
                    return new Pos(row, col);
                }
            }
        }

        return null;
    }

    public static boolean isArrange(int row, int col) {
        return row < 1 || col < 1 || row > mapSize || col > mapSize;
    }

    public static void main(String[] args) throws IOException {
        init();

        int count = 0;
        for (int row = 1; row <= mapSize; row++) {
            for (int col = 1; col <= mapSize; col++) {
                if (map[row][col]) {
                    map[row][col] = EMPTY;
                    if (BFS()) {
                        count++;
                        sb.append(row + " " + col + "\n");
                    }
                    map[row][col] = CHOCOLATE;
                }
            }
        }
        System.out.println(count);
        if (count > 0) {
            System.out.println(sb);
        }
    }

    public static void init() throws IOException {
        mapSize = Integer.parseInt(br.readLine().trim());

        map = new boolean[mapSize + 1][mapSize + 1];
        chocolateCount = 0;
        for (int row = 1; row <= mapSize; row++) {
            String input = br.readLine().trim();
            for (int col = 1; col <= mapSize; col++) {
                if (input.charAt(col-1) == '#') {
                    map[row][col] = CHOCOLATE;
                    chocolateCount++;
                }
            }
        }
        chocolateCount--;
    }
}