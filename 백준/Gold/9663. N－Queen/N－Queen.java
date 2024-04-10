import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [ main() ]
 * 1. 체스판의 크기(퀸의 개수) 를 입력받는다.
 * 2. 체스판을 생성한다.
 *
 * [ setChess() ]
 * 3. 모든 행에 퀸을 놓았다면 카운팅 후 종료
 * 4. 0 ~ N-1 열까지 현재 행에 퀸을 놓는다.
 *  4-1. 현재 (행, 열) 이 공격로 라면 넘겨준다.
 *  4-1. 놓은 위치를 기반으로 공격로를 생성한다.
 *  4-2. 다음 행과 현재 공격로 정보를 전달하며 재귀호출한다.
 *
 * [ makeAttack() ]
 * 5. 행, 열, 현재 맵을 받아 현재 맵에 현재 위치에서의 열과 대각선을 공격로 체크 후 반환한다.
 */
public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    static int chessMapSize, queenCount;
    static int[] chessColArr;

    static int successCount;

    public static void setQueen(int selectRow) {
        if (selectRow == queenCount) {
            successCount++;
            return;
        }

        for (int col = 0; col < chessMapSize; col++) {
            chessColArr[selectRow] = col;

            if (isPossible(selectRow)) {
                setQueen(selectRow + 1);
            }
        }
    }

    public static boolean isPossible(int selectRow) {
        for (int row = 0; row < selectRow; row++) {
            // 같은 열에 이미 존재한다면 false
            if(chessColArr[row] == chessColArr[selectRow]) {
                return false;
            }

            // 대각선에 존재한다면 false
            if(Math.abs(row - selectRow) == Math.abs(chessColArr[row] - chessColArr[selectRow])) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        chessMapSize = Integer.parseInt(br.readLine().trim());
        queenCount = chessMapSize;

        chessColArr = new int[chessMapSize];
        successCount = 0;

        setQueen(0);

        System.out.println(successCount);
    }
}