import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 1. N 을 저장하는 size
 * 2. 그림을 저장하는 map
 * 3. 적록색약이 아닌 사람의 BFS
 *  3-1. 다른 색이 나올 경우 방문하지 않고 방향 전환
 *  3-2. 같은 색이 나올 경우 방문 처리 및 이동
 * 4. R 과 G 통일
 * 5. 적록색약인 사람의 BFS
 */
public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();

    public static int size;
    public static char[][] map;

    public static boolean[][] visited;

    public static int RGBCount;
    public static int RBCount;

    public static final int[] DELTA_ROW = {0, 1, 0, -1};
    public static final int[] DELTA_COL = {1, 0, -1, 0};

    public static void rgbBfs(int[] start) {
        Deque<int[]> rgbQueue = new ArrayDeque<>();
        rgbQueue.offer(start);
        visited[start[0]][start[1]] = true; // 방문처리

        while (!rgbQueue.isEmpty()) {
            int[] nowNode = rgbQueue.poll();

            int startRow = nowNode[0];
            int startCol = nowNode[1];

            // 탐색 시작점의 색깔 저장
            char nowColor = map[nowNode[0]][nowNode[1]];

            for(int deltaIdx = 0; deltaIdx < 4; deltaIdx++) {
                int newRow = startRow + DELTA_ROW[deltaIdx];
                int newCol = startCol + DELTA_COL[deltaIdx];

                // 인덱스를 벗어나거나 이미 방문한 좌표라면 패스
                if(isArrange(newRow, newCol) || visited[newRow][newCol]) {
                    continue;
                }

                // 3-1. 다른 색이 나올 경우 방문하지 않고 방향 전환
                if(map[newRow][newCol] != nowColor) {
                    continue;
                }

                // 3-2. 같은 색이 나올 경우 방문 처리 및 이동
                rgbQueue.offer(new int[] {newRow, newCol});
                visited[newRow][newCol] = true; // 방문 처리
            }
        }
    }

    public static boolean isArrange(int row, int col) {
        return row < 0 || col < 0 || row >= size || col >= size;
    }

    public static void main(String[] args) throws IOException {
        size = Integer.parseInt(br.readLine().trim());
        map = new char[size][size];

        // map 에 색 저장
        for(int row = 0; row < size; row++) {
            String input = br.readLine().trim();
            for(int col = 0; col < size; col++) {
                map[row][col] = input.charAt(col);
            }
        }

        // 방문 관리를 위한 visited
        visited = new boolean[size][size];

        // 3. 적록색약이 아닌 사람의 BFS
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                // 이미 방문했다면 같은 색깔
                if(visited[row][col]) {
                    continue;
                }

                rgbBfs(new int[] {row, col});
                RGBCount++;
            }
        }

        // 4. R 과 G 통일
        for(int row = 0; row < size; row++) {
            for(int col = 0; col < size; col++) {
                if(map[row][col] == 'G') {
                    map[row][col] = 'R';
                }
            }
        }

        // 방문 관리를 위한 visited
        visited = new boolean[size][size];

        // 5. 적록색약인 사람의 BFS
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if(visited[row][col]) {
                    continue;
                }

                rgbBfs(new int[] {row, col});
                RBCount++;
            }
        }

        sb.append(RGBCount).append(" ").append(RBCount);
        System.out.println(sb);
    }
}