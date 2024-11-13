import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Pos {
        int row;
        int col;
        int time;

        public Pos(int row, int col, int time) {
            this.row = row;
            this.col = col;
            this.time = time;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int mapSize;
    static int[][] map;
    static final int START = 9;

    static int sharkSize, sharkRow, sharkCol, eatCount, resultTime;
    static Deque<Pos> q;

    static final int[] deltaRow = {0, 1, 0, -1};
    static final int[] deltaCol = {1, 0, -1, 0};

    // 부모를 사용하지 않고 가능한 경우를 수행
    public static boolean eatFish() {
        resultTime = 0;

        // 사이즈만큼 먹으면 먹은 수 초기화 후 사이즈 증가
        if (eatCount == sharkSize) {
            eatCount = 0;
            sharkSize++;
        }

        int minRow = Integer.MAX_VALUE;
        int minCol = Integer.MAX_VALUE;
        int minTime = Integer.MAX_VALUE;

        q = new ArrayDeque<>();
        boolean[][] visited = new boolean[mapSize][mapSize];

        q.add(new Pos(sharkRow, sharkCol, 0));
        visited[sharkRow][sharkCol] = true;

        while (!q.isEmpty()) {
            Pos curPos = q.poll();

            if (curPos.time >= minTime)
                break;

            for (int deltaIdx = 0; deltaIdx < 4; deltaIdx++) {
                int nextRow = curPos.row + deltaRow[deltaIdx];
                int nextCol = curPos.col + deltaCol[deltaIdx];

                if (isArrange(nextRow, nextCol) || visited[nextRow][nextCol])
                    continue;

                // 더 큰 사이즈면 패스
                if (map[nextRow][nextCol] > sharkSize)
                    continue;

                // 빈 공간이 아니면 더 작은 생선이므로 갱신
                if (map[nextRow][nextCol] > 0 && map[nextRow][nextCol] < sharkSize) {
                    // 가장 위에 있는 생선을 찾기 위해 이동 후 시간 증가
                    if (nextRow < minRow) {
                        minRow = nextRow;
                        minCol = nextCol;
                        minTime = curPos.time + 1;
                    } // row 가 같으면 왼쪽 생선으로 이동
                    else if (nextRow == minRow) {
                        if (nextCol < minCol) {
                            minCol = nextCol;
                            minTime = curPos.time + 1;
                        }
                    }
                }

                // 빈 공간이면 그냥 이동
                q.offer(new Pos(nextRow, nextCol, curPos.time + 1));
                visited[nextRow][nextCol] = true;
            }

        }

        if (minTime == Integer.MAX_VALUE) {
            return false;
        } // 가장 먼저 먹어야 하는 생선으로 이동 후 걸린 시간 갱신 및 먹었음 처리
        else {
            sharkRow = minRow;
            sharkCol = minCol;
            eatCount++;
            resultTime = minTime;
            map[sharkRow][sharkCol] = 0;

            return true;
        }

    }

    public static boolean isArrange(int row, int col) {
        return row < 0 || col < 0 || row >= mapSize || col >= mapSize;
    }

    public static void main(String[] args) throws IOException {
        init();

        int totalTime = 0;
        while (eatFish()) {
            totalTime += resultTime;
        }
        System.out.println(totalTime);
    }

    public static void init() throws IOException {
        mapSize = Integer.parseInt(br.readLine().trim());

        map = new int[mapSize][mapSize];
        for (int row = 0; row < mapSize; row++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int col = 0; col < mapSize; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());

                if (map[row][col] == START) {
                    sharkRow = row;
                    sharkCol = col;
                    map[row][col] = 0;
                }
            }
        }

        sharkSize = 2;
    }

}