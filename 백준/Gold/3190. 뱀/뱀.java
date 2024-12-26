import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

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

    static final int EMPTY = 0, SNAKE = 1, APPLE = 2;
    static final String L = "L", D = "D";

    static int mapSize, appleCnt, commandCnt;
    static int[][] map;
    static Map<Integer, Character> dirChanges;

    static Deque<Pos> q;

    static final int[] deltaRow = {0, 1, 0, -1};
    static final int[] deltaCol = {1, 0, -1, 0};

    public static int simulate(Pos start) {
        q = new ArrayDeque<>();
        q.add(start);
        map[0][0] = SNAKE;

        int time = 0;
        int dir = 0;

        while (!q.isEmpty()) {
            time++;

            Pos head = q.peekLast();

            // 다음 위치
            int nextRow = head.row + deltaRow[dir];
            int nextCol = head.col + deltaCol[dir];

            // 벽 또는 자기 자신이면 게임 종료
            if (isArrange(nextRow, nextCol) || map[nextRow][nextCol] == SNAKE)
                break;

            // 사과면 꼬리 그대로
            if (map[nextRow][nextCol] == APPLE) {
                map[nextRow][nextCol] = SNAKE;
            } // 사과가 아니면 꼬리 이동
            else {
                map[nextRow][nextCol] = SNAKE;
                Pos tail = q.pollFirst();
                map[tail.row][tail.col] = EMPTY;
            }

            // 이동 처리
            q.add(new Pos(nextRow, nextCol));

            // 회전 판단
            if (dirChanges.containsKey(time)) {
                dir = turn(dir, dirChanges.get(time));
            }
        }

        return time;
    }

    public static int turn(int curDir, char command) {
        if (command == 'L')
            return (curDir + 3) % 4;
        else
            return (curDir + 1) % 4;
    }

    public static boolean isArrange(int row, int col) {
        return row < 0 || col < 0 || row >= mapSize || col >= mapSize;
    }

    public static void main(String[] args) throws IOException {
        init();

        System.out.println(simulate(new Pos(0, 0)));
    }

    public static void init() throws IOException {
        mapSize = Integer.parseInt(br.readLine().trim());
        appleCnt = Integer.parseInt(br.readLine().trim());

        map = new int[mapSize][mapSize];
        for (int idx = 0; idx < appleCnt; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int row = Integer.parseInt(st.nextToken()) - 1;
            int col = Integer.parseInt(st.nextToken()) - 1;

            map[row][col] = APPLE;
        }

        dirChanges = new HashMap<>();
        commandCnt = Integer.parseInt(br.readLine().trim());
        for (int idx = 0; idx < commandCnt; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int key = Integer.parseInt(st.nextToken());
            char value = st.nextToken().charAt(0);

            dirChanges.put(key, value);
        }
    }
}
