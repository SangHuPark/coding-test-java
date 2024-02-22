import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 1. 판의 세로, 가로 길이를 저장하는 rowSize, colSize
 * 2. 판의 정보를 저장하는 map
 * 3. 치즈(1)일 경우 치즈의 가장자리인지 판단
 * 	3-1. 1 에서 출발해 0 을 따라 BFS 탐색
 * 	3-2. 탐색 중 인덱스를 벗어나거나 1 을 만나면 가장자리가 아니다.
 * 	3-3. 방문한 좌표인지 검사하는 visitCheck
 * 	3-4. 방문한 적 없고 && 1 이 아닌 좌표 따라 이동
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

    public static List<Integer> cheezeCountList;
    public static List<Position> edgeList;

    public static final int[] DELTA_ROW = {0, 1, 0, -1};
    public static final int[] DELTA_COL = {1, 0, -1, 0};

    public static boolean findEdge(Position curCheeze) {
        Deque<Position> cheezeQueue = new ArrayDeque<>();
        boolean[][] visit = new boolean[rowSize][colSize];

        int startRow = curCheeze.row;
        int startCol = curCheeze.col;

        cheezeQueue.add(curCheeze);
        visit[startRow][startCol] = true;

        while(!cheezeQueue.isEmpty()) {
            Position next = cheezeQueue.poll();
            int curRow = next.row;
            int curCol = next.col;

            boolean edgeStatus = false;
            for(int deltaIdx = 0; deltaIdx < 4; deltaIdx++) {
                int newRow = curRow + DELTA_ROW[deltaIdx];
                int newCol = curCol + DELTA_COL[deltaIdx];

                if(isArrange(newRow, newCol) || map[newRow][newCol] == 1 || visit[newRow][newCol]) {
                   continue;
                }

                if(isEdge(newRow, newCol)) {
                    edgeStatus = true;
                    break;
                }

                cheezeQueue.addFirst(new Position(newRow, newCol));
                visit[newRow][newCol] = true;
            }

            if(edgeStatus) {
                return true;
            }
        }

        return false;
    }

    public static boolean isEdge(int row, int col) {
        return row == 0 || col == 0 || row == rowSize || col == colSize;
    }

    public static boolean isArrange(int row, int col) {
        return row < 0 || col < 0 || row >= rowSize || col >= colSize;
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());

        map = new int[rowSize][colSize];

        for(int row = 0; row < rowSize; row++) {
            st = new StringTokenizer(br.readLine().trim());
            for(int col = 0; col < colSize; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        cheezeCountList = new ArrayList<>();

        while(true) {
            edgeList = new ArrayList<>();
            int cheezeCount = 0;
            for (int row = 0; row < rowSize; row++) {
                for (int col = 0; col < colSize; col++) {
                    if(map[row][col] == 1) {
                        if(findEdge(new Position(row, col))) {
                            cheezeCount++;
                            edgeList.add(new Position(row, col));
                        }
                    }
                }
            }

            for(Position edge : edgeList) {
                map[edge.row][edge.col] = 0;
            }

            if(cheezeCount == 0) {
                break;
            }

            cheezeCountList.add(cheezeCount);
        }

        System.out.print(cheezeCountList.size() + "\n" + cheezeCountList.get(cheezeCountList.size()-1));
    }
}