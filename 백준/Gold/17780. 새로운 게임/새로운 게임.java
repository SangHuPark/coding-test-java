import java.io.*;
import java.util.*;

/**
 * BOJ_17780_새로운게임
 * @author tkdgn407
 *
 * 1. 0(EMPTY), 1(RED), 2(BLUE) 로 NxN 배열 map에 저장한다.
 *  1-1. 말의 위치를 저장할 Deque[][] posMap을 초기화한다.
 *
 * 2. K+1 배열 horses에 말의 시작 위치와 방향 정보를 저장한다.
 *  2-1. 인덱스 1부터 저장한다.
 *  2-2. Horse 타입으로, 시작 위치/방향/말 번호 를 저장한다.
 *  2-3. 해당 위치의 posMap에 말 번호를 저장한다.
 *
 * 3. 큐에 사이즈(K)를 기준으로 time별 BFS를 진행한다.
 *  3-1. map에서 RED인 경우, posMap의 현재 위치 덱의 번호들을 다음 위치 덱에 pollLast -> addLast 한다.
 *  3-2. BLUE거나 벽인 경우, posMap의 현재 위치 덱의 첫 번째 번호 말의 방향을 반대로 바꾼다.
 *      3-2-1. 홀수면 짝수로, 짝수면 홀수로
 *      3-2-2. 바꾼 방향으로 이동한다.
 *      3-2-3. 이동하려는 위치가 벽이거나 파란색이면 이동하지 않는다.
 *  3-3. EMPTY인 경우, posMap의 현재 위치 덱을 다음 위치로 복사 후 현재 위치는 제거한다.
 *  3-4. 만약 다음 위치의 덱에 추가 시 사이즈가 4 이상이면 현재 time을 출력 후 종료한다.
 *  3-5. time이 1000 이상이면 -1 출력 후 종료한다.
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static final int RED = 1, BLUE = 2;
    // 1부터 순서대로 →, ←, ↑, ↓
    static final int[] deltaRow = {0, 0, 0, -1, 1};
    static final int[] deltaCol = {0, 1, -1, 0, 0};

    static class Horse {
        int row;
        int col;
        int delta;

        Horse(int row, int col, int delta) {
            this.row = row;
            this.col = col;
            this.delta = delta;
        }

        public String toString() {
            String tmp = "( " + row + ", " + col + " ) delta: ";

            switch (this.delta) {
                case 1: tmp += "→"; break;
                case 2: tmp += "←"; break;
                case 3: tmp += "↑"; break;
                case 4: tmp += "↓";
            }

            return tmp;
        }
    }

    static int N, K;
    static int[][] map;
    static List<Integer>[][] posMap;
    static Horse[] horses;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        posMap = new List[N][N];
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int col = 0; col < N; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
                posMap[row][col] = new LinkedList<>();
            }
        }

        horses = new Horse[K + 1];
        for (int idx = 1; idx <= K; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int row = Integer.parseInt(st.nextToken()) - 1;
            int col = Integer.parseInt(st.nextToken()) - 1;
            int delta = Integer.parseInt(st.nextToken());

            horses[idx] = new Horse(row, col, delta);
            posMap[row][col].add(idx);
        }

        play();
    }

    public static void play() {
        int turn = 1;
        while (turn < 1000) {

            for (int idx = 1; idx <= K; idx++) {
                int nextRow = horses[idx].row + deltaRow[horses[idx].delta];
                int nextCol = horses[idx].col + deltaCol[horses[idx].delta];

                // 말 위에 다른 말이 있는 경우, 이동 불가
                if (posMap[horses[idx].row][horses[idx].col].get(0) != idx) continue;

                // 현재 말과 위에 말들만 떼어내기
                List<Integer> moveList = posMap[horses[idx].row][horses[idx].col];

                if (isArrange(nextRow, nextCol) || map[nextRow][nextCol] == BLUE) {
                    // 방향 전환
                    horses[idx].delta = horses[idx].delta % 2 == 0 ? horses[idx].delta - 1 : horses[idx].delta + 1;

                    // 전환한 방향으로 다시 이동
                    nextRow = horses[idx].row + deltaRow[horses[idx].delta];
                    nextCol = horses[idx].col + deltaCol[horses[idx].delta];

                    // 전환 후 이동한 위치도 벽이거나 BLUE면 이동 x
                    if (isArrange(nextRow, nextCol) || map[nextRow][nextCol] == BLUE) continue;
                }

                if (map[nextRow][nextCol] == RED) {
                    Collections.reverse(moveList); // 움직일 말들 뒤집기
                }

                // 이동
                for (int num : moveList) {
                    posMap[nextRow][nextCol].add(num);
                    horses[num].row = nextRow;
                    horses[num].col = nextCol;
                }

                // 현재 위치의 말들은 제거
                moveList.clear();

//                System.out.print(horses[idx] + " | ");

                if (posMap[horses[idx].row][horses[idx].col].size() >= 4) {
                    System.out.println(turn);
                    return;
                }

            }

//            System.out.println();

            turn++;
        }
        System.out.println(-1);
    }

    public static boolean isArrange(int row, int col) {
        return row < 0 || row >= N || col < 0 || col >= N;
    }
}