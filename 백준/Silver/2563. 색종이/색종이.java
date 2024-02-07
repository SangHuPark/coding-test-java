import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1. 색종이의 수를 저장하는 colorPaperCount
 * 2. 101x101 크기의 도화지를 저장하는 2차원 배열 paperArray
 * 3. 영역을 구하면 되므로 입력받는 좌표부터 +10 까지 해당 좌표를 1로
 * 4. 1인 좌표의 개수를 저장하는 resultNum
 */
public class Main {
    public static BufferedReader br;
    public static StringTokenizer st;

    public static int colorPaperCount;
    public static int[][] paperArray;
    public static int resultNum;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        // 1. 색종이의 수를 저장하는 colorPaperCount
        colorPaperCount = Integer.parseInt(br.readLine().trim());

        // 2. 101x101 크기의 도화지를 저장하는 2차원 배열 paperArray
        paperArray = new int[101][101];

        for(int cnt = 1; cnt <= colorPaperCount; cnt++) {
            st = new StringTokenizer(br.readLine().trim());
            int rowBound = Integer.parseInt(st.nextToken());
            int colBound = Integer.parseInt(st.nextToken());
            // 한 변이 10이고 입력으로 들어오는 위치는 점이므로 9 만큼 이동
            for(int row = rowBound; row <= rowBound+9; row++) {
                for(int col = colBound; col <= colBound+9; col++) {
                    // 3. 영역을 구하면 되므로 입력받는 좌표부터 +10 까지 해당 좌표를 1로
                    paperArray[row][col] = 1;
                }
            }
        }

        // 4. 1인 좌표의 개수를 저장하는 resultNum
        resultNum = 0;
        for(int row = 0; row < 101; row++) {
            for(int col = 0; col < 101; col++) {
                resultNum += paperArray[row][col] == 1 ? 1 : 0;
            }
        }

        System.out.println(resultNum);
    }
}