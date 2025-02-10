import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Tomato {
        int depth;
        int row;
        int col;

        Tomato(int depth, int row, int col) {
            this.depth = depth;
            this.row = row;
            this.col = col;
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static final int EMPTY = 0, WALL = -1, TOMATO = 1;

    static int colSize, rowSize, depthSize;
    static int[][][] map;
    static List<Tomato> initTomatoes;
    static int totalDays;

    static Deque<Tomato> q;

    static final int[] deltaDepth = {-1, 1, 0, 0, 0, 0};
    static final int[] deltaRow = {0, 0, -1, 1, 0, 0};
    static final int[] deltaCol = {0, 0, 0, 0, -1, 1};

    public static void go() {
        q = new ArrayDeque<>();

        for (Tomato tomato : initTomatoes)
            q.addLast(tomato);

        totalDays = -1;
        while (!q.isEmpty()) {
            int qSize = q.size();

            for (int time = 0; time < qSize; time++) {
                Tomato tomato = q.poll();

                for (int deltaIdx = 0; deltaIdx < 6; deltaIdx++) {
                    int nextDepth = tomato.depth + deltaDepth[deltaIdx];
                    int nextRow = tomato.row + deltaRow[deltaIdx];
                    int nextCol = tomato.col + deltaCol[deltaIdx];

                    if (isArrange(nextDepth, nextRow, nextCol) || map[nextDepth][nextRow][nextCol] == WALL || map[nextDepth][nextRow][nextCol] == TOMATO)
                        continue;

                    map[nextDepth][nextRow][nextCol] = TOMATO;
                    q.addLast(new Tomato(nextDepth, nextRow, nextCol));
                }
            }

            totalDays++;
        }
    }

    public static boolean isArrange(int depth, int row, int col) {
        return depth < 0 || depth >= depthSize || row < 0 || row >= rowSize || col < 0 || col >= colSize;
    }

    public static void main(String[] args) throws IOException {
        init();

        if (totalDays == 0) {
            System.out.println(totalDays);
            return;
        }

        go();

        for (int depth = 0; depth < depthSize; depth++) {
            for (int row = 0; row < rowSize; row++) {
                for (int col = 0; col < colSize; col++) {
                    if (map[depth][row][col] == EMPTY) {
                        System.out.println(-1);
                        return;
                    }
                }
            }
        }

        System.out.println(totalDays);
    }

    public static void init() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        colSize = Integer.parseInt(st.nextToken());
        rowSize = Integer.parseInt(st.nextToken());
        depthSize = Integer.parseInt(st.nextToken());

        int notEmptyCnt = 0;
        totalDays = -1;
        initTomatoes = new ArrayList<>();
        map = new int[depthSize][rowSize][colSize];
        for (int depth = 0; depth < depthSize; depth++) {
            for (int row = 0; row < rowSize; row++) {
                st = new StringTokenizer(br.readLine().trim());
                for (int col = 0; col < colSize; col++) {
                    map[depth][row][col] = Integer.parseInt(st.nextToken());

                    if (map[depth][row][col] != EMPTY) {
                        // 벽이거나 토마토인 경우 카운팅
                        notEmptyCnt++;

                        // 토마토이면 추가
                        if (map[depth][row][col] == TOMATO)
                            initTomatoes.add(new Tomato(depth, row, col));
                    }
                }
            }
        }

        // 만약 빈칸이 없다면 익을 토마토가 없는 경우를 체크
        if (notEmptyCnt == depthSize * rowSize * colSize)
            totalDays = 0;
    }
}