import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        while (true) {
            String input = br.readLine().trim();
            if (input.equals("end")) break;

            // 게임판 초기화
            char[][] board = new char[3][3];
            int countX = 0, countO = 0;

            // 보드 생성 및 개수 카운팅
            for (int idx = 0; idx < 9; idx++) {
                int row = idx / 3;
                int col = idx % 3;
                board[row][col] = input.charAt(idx);
                if (board[row][col] == 'X') countX++;
                if (board[row][col] == 'O') countO++;
            }

            // 1. 유효하지 않은 게임 상태
            if (countX < countO || countX - countO >= 2) {
                sb.append("invalid\n");
                continue;
            }

            // 2. 빙고 체크
            boolean bingoX = isBingo(board, 'X');
            boolean bingoO = isBingo(board, 'O');

            // 3. 게임 종료 조건 판단
            if (bingoX && bingoO) {
                sb.append("invalid\n");
            } else if (bingoX) {
                if (countX == countO + 1) sb.append("valid\n");
                else sb.append("invalid\n");
            } else if (bingoO) {
                if (countX == countO) sb.append("valid\n");
                else sb.append("invalid\n");
            } else {
                if (countX + countO == 9) sb.append("valid\n");
                else sb.append("invalid\n");
            }
        }
        System.out.println(sb);
    }

    // 빙고 체크 함수
    public static boolean isBingo(char[][] board, char player) {
        // 가로, 세로 체크
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true;
            }
        }
        // 대각선 체크
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }
        return false;
    }
}
