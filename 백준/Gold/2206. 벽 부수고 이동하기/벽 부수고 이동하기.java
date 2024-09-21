import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 */
public class Main {
    static class Pos {
        int row;
        int col;
        boolean used;
        int moveCnt;

        public Pos(int row, int col, boolean used, int moveCnt) {
            this.row = row;
            this.col = col;
            this.used = used;
            this.moveCnt = moveCnt;
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static final int EMPTY = 0;
    static final int WALL = 1;

    static int N, M;
    static int[][] map;

    static Deque<Pos> q;

    static final int[] delta_row = {0, 1, 0, -1};
    static final int[] delta_col = {1, 0, -1, 0};

    public static int bfs(Pos start) {
        q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];
        boolean[][] broken_visited = new boolean[N][M];

        q.addLast(start);
        visited[start.row][start.col] = true;

        while (!q.isEmpty()) {
            Pos curPos = q.poll();

            if (curPos.row == N - 1 && curPos.col == M - 1) {
                return curPos.moveCnt;
            }

            for (int delta = 0; delta < 4; delta++) {
                int nextRow = curPos.row + delta_row[delta];
                int nextCol = curPos.col + delta_col[delta];

                // 벗어났으면 패스
                if (isArrange(nextRow, nextCol))
                    continue;

                // 부순 벽이 있다면
                if (curPos.used) {
                    // 방문했다면 패스
                    if (broken_visited[nextRow][nextCol])
                        continue;
                    
                    // 벽이 아닐 때만 이동
                    if (map[nextRow][nextCol] == EMPTY) {
                        broken_visited[nextRow][nextCol] = true;
                        q.add(new Pos(nextRow, nextCol, curPos.used, curPos.moveCnt + 1));
                    } // 벽이라면 패스
                    else {
                        continue;
                    }
                } // 부순 벽이 없다면
                else {
                    // 방문했다면 패스
                    if (visited[nextRow][nextCol])
                        continue;
                    
                    // 벽이라면 부수고 이동
                    if (map[nextRow][nextCol] == WALL) {
                        broken_visited[nextRow][nextCol] = true;
                        q.add(new Pos(nextRow, nextCol, true, curPos.moveCnt + 1));
                    } // 이동 가능할 때
                    else if (map[nextRow][nextCol] == EMPTY) {
                        visited[nextRow][nextCol] = true;
                        q.add(new Pos(nextRow, nextCol, curPos.used, curPos.moveCnt + 1));
                    }
                }

            }

        }

        return -1;
    }

    public static boolean isArrange(int row, int col) {
        return row < 0 || row >= N || col < 0 || col >= M;
    }

    public static void main(String[] args) throws IOException {

        init();

        System.out.println(bfs(new Pos(0, 0, false, 1)));
    }

    public static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int row = 0; row < N; row++) {
            String tmp = br.readLine();
            for (int col = 0; col < M; col++) {
                map[row][col] = tmp.charAt(col) - '0';
            }
        }
    }
}