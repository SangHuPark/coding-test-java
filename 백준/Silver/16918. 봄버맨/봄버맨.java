import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 0초 - 일부 칸에 폭탄을 설치 (초기값)
 * 1초 - 아무것도 하지 않음
 * 2초 - 빈 칸에 폭탄 설치 -> 모든 칸이 폭탄
 * 3초 - 초기값의 폭탄이 모두 폭발
 *
 * - 폭탄이 터지면 상하좌우가 사라짐
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int R, C, N;
    static int[][] map;
    static boolean[][] checked;

    static final int[] delta_row = {0, 1, 0, -1};
    static final int[] delta_col = {1, 0, -1, 0};

    public static void play() {
        flowSec();

        for (int sec = 2; sec <= N; sec++) {
            checked = new boolean[R][C];

            flowSec();

            fullBomb();

            bomb();

//            for (int row = 0; row < R; row++) {
//                for (int col = 0; col < C; col++) {
//                    System.out.print(map[row][col] + " ");
//                }
//                System.out.println();
//            }
//            System.out.println("*************************************");

        }
    }

    // 1초가 지나고 빈 칸을 모두 폭탄으로 바꾸는 메서드
    public static void fullBomb() {
        for (int row = 0; row < R; row++) {
            for (int col = 0; col < C; col++) {
                if (map[row][col] == -1)
                    map[row][col] = 3;
            }
        }
    }

    // 3초 뒤에 터질 모든 폭탄의 시간초를 줄이는 메서드
    public static void flowSec() {
        for (int row = 0; row < R; row++) {
            for (int col = 0; col < C; col++) {
                if (map[row][col] > 0) {
                    map[row][col]--;
                }

                if (map[row][col] == 0) {
                    checked[row][col] = true;
                    for (int delta = 0; delta < 4; delta++) {
                        int nextRow = row + delta_row[delta];
                        int nextCol = col + delta_col[delta];

                        if (isArrnage(nextRow, nextCol)) {
                            continue;
                        }

                        checked[nextRow][nextCol] = true;
                    }
                }
            }
        }
    }

    // 폭탄이 터지는 메서드
    public static void bomb() {
        for (int row = 0; row < R; row++) {
            for (int col = 0; col < C; col++) {
                if (checked[row][col]) {
                    map[row][col] = -1;
                }

            }
        }
    }

    public static boolean isArrnage(int row, int col) {
        return row < 0 || row >= R || col < 0 || col >= C;
    }

    public static void main(String[] args) throws IOException {

        init();

        play();

        for (int row = 0; row < R; row++) {
            for (int col = 0; col < C; col++) {
                sb.append(map[row][col] == -1 ? '.' : 'O');
            }
            sb.append('\n');
        }

        System.out.println(sb);

    }

    public static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        for (int row = 0; row < R; row++) {
            String input = br.readLine();
            for (int col = 0; col < C; col++) {
                char ch = input.charAt(col);

                switch (ch) {
                    case '.':
                        map[row][col] = -1; break;
                    case 'O':
                        map[row][col] = 3; break;
                }
            }

        }

    }
}