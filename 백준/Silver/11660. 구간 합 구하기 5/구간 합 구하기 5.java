import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1. 표의 크기를 저장하는 arrayLength
 * 2. 합을 하는 횟수를 저장하는 sumCount
 * 3. 입력받은 정수형 2차원 배열의 행별 누적합을 저장하는 prefixSumArray
 * 4. 첫 번째 좌표를 저장하는 firstRow, firstCol
 * 	4-1. 2차원 배열의 인덱스에 맞춰 각각 -1 한 값 저장
 * 5. 두 번째 좌표를 저장하는 lastRow, lastCol
 * 	5-1. 2차원 배열의 인덱스에 맞춰 각각 -1 한 값 저장
 * 6. 구간합을 저장하는 prefixSumTotal
 */
public class Main {
    public static BufferedReader br;
    public static StringBuilder sb;
    public static StringTokenizer st;

    public static int arrayLength;
    public static int sumCount;

    public static int[][] prefixSumArray;

    public static int firstRow, firstCol, lastRow, lastCol;

    public static int prefixSumTotal;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        st = new StringTokenizer(br.readLine().trim());
        // 1. 표의 크기를 저장하는 arrayLength
        arrayLength = Integer.parseInt(st.nextToken());

        // 2. 합을 하는 횟수를 저장하는 sumCount
        sumCount = Integer.parseInt(st.nextToken());

        // 3. 입력받은 정수의 누적합을 저장하는 prefixSumArray
        // 인덱스의 시작을 (1, 1) 로 두기 위해 arrayLength+1 크기로 저장
        prefixSumArray = new int[arrayLength+1][arrayLength+1];

        // 행별 누적합 저장
        for(int row = 1; row <= arrayLength; row++) {
            st = new StringTokenizer(br.readLine().trim());

            for(int col = 1; col <= arrayLength; col++) {
                prefixSumArray[row][col] = prefixSumArray[row][col-1] + Integer.parseInt(st.nextToken());
            }
        }

        for(int sc = 1; sc <= sumCount; sc++) {
            st = new StringTokenizer(br.readLine().trim());

            // 4. 첫 번째 좌표를 저장하는 firstRow, firstCol
            firstRow = Integer.parseInt(st.nextToken());
            firstCol = Integer.parseInt(st.nextToken());

            // 5. 두 번째 좌표를 저장하는 lastRow, lastCol
            lastRow = Integer.parseInt(st.nextToken());
            lastCol = Integer.parseInt(st.nextToken());

            // 6. 구간합을 저장하는 prefixSumTotal
            for(int row = firstRow; row <= lastRow; row++) { // 두 좌표가 같다면 한 번만 빼준다.
                prefixSumTotal += prefixSumArray[row][lastCol] - prefixSumArray[row][firstCol-1];
            }

            sb.append(prefixSumTotal).append("\n");
            prefixSumTotal = 0;
        }

        System.out.println(sb);
    }
}