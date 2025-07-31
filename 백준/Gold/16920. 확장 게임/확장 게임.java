import java.io.*;
import java.util.*;

/**
 * BOJ_문제번호_문제명
 * @author tkdgn407
 *
 * 1.
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static final int EMPTY = 0, WALL = -1;
    static final int[] deltaRow = {-1, 0, 1, 0};
    static final int[] deltaCol = {0, 1, 0, -1};

    static class Pos {
        int x;
        int y;

        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N, M, P;
    static int[] S;
    static int[][] map;
    static boolean[][][] checked;
    static boolean[] finish;
    static Deque<Pos>[] q;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine().trim());
        S = new int[P+1];
        for (int idx = 1; idx <= P; idx++) {
            S[idx] = Integer.parseInt(st.nextToken());
        }

        checked = new boolean[N][M][P + 1];
        q = new Deque[P + 1];
        for (int idx = 0; idx <= P; idx++) {
            q[idx] = new ArrayDeque<>();
        }

        map = new int[N][M];
        for (int row = 0; row < N; row++) {
            String input = br.readLine().trim();
            for (int col = 0; col < M; col++) {
                char ch = input.charAt(col);
                if (ch == '.')
                    map[row][col] = EMPTY;
                else if (ch == '#')
                    map[row][col] = WALL;
                else {
                    int idx = ch - '0';
                    map[row][col] = idx;
                    q[idx].addLast(new Pos(row, col));
                }
            }
        }

        finish = new boolean[P+1];
        while (isComplete()) {
            for (int turnIdx = 1; turnIdx <= P; turnIdx++) {
                if (finish[turnIdx]) continue;

                BFS(turnIdx);
            }
        }

        int[] answer = new int[P + 1];
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                if (map[row][col] <= EMPTY) continue;

                answer[map[row][col]]++;
            }
        }
        for (int idx = 1; idx <= P; idx++) {
            sb.append(answer[idx]).append(" ");
        }
        System.out.println(sb);
    }

    public static void BFS(int idx) {
        if (q[idx].isEmpty()) {
            finish[idx] = true;
            return;
        }

        for (int dis = 0; dis < S[idx]; dis++) {
            int cnt = 0;
            int size = q[idx].size();

            if (size == 0) return;

            for (int poll = 0; poll < size; poll++) {
                Pos pos = q[idx].poll();

                for (int delta = 0; delta < 4; delta++) {
                    int nextRow = pos.x + deltaRow[delta];
                    int nextCol = pos.y + deltaCol[delta];

                    //  || checked[nextRow][nextCol][idx]
                    if (isArrange(nextRow, nextCol))
                        continue;

                    if (map[nextRow][nextCol] != EMPTY) continue;

//                    checked[nextRow][nextCol][idx] = true;
                    map[nextRow][nextCol] = idx;
                    q[idx].addLast(new Pos(nextRow, nextCol));
                    cnt++;
                }
            }

            if (cnt == 0) {
                finish[idx] = true;
                return;
            }
        }

    }

    public static boolean isComplete() {
        for (int idx = 1; idx <= P; idx++) {
            if (!finish[idx]) return true;
        }

        return false;
    }

    public static boolean isArrange(int row, int col) {
        return row < 0 || row >= N || col < 0 || col >= M;
    }
}