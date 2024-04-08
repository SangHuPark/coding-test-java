import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * [ main() ]
 * 1. 테스트 케이스 개수를 입력받는다.
 *
 * [ inputTestCase() ]
 * 2. 세로 크기, 가로 크기, 맨홀 뚜껑의 위치, 소요 시간 을 입력받는다.
 *  2-1. 시작 위치, 이동한 횟수를 저장하는
 * 3. 지도의 정보를 입력받는다.
 *
 * [ followEscape() ]
 * 4. 맨홀 뚜껑의 위치에서 갈 수 있는 모든 경로를 탐색한다.
 *  4-1. 0 인 지점은 갈 수 없다.
 *  4-2. 0 이 아닌 지점이라면 해당 인덱스의 pipe 배열에 접근해 현재 방향으로 진입할 수 있는 지 검사한다.
 *      4-2-1. 이동할 수 있다면 현재 움직인 횟수+1 해서 큐에 삽입
 *  4-3. poll 한 후 현재 이동 횟수가 소요 시간과 같다면 종료한다.
 */
public class Solution {
    static class Point {
        int row;
        int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static final boolean[][] PIPE_TYPE = {
            {},
            {true, true, true, true}, // + 모양
            {true, false, true, false}, // | 모양
            {false, true, false, true}, // - 모양
            {true, true, false, false}, // ㄴ 모양
            {false, true, true, false}, // |- 모양
            {false, false, true, true}, // ㄱ 모양
            {true, false, false, true} // _| 모양
    };

    static int rowSize, colSize, limitTime;
    static Point startPoint;
    static int[][] map;

    static Deque<Point> escapeQ;
    static int pointCount;

    static final int DELTA_LENG = 4;
    static final int[] DELTA_ROW = {-1, 0, 1, 0};
    static final int[] DELTA_COL = {0, 1, 0, -1};

    public static void followEscape() {
        escapeQ = new ArrayDeque<>();
        boolean[][] visited = new boolean[rowSize][colSize];

        escapeQ.add(startPoint);
        visited[startPoint.row][startPoint.col] = true;
        pointCount = 1;

        int hour = 1;
        while(!escapeQ.isEmpty()) {

            if(hour == limitTime) {
                break;
            }

            int size = escapeQ.size();
            while (size-- > 0) {
                Point curPoint = escapeQ.poll();

                for (int deltaIdx = 0; deltaIdx < DELTA_LENG; deltaIdx++) {
                    if (!PIPE_TYPE[map[curPoint.row][curPoint.col]][deltaIdx]) continue;

                    int nextRow = curPoint.row + DELTA_ROW[deltaIdx];
                    int nextCol = curPoint.col + DELTA_COL[deltaIdx];

                    if (isArrange(nextRow, nextCol) || visited[nextRow][nextCol]) {
                        continue;
                    }

                    if (map[nextRow][nextCol] == 0) {
                        continue;
                    }

                    if (PIPE_TYPE[map[nextRow][nextCol]][(deltaIdx + 2) % 4]) {
                        escapeQ.add(new Point(nextRow, nextCol));
                        visited[nextRow][nextCol] = true;
                        pointCount++;
                    }
                }
            }

            hour++;
        }
    }

    public static boolean isArrange(int row, int col) {
        return row < 0 || col < 0 || row >= rowSize || col >= colSize;
    }

    public static void main(String[] args) throws IOException {
        int testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= testCase; tc++) {
            inputTestCase();

            followEscape();

            sb.append("#").append(tc).append(" ").append(pointCount).append("\n");
        }

        System.out.println(sb);
    }

    public static void inputTestCase() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());
        int startRow = Integer.parseInt(st.nextToken());
        int startCol = Integer.parseInt(st.nextToken());
        limitTime = Integer.parseInt(st.nextToken());

        startPoint = new Point(startRow, startCol);
        map = new int[rowSize][colSize];
        for (int row = 0; row < rowSize; row++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int col = 0; col < colSize; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }
    }
}