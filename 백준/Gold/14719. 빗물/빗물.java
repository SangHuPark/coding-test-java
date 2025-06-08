import java.io.*;
import java.util.*;

/**
 * 1. 각 칸의 블록 높이를 height[] 에 저장한다.
 * 2. 각 칸에 대해 좌측 최대 높이, 우측 최대 높이를 계산한다.
 * 3. 해당 칸에서 고일 수 있는 물 높이 = min(좌측최대, 우측최대) - 현재 높이
 *  3-1. 물 높이가 음수이면 고이지 않으므로 0 처리
 * 4. 모든 칸에 대해 물 높이를 누적합산 출력
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int rowSize, colSize, heightSum;
    static int[] heights;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine().trim());
        heights = new int[colSize];
        for (int idx = 0; idx < colSize; idx++) {
            heights[idx] = Integer.parseInt(st.nextToken());
        }

        heightSum = 0;
        for (int idx = 0; idx < colSize; idx++) {
            int leftHeight = 0;
            for (int left = 0; left < idx; left++) {
                leftHeight = Math.max(leftHeight, heights[left]);
            }

            int rightHeight = 0;
            for (int right = idx+1; right < colSize; right++) {
                rightHeight = Math.max(rightHeight, heights[right]);
            }

            int tmp = Math.min(leftHeight, rightHeight) - heights[idx];
            if (tmp < 0) tmp = 0;

            heightSum += tmp;
        }

        System.out.println(heightSum);
    }

}