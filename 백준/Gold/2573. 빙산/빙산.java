import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Pos {
        int row;
        int col;

        Pos (int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int rowSize, colSize;
    static int[][] map;
    static List<Pos> graph;
    static int graphSize;

    static int[][] meltMap;

    static final int[] deltaRow = {0, 1, 0, -1};
    static final int[] deltaCol = {1, 0, -1, 0};

    public static void meltCheck(int iceIdx) {
        if (iceIdx == graphSize) {
            return;
        }

        Pos curIce = graph.get(iceIdx);
        int row = curIce.row;
        int col = curIce.col;
        int zeroCnt = 0;
        for (int deltaIdx = 0; deltaIdx < 4; deltaIdx++) {
            int nextRow = row + deltaRow[deltaIdx];
            int nextCol = col + deltaCol[deltaIdx];

            if (isArrange(nextRow, nextCol) || map[nextRow][nextCol] > 0)
                continue;

            zeroCnt++;
        }

        // 녹을 값 체크
        meltMap[row][col] += Math.min(map[row][col], zeroCnt);

        // 다음 빙산
        meltCheck(iceIdx+1);

    }

    // 모든 빙산이 돌고 녹아야 하는 만큼 녹는 것 진행
    public static void melt() {
        for (int idx = graphSize - 1; idx >= 0; idx--) {
            Pos ice = graph.get(idx);
            int row = ice.row;
            int col = ice.col;

            map[row][col] -= meltMap[row][col];

            if (map[row][col] == 0)
                graph.remove(idx);
        }
    }

    /**
     * 덩어리 계산
     */
    public static int getIce() {
        int iceCnt = 0;
        Deque<Pos> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[rowSize][colSize];

        for (Pos ice : graph) {
            if (visited[ice.row][ice.col])
                continue;

            q.addLast(ice);
            visited[ice.row][ice.col] = true;

            while (!q.isEmpty()) {
                Pos curIce = q.poll();

                for (int deltaIdx = 0; deltaIdx < 4; deltaIdx++) {
                    int nextRow = curIce.row + deltaRow[deltaIdx];
                    int nextCol = curIce.col + deltaCol[deltaIdx];

                    if (isArrange(nextRow, nextCol) || map[nextRow][nextCol] == 0 || visited[nextRow][nextCol])
                        continue;

                    q.addLast(new Pos(nextRow, nextCol));
                    visited[nextRow][nextCol] = true;
                }
            }

            iceCnt++;
        }

        return iceCnt;
    }

    public static boolean isArrange(int row, int col) {
        return row < 0 || col < 0 || row >= rowSize || col >= colSize;
    }

    public static void main(String[] args) throws IOException {
        init();

        int year = 0;
        while (true) {
            int iceCnt = getIce();
            if (iceCnt >= 2) {
                System.out.println(year);
                return;
            }

            // 만일 전부 다 녹을 때까지 두 덩어리 이상으로 분리되지 않으면 프로그램은 0을 출력
            graphSize = graph.size();
            if (graphSize == 0) {
                System.out.println(0);
                return;
            }

            meltMap = new int[rowSize][colSize];
            meltCheck(0);
            melt();
            year++;

//            System.out.println("=============================");
//            for (int row = 0; row < rowSize; row++) {
//                for (int col = 0; col < colSize; col++) {
//                    System.out.print(map[row][col] + " ");
//                }
//                System.out.println();
//            }
        }
    }

    public static void init() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        map = new int[rowSize][colSize];
        for (int row = 0; row < rowSize; row++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int col = 0; col < colSize; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());

                if (map[row][col] != 0)
                    graph.add(new Pos(row, col));
            }
        }
    }

}