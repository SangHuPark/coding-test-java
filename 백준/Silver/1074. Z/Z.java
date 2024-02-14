import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1. 정수 2^N 을 저장하는 size
 * 2. 방문해야하는 행과 열을 저장하는 targetRow, targetCol
 * 3. size x size 크기의 2차원 배열 zArr
 * 4. 2^(N-1) 씩 분할해가며 zArr 요소 채우기
 * 	4-1. 분할한 사이즈가 1이 됐을 때 element 저장
 * 	4-2. element++
 * 	4-1. 파라미터로 자리에 들어갈 값, row, col
 */
public class Main {
    public static BufferedReader br;
    public static StringTokenizer st;

    public static int size;
    public static int targetRow;
    public static int targetCol;

    public static int element;

    public static int[] deltaRow = {0, 1};
    public static int[] deltaCol = {1, 0};

    public static void zRecursive(int nowSize, int startRow, int startCol) {
        if(nowSize == 1) {
            return;
        }

        int halfSize = nowSize >> 1;

        if(startRow < halfSize && startCol < halfSize) {
            zRecursive(halfSize, startRow, startCol);
        } else if (startRow < halfSize && startCol >= halfSize) {
            element += Math.pow(nowSize, 2) / 4;
            zRecursive(halfSize, startRow, startCol - halfSize);
        } else if (startRow >= halfSize && startCol < halfSize) {
            element += (Math.pow(nowSize, 2) / 4) * 2;
            zRecursive(halfSize, startRow - halfSize, startCol);
        } else{
            element += (Math.pow(nowSize, 2) / 4) * 3;
            zRecursive(halfSize, startRow - halfSize, startCol - halfSize);
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine().trim());

        size = (int) Math.pow(2, Integer.parseInt(st.nextToken()));
        targetRow = Integer.parseInt(st.nextToken());
        targetCol = Integer.parseInt(st.nextToken());

        element = 0;

        zRecursive(size, targetRow, targetCol);

        System.out.println(element);
    }

}