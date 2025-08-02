import java.io.*;
import java.util.*;

/**
 * BOJ_12100_2048(Easy)
 * @author tkdgn407
 *
 * 1. 보드에서 상, 하, 좌, 우 방향으로 블럭을 이동할 수 있다.
 * 2. 이동 시, 블럭은 해당 방향 끝까지 밀린다.
 * 3. 같은 숫자의 블럭끼리는 한 번만 합칠 수 있으며, 합쳐지면 값이 2배가 된다.
 * 4. 한 번의 이동에서 같은 위치에 두 번 합쳐지는 일은 없다.
 * 5. 5번의 이동을 완전탐색하며 모든 경우를 시뮬레이션한다.
 * 6. 각 경우마다 최대값을 갱신하여, 최종적으로 만들 수 있는 가장 큰 블럭 값을 출력한다.
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static final int EMPTY = 0;
    static final int[] deltaRow = {-1, 1, 0, 0};
    static final int[] deltaCol = {0, 0, -1, 1};

    static class Block {
        int row;
        int col;
        int num;
        boolean flag;

        Block(int row, int col, int num) {
            this.row = row;
            this.col = col;
            this.num = num;
            flag = false;
        }
    }

    static int N, max;
    static int[][] origin;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine().trim());

        origin = new int[N][N];
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int col = 0; col < N; col++) {
                origin[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        max = 0;
        DFS(0, copy(origin));
        System.out.println(max);
    }

    public static void DFS(int depth, int[][] board) {
        if (depth == 5) {
            max = Math.max(max, getMax(board));

            return;
        }

        for (int delta = 0; delta < 4; delta++) {
            DFS(depth + 1, move(board, delta));
        }
    }

    public static int[][] move(int[][] origin, int delta) {
        int[][] board = copy(origin);
        boolean[][] checked = new boolean[N][N];

        // 방향별 루프 제어 변수
        int startRow = 0, endRow = N, stepRow = 1;
        int startCol = 0, endCol = N, stepCol = 1;

        // === 포인트 1 ===
        if (delta == 0) { // 상
            startRow = 1; endRow = N; stepRow = 1;
        } else if (delta == 1) { // 하
            startRow = N - 2; endRow = -1; stepRow = -1;
        } else if (delta == 2) { // 좌
            startCol = 1; endCol = N; stepCol = 1;
        } else { // 우
            startCol = N - 2; endCol = -1; stepCol = -1;
        }

        for (int row = startRow; row != endRow; row += stepRow) {
            for (int col = startCol; col != endCol; col += stepCol) {
                if (board[row][col] == EMPTY) continue;

                // === 포인트 2 ===
                int curRow = row, curCol = col;
                while (true) {
                    int nextRow = curRow + deltaRow[delta];
                    int nextCol = curCol + deltaCol[delta];
                    if (isArrange(nextRow, nextCol)) break;

                    // 빈 칸이라면 이동
                    if (board[nextRow][nextCol] == EMPTY) {
                        board[nextRow][nextCol] = board[curRow][curCol];
                        board[curRow][curCol] = 0;
                        curRow = nextRow; curCol = nextCol;
                    } // 아직 합쳐진 적 없는 같은 값의 블록을 만났다면
                    else if (board[nextRow][nextCol] == board[curRow][curCol] && !checked[nextRow][nextCol]) {
                        board[nextRow][nextCol] <<= 1;
                        board[curRow][curCol] = 0;
                        checked[nextRow][nextCol] = true;
                        break;
                    } // 이미 합쳐진 블록을 만났거나 값이 다른 블록이면 종료
                    else {
                        break;
                    }
                }
            }
        }

        return board;
    }

    public static int[][] copy(int[][] origin) {
        int[][] copied = new int[N][N];
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                copied[row][col] = origin[row][col];
            }
        }

        return copied;
    }

    public static int getMax(int[][] board) {
        int max = -1;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                max = Math.max(max, board[row][col]);
            }
        }
        return max;
    }

    public static boolean isArrange(int row, int col) {
        return row < 0 || row >= N || col < 0 || col >= N;
    }
}