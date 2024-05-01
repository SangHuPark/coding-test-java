import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 1. 맵 크기
 * 2. 맵 정보 입력
 *  2-1. 1 인 위치의 core 는 coreList 에 저장
 *      2-1-1. 가장자리 코어는 저장하지 않음
 * 3. 코어 개수만큼 돌며 하나씩 시나리오 시작
 *  3-1. 4방 + 방향 없음
 *  3-2. 중간에 코어 있으면 전선 설치 불가
 *  3-3. 전선 놓고 놓은 자리 스택에 저장
 */
public class Solution {
    static class Core {
        int row;
        int col;

        public Core(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static class Line {
        int row;
        int col;

        public Line(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int mapSize;
    static int[][] map;

    static List<Core> coreList;
    static int coreCount;

    static int maxCoreCount, minLineCount;

    static final int[] deltaRow = {-1, 1, 0, 0};
    static final int[] deltaCol = {0, 0, 1, -1};

    public static void connectLine(int coreIdx, int connectCoreCount, int connectLineCount) {
        if (coreIdx == coreCount) {
            if (maxCoreCount < connectCoreCount) {
                maxCoreCount = connectCoreCount;
                minLineCount = connectLineCount;
            }
            else if(maxCoreCount == connectCoreCount && minLineCount > connectLineCount) {
                minLineCount = connectLineCount;
            }

            return;
        }

        Core curCore = coreList.get(coreIdx);

        for (int deltaIdx = 0; deltaIdx < 4; deltaIdx++) {
            int lineCount = 0;
            int nextRow = curCore.row + deltaRow[deltaIdx];
            int nextCol = curCore.col + deltaCol[deltaIdx];

            while(!isArrange(nextRow, nextCol) && map[nextRow][nextCol] == 0) {
                map[nextRow][nextCol] = 2;
                lineCount++;
                nextRow += deltaRow[deltaIdx];
                nextCol += deltaCol[deltaIdx];
            }

            if (isArrange(nextRow, nextCol)) {
                connectLine(coreIdx + 1, connectCoreCount + 1, connectLineCount + lineCount);
            }

            nextRow = curCore.row + deltaRow[deltaIdx];
            nextCol = curCore.col + deltaCol[deltaIdx];

            for (int idx = 0; idx < lineCount; idx++) {
                map[nextRow][nextCol] = 0;
                nextRow += deltaRow[deltaIdx];
                nextCol += deltaCol[deltaIdx];
            }
        }

        connectLine(coreIdx + 1, connectCoreCount, connectLineCount);
    }

    public static boolean isArrange(int row, int col) {
        return row < 0 || col < 0 || row >= mapSize || col >= mapSize;
    }

    public static void main(String[] args) throws IOException {
        int testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= testCase; tc++) {
            inputTestCase();

            maxCoreCount = 0;
            minLineCount = Integer.MAX_VALUE;
            connectLine(0, 0, 0);

            sb.append("#").append(tc).append(" ").append(minLineCount).append("\n");
        }

        System.out.println(sb);
    }

    public static void inputTestCase() throws IOException {
        mapSize = Integer.parseInt(br.readLine().trim());

        coreList = new ArrayList<>();
        map = new int[mapSize][mapSize];
        for (int row = 0; row < mapSize; row++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int col = 0; col < mapSize; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());

                if (map[row][col] == 1 && (row != 0 && col != 0 && row != mapSize-1 && col != mapSize-1))
                    coreList.add(new Core(row, col));
            }
        }

        coreCount = coreList.size();
    }
}