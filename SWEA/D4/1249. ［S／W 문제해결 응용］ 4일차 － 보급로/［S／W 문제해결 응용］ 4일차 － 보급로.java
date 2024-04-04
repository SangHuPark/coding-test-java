import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * 1. 지도의 크기를 입력받는다.
 * 2. 땅의 정보를 입력받는다.
 * 3. 출발지(0,0)에서 도착지까지 BFS 진행
 * 	3-1. 진행하며 현재 map 에 숫자를 가지고 큐에 삽입
 *  3-2. 좌표 이동 시 누적 좌표값을 저장해 현재 방문하려는 위치의 값과 현재 누적값을 더했을 때 더 작을 경우 방문한다.
 *  3-3. 이미 최소값을 넘은 위치라면 종료
 */
public class Solution {
    static class Tank {
        int row;
        int col;
        int totalTime;

        public Tank(int row, int col, int totalTime) {
            this.row = row;
            this.col = col;
            this.totalTime = totalTime;
        }
    }

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    public static int mapSize;
    public static int[][] map;
    public static int[][] minTimeMap;

    public static Deque<Tank> tankQ;

    public static final int DELTA_LENG = 4;
    public static int[] deltaRow = {0, 1, 0, -1};
    public static int[] deltaCol = {1, 0, -1, 0};

    public static void makeLoad(Tank startTank) {
        tankQ = new ArrayDeque<>();

        tankQ.add(startTank);

        while(!tankQ.isEmpty()) {
            Tank curTank = tankQ.poll();

            // 도착지라면 갱신
            if(curTank.row == mapSize-1 && curTank.col == mapSize-1) {
                minTimeMap[mapSize - 1][mapSize - 1] = Math.min(minTimeMap[mapSize - 1][mapSize - 1], curTank.totalTime);
                continue;
            }

            // 맵에 음수는 없기 때문에 이미 도착지보다 값이 크다면 검사할 필요가 없으므로 패스
            if(curTank.totalTime > minTimeMap[mapSize-1][mapSize-1]) {
                continue;
            }

            for(int deltaIdx = 0; deltaIdx < DELTA_LENG; deltaIdx++) {
                int nextRow = curTank.row + deltaRow[deltaIdx];
                int nextCol = curTank.col + deltaCol[deltaIdx];

                // 인덱스를 벗어나면 패스
                if(isArrange(nextRow, nextCol)) {
                    continue;
                }

                // 다음 위치의 누적합 값이 현재 가진 값 + 기존의 다음 위치 값보다 작다면 패스
                if(minTimeMap[nextRow][nextCol] <= curTank.totalTime + map[nextRow][nextCol]) {
                    continue;
                }

                // 다음 위치의 누적합 보다 작다면 갱신 후 이동
                tankQ.add(new Tank(nextRow, nextCol, curTank.totalTime + map[nextRow][nextCol]));
                minTimeMap[nextRow][nextCol] = curTank.totalTime + map[nextRow][nextCol];
            }
        }

    }

    public static boolean isArrange(int row, int col) {
        return row < 0 || col < 0 || row >= mapSize || col >= mapSize;
    }

    public static void main(String[] args) throws IOException {
        int testCase = Integer.parseInt(br.readLine().trim());

        for(int tc = 1; tc <= testCase; tc++) {
            input();

            makeLoad(new Tank(0, 0, 0));

            sb.append("#").append(tc).append(" ").append(minTimeMap[mapSize-1][mapSize-1]).append("\n");
        }

        System.out.println(sb);
    }

    public static void input() throws IOException {
        mapSize = Integer.parseInt(br.readLine().trim());

        map = new int[mapSize][mapSize];
        minTimeMap = new int[mapSize][mapSize];
        for(int row = 0; row < mapSize; row++) {
            String st = br.readLine().trim();
            for(int col = 0; col < mapSize; col++) {
                map[row][col] = st.charAt(col) - '0';
                // 최소값 갱신을 위해 max 로 초기화
                minTimeMap[row][col] = Integer.MAX_VALUE;
            }
        }
    }

}