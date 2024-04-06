import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * [ main() ]
 * 1. 테스트케이스 개수를 입력받는다.
 * 2. 가장 높은 모든 봉우리에서 출발한다.
 * 3. 최대 등산로 길이를 출력한다.
 *
 * [ inputTestCase() ]
 * 1. 지도의 한 변의 길이와 최대 공사 가능 깊이를 입력받는다.
 * 2. 지도 정보를 입력받는다.
 *  2-1. 출발할 가장 높은 봉우리의 높이를 저장한다.
 *
 * [ getStartPosition() ]
 * 1. 가장 높은 봉우리를 가진 위치를 찾아 저장한다.
 * 2. 각 봉우리의 번호를 인덱스로 3차원 배열에 저장한다.
 *  2-1. copyArray 함수를 만든다.
 *
 * [ makeMountainLoad() ]
 * 1. 첫 번째 봉우리부터 등산로를 만들며 DFS 진행
 * 2. 4방으로 높이가 낮은 지형으로 재귀 호출
 * 3. 높이가 높다면 현재 위치의 봉우리보다 작게 만들고 이동
 *  3-1. 최대 공사 가능 깊이로 부족하다면 진행하지 않는다.
 * 4. 공사를 하고 이동했다면 사용 여부 전달
 * 5. 4방으로 높이가 낮은 지형이 없다면 종료
 *  5-1. 현재 등산로 길이 저장
 * 6. 방문 체크 필요
 */
public class Solution {
    static class Peak {
        int row;
        int col;
        int loadLength;

        public Peak(int row, int col, int loadLength) {
            this.row = row;
            this.col = col;
            this.loadLength = loadLength;
        }
    }

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    public static int mapSize, maxDigDepth;
    public static int[][] map;
    public static int maxPeakHeight;

    public static List<Peak> peakList;
    public static int maxLoadLength;

    public static final int DELTA_LENG = 4;
    public static final int[] DELTA_ROW = {0, 1, 0, -1};
    public static final int[] DELTA_COL = {1, 0, -1, 0};

    public static void makeMountainLoad(Peak peak, boolean[][] visited, boolean digFlag) {

        visited[peak.row][peak.col] = true;

        for (int deltaIdx = 0; deltaIdx < DELTA_LENG; deltaIdx++) {
            int nextRow = peak.row + DELTA_ROW[deltaIdx];
            int nextCol = peak.col + DELTA_COL[deltaIdx];

            // 인덱스를 벗어나면 x, 방문한 곳이면 x
            if(isArrange(nextRow, nextCol) || visited[nextRow][nextCol]) {
                continue;
            }

            // 다음 자리의 높이가 더 높거나 같고 지형을 깎으면 갈 수 있을 때
            if(map[nextRow][nextCol] >= map[peak.row][peak.col] && map[nextRow][nextCol] - maxDigDepth < map[peak.row][peak.col]) {
                // 아직 지형을 깎지 않았다면
                if (!digFlag) {
                    // 1씩 깎아보며 진행
                    for (int digDepth = 1; digDepth <= maxDigDepth; digDepth++) {
                        if(map[nextRow][nextCol] - digDepth < map[peak.row][peak.col]) {
                            map[nextRow][nextCol] -= digDepth;
                            makeMountainLoad(new Peak(nextRow, nextCol, peak.loadLength+1), visited, true);
                            map[nextRow][nextCol] += digDepth;
                            break;
                        }
                    }
                }
            } // 아니라면 높이가 낮을 때만 이동 가능
            else if(map[nextRow][nextCol] < map[peak.row][peak.col]) {
                makeMountainLoad(new Peak(nextRow, nextCol, peak.loadLength+1), visited, digFlag);
            }

        }

        visited[peak.row][peak.col] = false;
        // 4방을 빠져나오면 종료
        maxLoadLength = Math.max(maxLoadLength, peak.loadLength);
    }

    public static boolean isArrange(int row, int col) {
        return row < 0 || col < 0 || row >= mapSize || col >= mapSize;
    }

    public static void getStartPosition() {
        peakList = new ArrayList<>();

        for (int row = 0; row < mapSize; row++) {
            for (int col = 0; col < mapSize; col++) {
                // 1. 가장 높은 봉우리를 가진 위치를 찾아 저장한다.
                if(map[row][col] == maxPeakHeight) {
                    // 2. 각 봉우리의 번호를 인덱스로 list에 저장한다.
                    peakList.add(new Peak(row, col, 1));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // 1. 테스트케이스 개수를 입력받는다.
        int testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= testCase; tc++) {
            inputTestCase();

            // 가장 높은 봉우리 지점들을 찾는다.
            getStartPosition();

            maxLoadLength = 0;
            // 2. 가장 높은 모든 봉우리에서 출발한다.
            for (int peakIdx = 0; peakIdx < peakList.size(); peakIdx++) {
                makeMountainLoad(peakList.get(peakIdx), new boolean[mapSize][mapSize], false);
            }

            // 3. 최대 등산로 길이를 출력한다.
            sb.append("#")
                    .append(tc)
                    .append(" ")
                    .append(maxLoadLength)
                    .append("\n");
        }
        System.out.println(sb);
    }

    public static void inputTestCase() throws IOException {
        // 1. 지도의 한 변의 길이와 최대 공사 가능 깊이를 입력받는다.
        st = new StringTokenizer(br.readLine().trim());
        mapSize = Integer.parseInt(st.nextToken());
        maxDigDepth = Integer.parseInt(st.nextToken());

        map = new int[mapSize][mapSize];
        maxPeakHeight = 0;

        // 2. 지도 정보를 입력받는다.
        for (int row = 0; row < mapSize; row++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int col = 0; col < mapSize; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());

                // 2-1. 출발할 가장 높은 봉우리의 높이를 저장한다.
                maxPeakHeight = Math.max(maxPeakHeight, map[row][col]);
            }
        }
    }
}