import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [조건]
 *
 * 1. 집의 수 N 을 저장하는 homeCount
 * 2. 각 집에 칠할 수 있는 RGB 비용을 저장하는 RGBList
 * 3. N-1 번째 집에서 시작해 +1 번째의 최적 선택을 하며 누적합의 최솟값 갱신
 * 	3-1. N-1 ~ 1 번까지 반복하며 갱신
 * 	3-2. rgbIdx 0 ~ 3 까지 해당 인덱스 제외하고 최소값을 누적합
 * 4. 비용의 최솟값을 저장하는 minPrice
 *  4-1. 1번째 rgb 중 최솟값
 */
public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st;

    public static final int RGB_COUNT = 3;

    public static int homeCount;

    public static int[][] RGBList;

    public static int minPrice;

    public static void main(String[] args) throws IOException {
        // 1. 집의 수 N 을 저장하는 homeCount
        homeCount = Integer.parseInt(br.readLine().trim());

        // 2. 각 집에 칠할 수 있는 RGB 비용을 저장하는 RGBList
        RGBList = new int[homeCount][RGB_COUNT];

        for(int idx = 0; idx < homeCount; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            for(int rgbIdx = 0; rgbIdx < 3; rgbIdx++) {
                RGBList[idx][rgbIdx] = Integer.parseInt(st.nextToken());
            }
        }

        // 3. N-1 번째 집에서 시작해 +1 번째의 최적 선택을 하며 누적합의 최솟값 갱신
        for(int idx = homeCount-2; idx >= 0; idx--) {
            // 3-2. rgbIdx 0 ~ 3 까지 해당 인덱스 제외하고 최소값을 누적합
            for(int rgbIdx = 0; rgbIdx < RGB_COUNT; rgbIdx++) {
                RGBList[idx][rgbIdx] = RGBList[idx][rgbIdx]
                        + Math.min(RGBList[idx+1][(rgbIdx+1) % 3], RGBList[idx+1][(rgbIdx+2) % 3]);
            }
        }

        // 4. 비용의 최솟값을 저장하는 minPrice
        minPrice = Math.min(RGBList[0][0], Math.min(RGBList[0][1], RGBList[0][2]));
        System.out.println(minPrice);
    }
}