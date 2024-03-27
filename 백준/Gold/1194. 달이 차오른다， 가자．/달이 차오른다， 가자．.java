import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * 1. 세로 크기 N -> rowSize, 가로 크기 M -> colSize
 * 2. 미로의 모양을 저장하는 maze
 * 	2-1. 0 을 만나면 해당 위치 startRow, startCol 에 저장
 * 3. 위치와 이동 횟수, 각 열쇠 소지 여부를 저장하는 Moon 클래스
 * 	3-1. 열쇠는 재사용 가능하므로 boolean
 * 	3-2. 문 - 65 를 인덱스로 하는 1차원 배열
 * 4. startRow, startCol 로 Moon 생성 후 BFS
 * 	4-1. 4방 탐색하며 벽이거나 열쇠가 없는 문을 만난 경우 이동 불가
 * 	4-2. 탈출구 1 을 만나면 해당 이동 횟수와 최소 이동 횟수 갱신
 * 5. 최소 이동 횟수가 MAX_VALUE 면 -1 출력
 */
public class Main {
    static class Moon {
        int row;
        int col;
        int keyIdx;
        int moveCnt;

        Moon (int row, int col, int keyIdx, int moveCnt) {
            this.row = row;
            this.col = col;
            this.keyIdx = keyIdx;
            this.moveCnt = moveCnt;
        }
    }

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    public static int rowSize, colSize;
    public static char[][] maze;
    public static int startRow, startCol;

    public static boolean[][][] visited;

    public static int exitResult = -1;

    public static int[] deltaRow = {-1, 0, 1, 0};
    public static int[] deltaCol = {0, 1, 0, -1};

    public static void moveBFS(Moon startMoon) {
        Deque<Moon> queue = new ArrayDeque<>();

        queue.addFirst(startMoon);

        visited[0][startMoon.row][startMoon.col] = true;

        while(!queue.isEmpty()) {
            Moon curMoon = queue.poll();
            int curRow = curMoon.row;
            int curCol = curMoon.col;

            for(int deltaIdx = 0; deltaIdx < 4; deltaIdx++) {
                int nextRow = curRow + deltaRow[deltaIdx];
                int nextCol = curCol + deltaCol[deltaIdx];

                if(isArrange(nextRow, nextCol)) {
                    continue;
                }

                if(maze[nextRow][nextCol] == '#' || visited[curMoon.keyIdx][nextRow][nextCol]) {
                    continue;
                }

                if(maze[nextRow][nextCol] == '1') {
                    exitResult = curMoon.moveCnt + 1;
                    return;
                }

                if(maze[nextRow][nextCol] >= 'A' && maze[nextRow][nextCol] <= 'F') {
                    if(!isOpen(maze[nextRow][nextCol] - 'A', curMoon.keyIdx)) {
                        continue;
                    }
                }

                int nextKeyIdx = curMoon.keyIdx;

                if(maze[nextRow][nextCol] != '.') {
                    nextKeyIdx = getVisitIdx(maze[nextRow][nextCol] - 'a', nextKeyIdx);
                }

                Moon nextMoon = new Moon(nextRow, nextCol, nextKeyIdx, curMoon.moveCnt + 1);

                visited[nextKeyIdx][nextMoon.row][nextMoon.col] = true;

                queue.add(nextMoon);
            }
        }
    }

    public static int getVisitIdx(int newKey, int curKeyIdx) {
        return curKeyIdx | (1 << newKey);
    }

    public static boolean isOpen(int curDoor, int curKeyStatus) {
        return (1 << curDoor) == ((1 << curDoor) & curKeyStatus);
    }

    public static boolean isArrange(int row, int col) {
        return row < 0 || col < 0 || row >= rowSize || col >= colSize;
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());

        maze = new char[rowSize][colSize];
        for(int row = 0; row < rowSize; row++) {
            String input = br.readLine().trim();
            for(int col = 0; col < colSize; col++) {
                maze[row][col] = input.charAt(col);
                if(maze[row][col] == '0') {
                    startRow = row;
                    startCol = col;
                    maze[row][col] = '.';
                }
            }
        }

        visited = new boolean[64][rowSize][colSize];

        moveBFS(new Moon(startRow, startCol, 0, 0));

        System.out.println(exitResult);
    }

}