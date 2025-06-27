import java.io.*;
import java.util.*;

/**
 * BOJ_19236_청소년상어
 * @author tkdgn407
 *
 * 1. 물고기 정보를 입력 받는다.
 *  1-1. map[4][4]에는 각 칸의 물고기 번호를 저장하고,
 *  1-2. fish[17]에는 각 물고기의 위치(x, y)와 방향을 저장한다.
 * 2. 상어는 (0,0)의 물고기를 먹고 해당 물고기의 방향을 얻어 시작한다.
 *  2-1. map[0][0]은 상어(0)로 표시하고, 먹힌 물고기 번호는 fish[]에서 제거한다.
 * 3. 물고기 번호 1~16을 순서대로 돌며 각 물고기를 이동시킨다.
 *  3-1. 현재 방향으로 이동 불가능하면 최대 8번 회전하며 가능한 칸을 찾는다.
 *  3-2. 빈 칸이면 이동하고, 다른 물고기면 서로 위치를 교환한다.
 * 4. 상어 DFS
 *  4-1. 상어는 현재 방향으로 1~3칸 이동한다.
 *  4-2. 해당 칸에 물고기가 있는 경우만 재귀적으로 이동한다.
 *  4-3. 매 이동마다 먹은 물고기 번호를 누적하고, 방향을 갱신한다.
 *  4-4. 재귀 종료 조건은 더 이상 이동 가능한 칸이 없을 때이며,
 *  4-5. 이때까지 먹은 물고기 번호의 합을 최대값으로 갱신한다.
 * 5. DFS에서는 map과 fish 상태를 깊은 복사하여 넘긴다.
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static final int SHARK = 0, EMPTY = -1;
    // 0부터 순서대로 ↑, ↖, ←, ↙, ↓, ↘, →, ↗
    static final int[] deltaRow = {-1, -1, 0, 1, 1, 1, 0, -1};
    static final int[] deltaCol = {0, -1, -1, -1, 0, 1, 1, 1};

    static class Pos {
        int row;
        int col;
        int delta;

        Pos(int row, int col, int delta) {
            this.row = row;
            this.col = col;
            this.delta = delta;
        }
    }

    static int[][] map;
    static Pos[] fishes;
    static int maxPrefixSum;

    public static void main(String[] args) throws IOException {
        map = new int[4][4];
        fishes = new Pos[17];

        for (int row = 0; row < 4; row++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int col = 0; col < 4; col++) {
                int idx = Integer.parseInt(st.nextToken());
                int delta = Integer.parseInt(st.nextToken()) - 1;

                map[row][col] = idx;
                fishes[idx] = new Pos(row, col, delta);
            }
        }

        // 2. 상어는 (0,0)의 물고기를 먹고 해당 물고기의 방향을 얻어 시작한다.
        int startIdx = map[0][0];
        int startDelta = fishes[startIdx].delta;
        map[0][0] = SHARK;
        fishes[startIdx] = null;

        // 3. 물고기 번호 1~16을 순서대로 돌며 각 물고기를 이동시킨다.
        moveFish(map, fishes);

        maxPrefixSum = Integer.MIN_VALUE;
        moveShark(map, fishes, new Pos(0, 0, startDelta), startIdx);
        System.out.println(maxPrefixSum);
    }

    public static void moveFish(int[][] copyMap, Pos[] copyFishes) {
        for (int fishIdx = 1; fishIdx <= 16; fishIdx++) {
            // 먹힌 물고기면 패스
            if (Objects.isNull(copyFishes[fishIdx])) continue;

            Pos fish = copyFishes[fishIdx];

            // 3-1. 현재 방향으로 이동 불가능하면 최대 8번 회전하며 가능한 칸을 찾는다.
            for (int turn = 0; turn < 8; turn++) {
                int nextDelta = (fish.delta + turn) % 8;
                int nextRow = fish.row + deltaRow[nextDelta];
                int nextCol = fish.col + deltaCol[nextDelta];

                // 맵 밖이거나 상어면 회전하러 패스
                if (isArrange(nextRow, nextCol) || copyMap[nextRow][nextCol] == SHARK) continue;

                // 3-2. 빈 칸이면 이동하고, 다른 물고기면 서로 위치를 교환한다.
                if (copyMap[nextRow][nextCol] == EMPTY) {
                    copyMap[nextRow][nextCol] = fishIdx;
                    copyMap[fish.row][fish.col] = EMPTY;
                    copyFishes[fishIdx] = new Pos(nextRow, nextCol, nextDelta);
                } else {
                    int nextIdx = copyMap[nextRow][nextCol];

                    // map 위치 교환
                    copyMap[fish.row][fish.col] = nextIdx;
                    copyMap[nextRow][nextCol] = fishIdx;

                    // fishes 값 스왑
                    int switchFishDelta = copyFishes[nextIdx].delta;
                    copyFishes[nextIdx] = new Pos(fish.row, fish.col, switchFishDelta);
                    copyFishes[fishIdx] = new Pos(nextRow, nextCol, nextDelta);
                }
                break;
            }
        }
    }

    public static void moveShark(int[][] copyMap, Pos[] copyFishes, Pos shark, int curPrefixSum) {
        maxPrefixSum = Math.max(maxPrefixSum, curPrefixSum);

        // 4-1. 상어는 현재 방향으로 1~3칸 이동한다.
        for (int move = 1; move <= 3; move++) {
            int nextRow = shark.row + deltaRow[shark.delta] * move;
            int nextCol = shark.col + deltaCol[shark.delta] * move;

            if (isArrange(nextRow, nextCol)) return;

            // 물고기가 없는 칸이면 이동 불가
            if (copyMap[nextRow][nextCol] <= 0) continue;

            // 상어가 이동한 것으로 계산
            int nextIdx = copyMap[nextRow][nextCol]; // 먹힐 물고기

            // 먹은 후 맵과 물고기 상태 생성
            int[][] nextMap = deepCopyMap(copyMap);
            Pos[] nextFishes = deepCopyFish(copyFishes);

            nextMap[shark.row][shark.col] = EMPTY; // 현재 자리 빈 칸 처리
            nextMap[nextRow][nextCol] = SHARK; // 상어 이동
            nextFishes[nextIdx] = null; // 물고기 먹힘 처리

            moveFish(nextMap, nextFishes);

            moveShark(nextMap, nextFishes, new Pos(nextRow, nextCol, copyFishes[nextIdx].delta), curPrefixSum + nextIdx);
        }
    }

    public static int[][] deepCopyMap(int[][] origin) {
        int[][] copy = new int[origin.length][origin[0].length];
        for (int idx = 0; idx < origin.length; idx++) {
            copy[idx] = Arrays.copyOf(origin[idx], origin[idx].length);
        }
        return copy;
    }

    public static Pos[] deepCopyFish(Pos[] origin) {
        Pos[] copy = new Pos[origin.length];
        for (int idx = 0; idx < origin.length; idx++) {
            if (Objects.isNull(origin[idx])) continue;

            copy[idx] = new Pos(origin[idx].row, origin[idx].col, origin[idx].delta);
        }
        return copy;
    }

    public static boolean isArrange(int row, int col) {
        return row < 0 || row >= 4 || col < 0 || col >= 4;
    }
}