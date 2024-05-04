import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 1. 맵의 세로, 가로 크기 입력
 * 2. 맵의 정보 입력
 *  2-1. 빈 칸인 지점 리스트에 저장
 *  2-2. 바이러스 지점 리스트에 저장
 * 3. 빈 칸의 개수 중 3개 뽑기 (조합)
 *  3-1. 조합 완성 시 originMap 복사해서 해당 지점에 벽 세우기
 *      3-1-1. 바이러스 퍼뜨리기 (BFS)
 *      3-1-2. 인덱스를 벗어나거나, 방문했거나, 1 인 지점은 못감
 *      3-1-3. BFS 가 끝나면 빈 칸의 개수 계산
 */
public class Main {
    static class Pos {
        int row;
        int col;

        public Pos(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static final int EMPTY = 0, WALL = 1, VIRUS = 2;
    static final int SELECT_COUNT = 3;

    static int rowSize, colSize;
    static int[][] originMap;
    static List<Pos> emptyList;
    static List<Pos> virusList;
    static Deque<Pos> virusQ;

    static int elementCount;
    static int[] selectList;
    static int virusCount;

    static int[][] virusMap;

    static int maxEmptyCount;

    static final int[] deltaRow = {0, 1, 0, -1};
    static final int[] deltaCol = {1, 0, -1, 0};

//    public static void printMap() {
//        for (int row = 0; row < rowSize; row++) {
//            for (int col = 0; col < colSize; col++) {
//                System.out.print(virusMap[row][col] + " ");
//            }
//            System.out.println();
//        }
//    }

    public static void emptyComb(int selectIdx, int elementIdx) {
        if (selectIdx == SELECT_COUNT) {
            copyArray();

            spreadVirus();

            int curEmptyCount = getEmptyCount();

//            printMap();

            maxEmptyCount = Math.max(maxEmptyCount, curEmptyCount);

            return;
        }

        if (elementIdx == elementCount) {
            return;
        }

        selectList[selectIdx] = elementIdx;
        emptyComb(selectIdx + 1, elementIdx + 1);

        selectList[selectIdx] = 0;
        emptyComb(selectIdx, elementIdx + 1);
    }

    public static void spreadVirus() {
        virusQ = new ArrayDeque<>();

        for (int idx = 0; idx < virusCount; idx++) {
            virusQ.add(virusList.get(idx));
        }

        boolean[][] visited = new boolean[rowSize][colSize];
        for (int selectIdx = 0; selectIdx < SELECT_COUNT; selectIdx++) {
            Pos pos = emptyList.get(selectList[selectIdx]);
            visited[pos.row][pos.col] = true;
            virusMap[pos.row][pos.col] = WALL;
        }

        while (!virusQ.isEmpty()) {
            Pos curPos = virusQ.poll();

            for (int deltaIdx = 0; deltaIdx < 4; deltaIdx++) {
                int nextRow = curPos.row + deltaRow[deltaIdx];
                int nextCol = curPos.col + deltaCol[deltaIdx];

                if (isArrange(nextRow, nextCol) || visited[nextRow][nextCol] || virusMap[nextRow][nextCol] == VIRUS || virusMap[nextRow][nextCol] == WALL)
                    continue;

                virusQ.add(new Pos(nextRow, nextCol));
                visited[nextRow][nextCol] = true;
                virusMap[nextRow][nextCol] = VIRUS;
            }
        }
    }

    public static void copyArray() {
        virusMap = new int[rowSize][colSize];

        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                virusMap[row][col] = originMap[row][col];
            }
        }
    }

    public static int getEmptyCount() {
        int emptyCount = 0;

        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                if (virusMap[row][col] == EMPTY)
                    emptyCount++;
            }
        }

        return emptyCount;
    }

    public static boolean isArrange(int row, int col) {
        return row < 0 || col < 0 || row >= rowSize || col >= colSize;
    }

    public static void main(String[] args) throws IOException {
        init();

        emptyComb(0, 0);

        System.out.println(maxEmptyCount);
    }

    public static void init() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());

        originMap = new int[rowSize][colSize];
        emptyList = new ArrayList<>();
        virusList = new ArrayList<>();
        for (int row = 0; row < rowSize; row++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int col = 0; col < colSize; col++) {
                originMap[row][col] = Integer.parseInt(st.nextToken());

                if (originMap[row][col] == EMPTY)
                    emptyList.add(new Pos(row, col));
                else if(originMap[row][col] == VIRUS)
                    virusList.add(new Pos(row, col));
            }
        }

        elementCount = emptyList.size();
        selectList = new int[SELECT_COUNT];
        virusCount = virusList.size();
        maxEmptyCount = 0;
    }
}