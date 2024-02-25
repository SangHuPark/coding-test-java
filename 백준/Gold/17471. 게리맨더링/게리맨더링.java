import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 1. 구역의 개수 N 을 저장하는 areaCount
 * 2. 구역의 인구를 저장하는 peopleCountList
 * 3. 각 구역의 인접 정보를 저장하는 adjMatrix
 *  3-1. 인접했다면 true 저장
 * 4. 구역 N 개를 부분집합 생성, 현재 선택한 인구 수 누적합 함께 전달
 *  4-1. 선택한 원소의 인구 합과 선택하지 않은 원소의 인구 수 차를 계산
 *  4-2. 인구 차이의 최솟값 계산
 *  4-3. 모든 원소를 선택할 수 없다.
 *  4-4. 두 집합이 서로 인접해 있어야 가능
 */
public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st;

    public static int areaCount;

    public static int[] peopleCountList;

    public static boolean[][] adjMatrix;

    public static boolean[] usedCheck;

    public static int minPopulation;

    public static void peoplePowerSet(int selectIdx, int selectedPopulation) {
        if(selectIdx == areaCount+1) {
            // 두 구역의 원소 저장
            List<Integer> areaAList = new ArrayList<>();
            List<Integer> areaBList = new ArrayList<>();
            for(int idx = 1; idx <= areaCount; idx++) {
                if(usedCheck[idx]) {
                    areaAList.add(idx);
                } else {
                    areaBList.add(idx);
                }
            }

            // 어느 한 쪽이 모두 선택했다면
            if (areaAList.isEmpty() || areaBList.isEmpty()) {
                return;
            }

            // 두 구역 중 한 구역이라도 인접하지 않았다면 종료
            // 첫번째 구역 (선택한 원소) 인접 검사
            // 인접하지 않았다면 종료
            if(!isAdj(areaAList)) {
                return;
            }

            // 두번째 구역(선택하지 않은 원소) 인접 검사
            // 인접하지 않았다면 종료
            if(!isAdj(areaBList)) {
                return;
            }

            int notSelectPopulation = 0;
            for(int idx : areaBList) {
                notSelectPopulation += peopleCountList[idx];
            }

            int resultPopulation = Math.abs(selectedPopulation - notSelectPopulation);
            minPopulation = Math.min(minPopulation, resultPopulation);

            return;
        }

        usedCheck[selectIdx] = true;
        peoplePowerSet(selectIdx+1, selectedPopulation + peopleCountList[selectIdx]);

        usedCheck[selectIdx] = false;
        peoplePowerSet(selectIdx+1, selectedPopulation);
    }

    public static boolean isAdj(List<Integer> areaList) {
        Deque<Integer> adjQueue = new ArrayDeque<>();

        boolean[] visit = new boolean[areaCount + 1];

        adjQueue.offer(areaList.get(0));
        visit[areaList.get(0)] = true;

        int adjCount = 1;
        while (!adjQueue.isEmpty()) {
            int curArea = adjQueue.poll();

            for(int idx = 1; idx <= areaCount; idx++) {
                if(!areaList.contains(idx) || !adjMatrix[curArea][idx] || visit[idx]) {
                    continue;
                }

                adjQueue.offer(idx);
                visit[idx] = true;
                adjCount++;
            }
        }

        return adjCount == areaList.size();
    }

    public static void main(String[] args) throws IOException {
        // 1. 구역의 개수 N 을 저장하는 areaCount
        areaCount = Integer.parseInt(br.readLine().trim());

        // 2. 구역의 인구를 저장하는 peopleCountList
        st = new StringTokenizer(br.readLine().trim());
        peopleCountList = new int[areaCount+1];
        for(int idx = 1; idx <= areaCount; idx++) {
            peopleCountList[idx] = Integer.parseInt(st.nextToken());
        }

        // 3. 각 구역의 인접 정보를 저장하는 adjMatrix
        adjMatrix = new boolean[areaCount+1][areaCount+1];
        for(int row = 1; row <= areaCount; row++) {
            st = new StringTokenizer(br.readLine().trim());
            int colSize = Integer.parseInt(st.nextToken());
            for(int col = 1; col <= colSize; col++) {
                int areaNum = Integer.parseInt(st.nextToken());
                adjMatrix[row][areaNum] = true;
            }
        }

        // 선택한 원소를 저장하는 usedCheck
        usedCheck = new boolean[areaCount+1];

        // 인구 차의 최소값 저장
        minPopulation = Integer.MAX_VALUE;

        peoplePowerSet(1, 0);

        if (minPopulation == Integer.MAX_VALUE) {
            minPopulation = -1;
        }

        System.out.println(minPopulation);
    }
}