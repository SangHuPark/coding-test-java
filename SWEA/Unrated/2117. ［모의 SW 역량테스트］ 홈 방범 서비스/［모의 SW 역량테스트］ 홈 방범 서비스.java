import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * [ main() ]
 * 1. 테스트 케이스 개수를 입력받는다.
 *
 * [ inputTestCase() ]
 * 2. 맵의 크기와 집이 지불할 수 있는 비용을 입력받는다.
 * 3. 맵의 정보를 입력받는다.
 *
 * [ getCost() ]
 * 4. [0, 0] 부터 맵을 돌며 모든 좌표에 가능한 범위를 모두 구한다.
 *  4-1. 마름모 범위 내로 집이 있다면 집이 지불할 수 있는 비용을 현재 비용에서 제외한다.
 *      4-1-1. 지불한 집의 개수를 카운팅한다.
 *  4-2. 비용이 0 이하라면 손해를 보지 않았으므로 지불한 집의 최대값을 갱신한다.
 */
public class Solution {
    static class Home {
        int row;
        int col;

        public Home(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static final int HOME = 1;

    static int mapSize, homeCost;
    static int[][] map;
    static List<Home> homeList;

    static int maxHomeCount;

    public static void getCost() {
        for (int row = 0; row < mapSize; row++) {
            for (int col = 0; col < mapSize; col++) {
                for (int range = 0; range <= 2 * (mapSize-1); range++) {
                    int curCost = range * range + (range - 1) * (range - 1);
                    int homeCount = 0;

                    for (Home home : homeList) {
                        if (range > Math.abs(home.row - row) + Math.abs(home.col - col)) {
                            curCost -= homeCost;
                            homeCount++;
                        }
                    }

                    if (curCost <= 0)
                        maxHomeCount = Math.max(maxHomeCount, homeCount);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        int testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= testCase; tc++) {
            inputTestCase();

            maxHomeCount = 0;
            getCost();

            sb.append("#").append(tc).append(" ").append(maxHomeCount).append("\n");
        }

        System.out.println(sb);
    }

    public static void inputTestCase() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        mapSize = Integer.parseInt(st.nextToken());
        homeCost = Integer.parseInt(st.nextToken());

        homeList = new ArrayList<>();
        map = new int[mapSize][mapSize];
        for (int row = 0; row < mapSize; row++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int col = 0; col < mapSize; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
                if (map[row][col] == HOME)
                    homeList.add(new Home(row, col));
            }
        }
    }
}
