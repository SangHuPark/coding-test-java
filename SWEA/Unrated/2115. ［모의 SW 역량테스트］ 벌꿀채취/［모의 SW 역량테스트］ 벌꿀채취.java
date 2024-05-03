import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 1. 테스트 케이스 개수
 * 2. 맵의 크기, 추출할 칸의 개수, 최대 얻을 수 있는 양
 * 3. row 를 모두 돌며 col = 0 ~ col < mapSize-추출할 칸의 개수 로 시도
 *  3-1. 칸 자르고 각 인원별로 자른 칸의 부분집합 생성
 * 4. 생성한 부분집합이 최대 얻을 수 있는 양보다 같거나 작은 경우에만 계산
 *  4-1. 각 벌꿀 양의 제곱의 합이 가장 큰 경우 구해서 각각 저장
 *  4-2. 서로 가장 큰 경우의 합의 최대값 구하기
 */
public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int mapSize, sliceSize, limit;
    static int map[][];

    static int[] humanA;
    static int[] humanB;

    static boolean[] selectList;
    static int aTotalMax;
    static int bTotalMax;

    static int maxCount;

    public static void aRecur(int selectIdx, int count) {
        if (count > limit)
            return;

        if (selectIdx == sliceSize) {
            int curTotal = 0;
            for (int idx = 0; idx < sliceSize; idx++) {
                if (selectList[idx]) {
                    curTotal += humanA[idx] * humanA[idx];
                }
            }

            aTotalMax = Math.max(aTotalMax, curTotal);

            return;
        }

        selectList[selectIdx] = true;
        aRecur(selectIdx + 1, count + humanA[selectIdx]);

        selectList[selectIdx] = false;
        aRecur(selectIdx + 1, count);
    }

    public static void bRecur(int selectIdx, int count) {
        if (count > limit)
            return;

        if (selectIdx == sliceSize) {
            int curTotal = 0;
            for (int idx = 0; idx < sliceSize; idx++) {
                if (selectList[idx]) {
                    curTotal += humanB[idx] * humanB[idx];
                }
            }

            bTotalMax = Math.max(bTotalMax, curTotal);

            return;
        }

        selectList[selectIdx] = true;
        bRecur(selectIdx + 1, count + humanB[selectIdx]);

        selectList[selectIdx] = false;
        bRecur(selectIdx + 1, count);
    }

    public static void slice() {
        for (int row = 0; row < mapSize; row++) {
            for (int col = 0; col < mapSize-sliceSize+1; col++) {
                boolean[][] visited = new boolean[mapSize][mapSize];

                int aIdx = 0;
                for (int slice = col; slice < col+sliceSize; slice++) {
                    humanA[aIdx++] = map[row][slice];
                    visited[row][slice] = true;
                }

                for (int bRow = 0; bRow < mapSize; bRow++) {
                    for (int bCol= 0; bCol < mapSize-sliceSize+1; bCol++) {
                        int bIdx = 0;
                        for (int bSlice = bCol; bSlice < bCol+sliceSize; bSlice++) {
                            if (visited[bRow][bSlice])
                                continue;

                            humanB[bIdx++] = map[bRow][bSlice];
                        }

                        if (bIdx == sliceSize) {
                            selectList = new boolean[sliceSize];
                            aTotalMax = 0;
                            aRecur(0, 0);

                            selectList = new boolean[sliceSize];
                            bTotalMax = 0;
                            bRecur(0, 0);

                            maxCount = Math.max(maxCount, aTotalMax + bTotalMax);
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        int testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= testCase; tc++) {
            init();

            slice();

            sb.append("#").append(tc).append(" ").append(maxCount).append("\n");
        }

        System.out.println(sb);
    }

    public static void init() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        mapSize = Integer.parseInt(st.nextToken());
        sliceSize = Integer.parseInt(st.nextToken());
        limit = Integer.parseInt(st.nextToken());

        map = new int[mapSize][mapSize];
        for (int row = 0; row < mapSize; row++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int col = 0; col < mapSize; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        humanA = new int[sliceSize];
        humanB = new int[sliceSize];
        maxCount = 0;
    }

}