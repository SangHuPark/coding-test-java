import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * [ init() ]
 * 1. rowSize, colSize 입력
 * 2. 로봇의 출발 좌표 입력
 * 3. 맵의 정보 입력
 *
 * [ moveCleaner() ]
 * 4. 현재 칸이 0 이면 cleaned true
 * 5. 4 방에 0 인 지점이 있는지 검사
 *  5-1. 있다면 반시계로 방향 전환 [ turnCleaner() ]
 *      5-1-1. 전환한 방향으로 이동할 자리가 빈 칸이면서 청소가 안된 경우에만 전진
 *  5-2. 없다면 종료 후 청소한 구역 카운팅
 */
public class Main {
    static class Robot {
        int row;
        int col;
        int direction;

        public Robot(int row, int col, int direction) {
            this.row = row;
            this.col = col;
            this.direction = direction;
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int rowSize, colSize;
    static int[][] map;
    static Robot robot;
    static int startRow, startCol, startDirection;

    static boolean[][] cleaned;

    static int cleanCount;

    static final int[] deltaRow = {-1, 0, 1, 0};
    static final int[] deltaCol = {0, 1, 0, -1};

    public static void moveCleaner(int row, int col, int direction) {
        map[row][col] = 2;

        for (int deltaIdx = 0; deltaIdx < 4; deltaIdx++) {
            direction--;
            if (direction < 0)
                direction = 3;

            int nextRow = row + deltaRow[direction];
            int nextCol = col + deltaCol[direction];

            if (isArrange(nextRow, nextCol) || map[nextRow][nextCol] > 0)
                continue;

            cleanCount++;
            moveCleaner(nextRow, nextCol, direction);
            return;
        }

        int backRow = row + (-1) * deltaRow[direction];
        int backCol = col + (-1) * deltaCol[direction];

        if (map[backRow][backCol] != 1)
            moveCleaner(backRow, backCol, direction);
    }

    public static boolean isArrange(int row, int col) {
        return row < 0 || col < 0 || row >= rowSize || col >= colSize;
    }

    public static void main(String[] args) throws IOException {
        init();

        cleanCount = 1;
        moveCleaner(startRow, startCol, startDirection);

        System.out.println(cleanCount);
    }

    public static void init() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine().trim());
        startRow = Integer.parseInt(st.nextToken());
        startCol = Integer.parseInt(st.nextToken());
        startDirection = Integer.parseInt(st.nextToken());

        map = new int[rowSize][colSize];
        for (int row = 0; row < rowSize; row++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int col = 0; col < colSize; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }
    }
}