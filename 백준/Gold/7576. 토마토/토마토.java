import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Tomato {
        int row;
        int col;

        Tomato (int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static final int WALL = -1, TOMATO = 1;

    static int rowSize, colSize;
    static int[][] map;
    static List<Tomato> startTomatoes;
    static int totalDays;

    static Deque<Tomato> q;

    static final int[] deltaRow = {0, 1, 0, -1};
    static final int[] deltaCol = {1, 0, -1, 0};

    public static int play() {
        q = new ArrayDeque<>();
        boolean[][] visited = new boolean[rowSize][colSize];

        for (Tomato start : startTomatoes) {
            q.addLast(start);
            visited[start.row][start.col] = true;
        }

        int time = -1;
        while (!q.isEmpty()) {
            int size = q.size();

            for (int T = 0; T < size; T++) {
                Tomato curTomato = q.poll();

                for (int idx = 0; idx < 4; idx++) {
                    int nextRow = curTomato.row + deltaRow[idx];
                    int nextCol = curTomato.col + deltaCol[idx];

                    if (isArrange(nextRow, nextCol) || map[nextRow][nextCol] == WALL || map[nextRow][nextCol] == TOMATO)
                        continue;

                    map[nextRow][nextCol] = TOMATO;
                    q.addLast(new Tomato(nextRow, nextCol));
                }
            }

            time++;
        }

        return time;
    }

    public static boolean isArrange(int row, int col) {
        return row < 0 || col < 0 || row >= rowSize || col >= colSize;
    }

    public static void main(String[] args) throws IOException {
        init();

        if (totalDays == 0) {
            System.out.println(totalDays);
            return;
        }

        int answer = play();

        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                if (map[row][col] == 0) {
                    System.out.println(-1);
                    return;
                }
            }
        }

        System.out.println(answer);
    }

    public static void init() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        colSize = Integer.parseInt(st.nextToken());
        rowSize = Integer.parseInt(st.nextToken());

        int conditionCnt = 0;
        totalDays = -1;
        startTomatoes = new ArrayList<>();
        map = new int[rowSize][colSize];
        for (int row = 0; row < rowSize; row++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int col = 0; col < colSize; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());

                if (map[row][col] != 0) {
                    conditionCnt++;
                    if (map[row][col] == 1) {
                        startTomatoes.add(new Tomato(row, col));
                    }
                }
            }
        }

        // 저장 할 때부터 안익은 토마토가 없다면
        if (conditionCnt == rowSize * colSize)
            totalDays++;
    }

}