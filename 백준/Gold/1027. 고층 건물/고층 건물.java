import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int buildingCount;
    static int[] buildings;

    public static void main(String[] args) throws IOException {
        init();

        int max = 0;
        for (int x = 1; x <= buildingCount; x++) {
            int count = 0;
            double before = 0; // 이전 기울기 저장

            // 왼쪽 건물과 기울기 비교
            for (int leftX = x - 1; leftX > 0; leftX--) {
                double current = (double) (buildings[x] - buildings[leftX]) / (x - leftX); // 현재 기울기 저장

                // 이전 기울기보다 작을 때만 볼 수 있음
                if (leftX == x - 1 || before > current) {
                    count++;
                    before = current;
                }
            }

            // 오른쪽 건물과 기울기 비교
            for (int rightX = x + 1; rightX <= buildingCount; rightX++) {
                double current = (double) (buildings[x] - buildings[rightX]) / (x - rightX); // 현재 기울기 저장

                // 이전 기울기보다 클 때만 볼 수 있음
                if (rightX == x + 1 || before < current) {
                    count++;
                    before = current;
                }
            }

            max = Math.max(max, count);
        }
        System.out.println(max);
    }

    public static void init() throws IOException {
        buildingCount = Integer.parseInt(br.readLine().trim());

        st = new StringTokenizer(br.readLine().trim());
        buildings = new int[buildingCount+1];
        for (int idx = 1; idx <= buildingCount; idx++) {
            buildings[idx] = Integer.parseInt(st.nextToken());
        }
    }
}