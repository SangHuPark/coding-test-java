import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * - 구역은 주어진 크기 NxN
 * - 구역 가장자리는 미생물을 죽이는 약품이 처리되어 있다.
 * - 미생물은 1시간마다 이동방향에 다음 셀로 이동
 * - 약품에 도착하면 미생물의 절반( >> 1)이 죽고 이동방향이 반대로 바뀐다.
 *  -- 홀수라면 소수점 이하는 버린다.
 *  -- 1마리가 살아있는 미생물 군집이면 0 이 되어 사라진다.
 * - 이동 후 미생물 군집이 한 셀에 모이는 경우 미생물끼리 합쳐진다.
 *  -- 미생물이 합쳐진다는 것은 각 미생물 수의 합
 *  -- 합쳐진 미생물은 더 많은 미생물을 가진 군집의 이동방향으로 설정
 *  -- 같은 경우는 주어지지 않는다.
 *  -- 이동 시 교차되는 경우는 합쳐지지 않는다.
 * - 테케 50개, 제한시간 5초
 *
 * [ main() ]
 * 1. 테스트 케이스 개수를 입력받는다.
 *
 * [ inputTestCase() ]
 * 2. 각 테스트 케이스 정보를 입력받는다.
 *  2-1. 맵의 크기, 격리 시간, 미생물 군집의 수를 입력받는다.
 *  2-2. 미생물 군집의 정보를 리스트에 저장한다.
 *      2-2-1. 미생물 군집의 위치, 1차원 인덱스로 변환한 값, 미생물의 수, 이동방향을 입력받는다. (Bug 클래스)
 *
 * [ startMoveBug() ]
 * 3. 격리 시간동안 격리시킨다.
 *  3-1. 모든 미생물 군집을 이동시킨다.
 *      3-1-1.
 */
public class Solution {
    static class Bug implements Comparable<Bug> {
        int row;
        int col;
        int oneDimensionNum;
        int bugCount;
        int deltaIdx;

        public Bug(int row, int col, int oneDimensionNum, int bugCount, int deltaIdx) {
            this.row = row;
            this.col = col;
            this.oneDimensionNum = oneDimensionNum;
            this.bugCount = bugCount;
            this.deltaIdx = deltaIdx;
        }

        @Override
        public int compareTo(Bug o) {
            if (this.oneDimensionNum == o.oneDimensionNum) {
                return o.bugCount - this.bugCount;
            }
            
            return this.oneDimensionNum - o.oneDimensionNum;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int mapSize, limitTime, totalBugCount;
    static List<Bug> bugList;
    
    static int remainBug;
    
    static final int[] DELTA_ROW = {0, -1, 1, 0, 0};
    static final int[] DELTA_COL = {0, 0, 0, -1, 1};
    
    public static void startMoveBug() {
        for(int time = 0; time < limitTime; time++) {
            // 각 미생물 이동
            for (int bugIdx = 0; bugIdx < bugList.size(); bugIdx++) {
                Bug bug = bugList.get(bugIdx);
                bug.row = bug.row + DELTA_ROW[bug.deltaIdx];
                bug.col = bug.col + DELTA_COL[bug.deltaIdx];
                bug.oneDimensionNum = (bug.row * mapSize) + bug.col;
                
                // 약품에 닿으면 절반 죽인 후 방향 전환
                if (isCutLine(bug.row, bug.col)) {
                    bug.bugCount = bug.bugCount >> 1;
                    bug.deltaIdx = changeDelta(bug.deltaIdx);
                    if (bug.bugCount == 0) {
                        bugList.remove(bugIdx);
                        bugIdx--;
                    }
                }
            }

            Collections.sort(bugList);
            
            // 같은 위치에 모인 여러 군집이 있다면 합친다.
            for (int bugIdx = 0; bugIdx < bugList.size()-1; bugIdx++) {
                Bug curBug = bugList.get(bugIdx);
                Bug nextBug = bugList.get(bugIdx + 1);
                
                if(curBug.oneDimensionNum == nextBug.oneDimensionNum) {
                    curBug.bugCount += nextBug.bugCount;
                    bugList.remove(nextBug);
                    bugIdx--;
                }
            }
        }
    }
    
    public static boolean isCutLine(int row, int col) {
        return row == 0 || col == 0 || row == mapSize-1 || col == mapSize-1;
    }
    
    public static int changeDelta(int deltaIdx) {
        // 홀수면 +1, 짝수면 -1
        deltaIdx = (deltaIdx & 1) == 1 ? deltaIdx + 1 : deltaIdx - 1;
        return deltaIdx;
    }

    public static void getRemainBug() {
        remainBug = 0;
        for (int bugIdx = 0; bugIdx < bugList.size(); bugIdx++) {
            remainBug += bugList.get(bugIdx).bugCount;
        }
    }
    
    public static void main(String[] args) throws IOException {
        int testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= testCase; tc++) {
            inputTestCase();
            
            startMoveBug();
            
            getRemainBug();

            sb.append("#").append(tc).append(" ").append(remainBug).append("\n");
        }

        System.out.println(sb);
    }

    public static void inputTestCase() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        mapSize = Integer.parseInt(st.nextToken());
        limitTime = Integer.parseInt(st.nextToken());
        totalBugCount = Integer.parseInt(st.nextToken());

        bugList = new ArrayList<>();
        for (int bugIdx = 0; bugIdx < totalBugCount; bugIdx++) {
            st = new StringTokenizer(br.readLine().trim());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            int bugCount = Integer.parseInt(st.nextToken());
            int deltaIdx = Integer.parseInt(st.nextToken());

            bugList.add(new Bug(row, col, (row * mapSize) + col, bugCount, deltaIdx));
        }
    }
}