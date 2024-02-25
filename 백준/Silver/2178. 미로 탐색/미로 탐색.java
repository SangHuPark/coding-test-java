import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * 1. 행 N, 열 M 을 저장하는 rowSize, colSize
 * 2. 미로의 정보를 저장하는 map
 * 3. 미로를 따라 BFS 탐색
 *  3-1. 시작 위치는 (0, 0) 고정
 *  3-2. 다음 방문이 가능한 좌표의 map[x, y] 값에 현재 좌표값 + 1 저장
 */
public class Main {
    static class Position {
        int row;
        int col;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st;

    public static int rowSize;
    public static int colSize;

    public static int[][] map;

    public static final int[] DELTA_ROW = {0, 1, 0, -1};
    public static final int[] DELTA_COL = {1, 0, -1, 0};

    // 3. 미로를 따라 BFS 탐색
    public static void bfsMaze() {
        // 3-1. 시작 위치는 (0, 0) 고정
        Position start = new Position(0, 0);

        Deque<Position> mazeQueue = new ArrayDeque<>();
        boolean[][] visited = new boolean[rowSize][colSize];

        mazeQueue.add(start);
        visited[start.row][start.col] = true;
        while(!mazeQueue.isEmpty()) {
            Position curPosition = mazeQueue.poll();

            int curRow = curPosition.row;
            int curCol = curPosition.col;

            for(int deltaIdx = 0; deltaIdx < 4; deltaIdx++) {
                int newRow = curRow + DELTA_ROW[deltaIdx];
                int newCol = curCol + DELTA_COL[deltaIdx];

                if(isArrange(newRow, newCol) || visited[newRow][newCol] || map[newRow][newCol] == 0) {
                    continue;
                }

                mazeQueue.add(new Position(newRow, newCol));
                visited[newRow][newCol] = true;
                // 3-2. 다음 방문이 가능한 좌표의 map[x, y] 값에 현재 좌표값 + 1 저장
                map[newRow][newCol] = map[curRow][curCol] + 1;
            }
        }
    }

    public static boolean isArrange(int row, int col) {
        return row < 0 || col < 0 || row >= rowSize || col >= colSize;
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine().trim());

        // 1. 행 N, 열 M 을 저장하는 rowSize, colSize
        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());

        // 2. 미로의 정보를 저장하는 map
        map = new int[rowSize][colSize];
        for(int row = 0; row < rowSize; row++) {
            String input = br.readLine().trim();
            for(int col = 0; col < colSize; col++) {
                map[row][col] = input.charAt(col) - '0';
            }
        }

        bfsMaze();

        // 도착 위치값 출력
        System.out.println(map[rowSize-1][colSize-1]);
    }
}