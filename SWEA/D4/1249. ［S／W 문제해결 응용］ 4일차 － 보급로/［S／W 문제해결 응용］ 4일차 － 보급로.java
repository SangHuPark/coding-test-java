import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * 1. 지도의 크기
 * 2. 2차원 배열 지도 정보
 * 3. (0, 0) -> start, (mapSize-1, mapSize-1) definition
 * 4. MAX 로 초기화한 2차원 배열
 * 5. start 에서 이동하려는 좌표의 originMap 의 값을 더해 maxMap 의 값과 비교해 작으면 갱신 후 이동
 *  5-1. 크면 큐에 삽입 x
 */
public class Solution {
    static class Pos implements Comparable<Pos> {
        int row;
        int col;
        int cost;

        public Pos(int row, int col, int cost) {
            this.row = row;
            this.col = col;
            this.cost = cost;
        }

        @Override
        public int compareTo(Pos o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int mapSize;
    static int[][] originMap;
    static int[][] maxMap;

    static PriorityQueue<Pos> queue;

    static final int[] deltaRow = {0, 1, 0, -1};
    static final int[] deltaCol = {1, 0, -1, 0};

    public static void find() {
        queue = new PriorityQueue<>();

        queue.offer(new Pos(0, 0, 0));

        while(!queue.isEmpty()) {
            Pos pos = queue.poll();

            if (pos.row == mapSize-1 && pos.col == mapSize-1) {
                continue;
            }

            for (int deltaIdx = 0; deltaIdx < 4; deltaIdx++) {
                int nextRow = pos.row + deltaRow[deltaIdx];
                int nextCol = pos.col + deltaCol[deltaIdx];

                if (isArrange(nextRow, nextCol))
                    continue;

                if (pos.cost + originMap[nextRow][nextCol] >= maxMap[nextRow][nextCol])
                    continue;

                queue.add(new Pos(nextRow, nextCol, pos.cost + originMap[nextRow][nextCol]));
                maxMap[nextRow][nextCol] = pos.cost + originMap[nextRow][nextCol];
            }
        }
    }

    public static boolean isArrange(int row, int col) {
        return row < 0 || col < 0 || row >= mapSize || col >= mapSize;
    }

    public static void main(String[] args) throws IOException {
        int testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= testCase; tc++) {
            init();

            find();

            sb.append("#").append(tc).append(" ").append(maxMap[mapSize - 1][mapSize - 1]).append("\n");
        }

        System.out.println(sb);
    }

    public static void init() throws IOException {
        mapSize = Integer.parseInt(br.readLine().trim());

        originMap = new int[mapSize][mapSize];
        maxMap = new int[mapSize][mapSize];
        for (int row = 0; row < mapSize; row++) {
            String input = br.readLine().trim();
            for (int col = 0; col < mapSize; col++) {
                originMap[row][col] = input.charAt(col) - '0';
                maxMap[row][col] = Integer.MAX_VALUE;
            }
        }
    }

}