import java.io.*;
import java.util.*;

/**
 * 1. 입력을 받아 미로를 2차원 배열로 구성하고, 벽은 1, 빈 공간은 0, 불은 별도 처리용으로 구분한다.
 * 2. 불의 시작 위치와 지훈이의 시작 위치를 따로 저장한 뒤, **불의 BFS를 먼저 돌려** 각 위치에 도달하는 **최소 시간을 기록**한다.
 * 3. 이후 지훈이의 BFS를 돌리며, **다음 칸으로 이동할 때 불보다 늦게 도착하는 칸은 제외**한다.
 * 4. 지훈이가 탈출 가능한 조건은 **가장자리 칸에 불보다 먼저 도착**하는 것이다.
 * 5. BFS는 시간 순으로 진행하며, 더 이상 이동할 수 없고 탈출도 불가능하면 "IMPOSSIBLE"을 출력한다.
 */
public class Main {
    static class Pos {
        int row;
        int col;

        Pos(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static final int EMPTY = -1, WALL = -2, FIRE = 0;
    static int rowSize, colSize;
    static int[][] map;
    static Pos start;
    static Deque<Pos> fires, q;

    static final int[] deltaRow = {0, 1, 0, -1};
    static final int[] deltaCol = {1, 0, -1, 0};

    public static void fire() {
        while (!fires.isEmpty()) {
            Pos cur = fires.poll();
            for (int idx = 0; idx < 4; idx++) {
                int nextRow = cur.row + deltaRow[idx];
                int nextCol = cur.col + deltaCol[idx];

                // 범위를 벗어나면 패스
                if (isArrange(nextRow, nextCol))
                    continue;

                // 빈 공간이 아니면 패스
                if (map[nextRow][nextCol] != EMPTY)
                    continue;

                map[nextRow][nextCol] = map[cur.row][cur.col] + 1;
                fires.add(new Pos(nextRow, nextCol));
            }
        }
    }

    public static int jihun() {
        q = new ArrayDeque<>();
        boolean[][] visited = new boolean[rowSize][colSize];

        q.add(start);
        visited[start.row][start.col] = true;

        int time = 1;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int poll = 0; poll < size; poll++) {
                Pos cur = q.poll();
                for (int idx = 0; idx < 4; idx++) {
                    int nextRow = cur.row + deltaRow[idx];
                    int nextCol = cur.col + deltaCol[idx];

                    // 도착하면 종료
                    if (isArrange(nextRow, nextCol))
                        return time;

                    if (map[nextRow][nextCol] == WALL || visited[nextRow][nextCol])
                        continue;

                    // 현재 시간에 이미 불이면 패스
                    if (map[nextRow][nextCol] > EMPTY && time >= map[nextRow][nextCol])
                        continue;

                    q.add(new Pos(nextRow, nextCol));
                    visited[nextRow][nextCol] = true;
                }
            }
            time++;
        }

        return -1;
    }

    public static boolean isArrange(int row, int col) {
        return row < 0 || col < 0 || row >= rowSize || col >= colSize;
    }

    public static void main(String[] args) throws IOException {
        init();

        fire();

        int answer = jihun();
        if (answer == -1)
            System.out.println("IMPOSSIBLE");
        else
            System.out.println(answer);
    }

    public static void init() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());

        map = new int[rowSize][colSize];
        fires = new ArrayDeque<>();
        for (int row = 0; row < rowSize; row++) {
            String input = br.readLine().trim();
            for (int col = 0; col < colSize; col++) {
                switch (input.charAt(col)) {
                    // 벽
                    case '#': map[row][col] = WALL; break;
                    // 빈 공간
                    case '.': map[row][col] = EMPTY; break;
                    // 불
                    case 'F': map[row][col] = FIRE; fires.add(new Pos(row, col)); break;
                    // 지훈
                    case 'J': map[row][col] = EMPTY; start = new Pos(row, col); break;
                }
            }
        }
    }
}