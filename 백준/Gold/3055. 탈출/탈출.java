import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * 1. 숲의 크기 rowSize, colSize (R, C <= 50)
 * 2. 숲에 정보를 저장하는 forest
 *  2-1. 물(*) 의 위치에 waterNum(-1) 저장
 *  2-2. 고슴도치(S) 의 위치를 저장하는 startRow, startCol (forest 에 0 저장)
 *  2-3. 비버의 굴(D) 는 51 로 저장
 *  2-4. 이동 가능하다면 0 으로 저장, 돌(X)은 -51 로 저장
 * 3. 고슴도치의 위치에서 부터 BFS 시작
 *  3-1. BFS 의 파라미터로 고슴도치의 위치와 이동횟수를 가진 클래스 Sinfo 전달
 *  3-2. 물(waterNum)이 차는 지점에 waterNum - 1 값 저장
 *  3-3. 고슴도치는 4방 으로 0 이거나 방문하지 않은 곳만 이동 가능
 * 4. 도착지까지 온 횟수를 저장하는 resultMoveCnt
 *  4-1. 초기값 -1, 출력 시 -1 이면 "KAKTUS" 출력
 */
public class Main {
    static class Sinfo {
        int row;
        int col;
        int moveCnt;
        int curWater = waterNum;

        public Sinfo(int row, int col, int moveCnt, int curWater) {
            this.row = row;
            this.col = col;
            this.moveCnt = moveCnt;
            curWater = waterNum;
        }
    }

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    public static final int CAN_MOVE = 0;
    public static final int DESTINATION = 251;
    public static final int ROCK = -251;

    public static int rowSize, colSize;
    public static int[][] forest;
    public static int waterNum = -1;

    public static int resultMoveCnt = -1;

    public static int[] deltaRow = {-1, 0, 1, 0};
    public static int[] deltaCol = {0, 1, 0, -1};

    public static void move(Sinfo start) {
        Deque<Sinfo> queueS = new ArrayDeque<>();
        boolean[][] visited = new boolean[rowSize][colSize];

        queueS.add(start);
        visited[start.row][start.col] = true;

        while(!queueS.isEmpty()) {
            Sinfo curS = queueS.poll();

            // D(51) 이면 도착
            if (forest[curS.row][curS.col] == DESTINATION) {
                resultMoveCnt = curS.moveCnt;
                return;
            }

            if (curS.curWater == waterNum) {
                // 물
                spreadWater();
            }

            // 고슴도치 이동
            for (int deltaIdx = 0; deltaIdx < 4; deltaIdx++) {
                int nextRow = curS.row + deltaRow[deltaIdx];
                int nextCol = curS.col + deltaCol[deltaIdx];

                // 인덱스를 벗어나면 패스
                if(isArrange(nextRow, nextCol)) {
                    continue;
                }

                // 0 이거나 도착지면서 방문하지 않은 지점만 가능
                if ((forest[nextRow][nextCol] == CAN_MOVE || forest[nextRow][nextCol] == DESTINATION) && !visited[nextRow][nextCol]) {
                    queueS.add(new Sinfo(nextRow, nextCol, curS.moveCnt + 1, waterNum));
                    visited[nextRow][nextCol] = true;
                }
            }

//            for (int row = 0; row < rowSize; row++) {
//                System.out.print("행 = " + row + "          ");
//                for (int col = 0; col < colSize; col++) {
//                    if(row == curS.row && col == curS.col) {
//                        System.out.print("S ");
//                        continue;
//                    }
//                    System.out.print(forest[row][col] + " ");
//                }
//                System.out.println();
//            }
//            System.out.println("----------------------------");
        }
    }

    public static void spreadWater() {
        // waterNum 과 같은 지점을 4방으로 확장
        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                if(forest[row][col] == waterNum) {
                    // 4 방 확장
                    for (int deltaIdx = 0; deltaIdx < 4; deltaIdx++) {
                        int nextRow = row + deltaRow[deltaIdx];
                        int nextCol = col + deltaCol[deltaIdx];

                        // 인덱스 벗어나면 확장 불가
                        if(isArrange(nextRow, nextCol)) {
                            continue;
                        }

                        // 도착지거나 돌은 확장 불가
                        if (forest[nextRow][nextCol] == DESTINATION || forest[nextRow][nextCol] == ROCK) {
                            continue;
                        }

                        // 확장: waterNum-1
                        forest[nextRow][nextCol] = waterNum - 1;
                    }
                }
            }
        }

        // 다음에 확장될 물 값 갱신
        waterNum--;
    }

    public static boolean isArrange(int row, int col) {
        return row < 0 || col < 0 || row >= rowSize || col >= colSize;
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());

        int startRow = 0, startCol = 0;
        forest = new int[rowSize][colSize];
        for (int row = 0; row < rowSize; row++) {
            String input = br.readLine().trim();
            for (int col = 0; col < colSize; col++) {
                switch (input.charAt(col)) {
                    case '.': forest[row][col] = CAN_MOVE; break; // 이동 가능
                    case '*': forest[row][col] = waterNum; break; // 물
                    case 'X': forest[row][col] = ROCK; break; // 돌
                    case 'S': // 고슴도치(시작위치)
                        forest[row][col] = CAN_MOVE;
                        startRow = row;
                        startCol = col;
                        break;
                    case 'D': forest[row][col] = DESTINATION; break; // 비버(도착위치)
                }
            }
        }

        move(new Sinfo(startRow, startCol, 0, waterNum));

        if(resultMoveCnt == -1) {
            System.out.println("KAKTUS");
            return;
        }

        System.out.println(resultMoveCnt);
    }

}
