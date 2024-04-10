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
 * 2. 총 이동 시간, BC 의 개수를 입력받는다.
 * 3. 사용자의 이동 정보를 입력받는다.
 *  3-1. 사용자 A, 사용자 B 배열에 각각 저장한다.
 * 4. BC 의 정보를 저장한다.
 *  4-1. BC 의 row, col, rangeMap, power 를 저장한다.
 *  4-2. BC 의 범위를 각 rangeMap 에 true 로 한다.
 *
 * [ goCharge() ]
 * 5. 두 사용자의 이동 정보를 따라 이동한다.
 *  5-1. 두 사용자의 현재 위치에서 충전할 수 있는지 검사한다.
 *      5-1-1. 모든 BC 를 반복하며 각 사용자가 충전할 BC 의 인덱스, power를 각각 저장한다.
 *      5-1-2. 두 사용자의 위치 모두 true 일 경우
 *          5-1-2-1. 현재 선택한 BC 가 없다면 해당 BC 의 power/2 로 갱신한다.
 *          5-1-2-2. 현재 선택한 BC 가 있다면 해당 BC 의 power/2 의 값과 기존에 선택한 BC 의 power 값을 비교 후 큰 쪽으로 갱신한다.
 *      5-1-3. 한 사용자의 위치만 true 일 경우
 *          5-1-3-1. 현재 선택한 BC 가 없다면 해당 BC 로 갱신한다.
 *          5-1-3-2. 현재 선택한 BC 가 있을 때 현재 사용자는 해당 BC 의 power 와 비교해 큰 BC 로 갱신한다.
 *              5-1-3-2-1. 기존의 BC 가 다른 사용자가 선택한 BC 와 같다면 다른 사용자의 power/2 를 다시 power 로 바꾼다.
 *  5-2. 각 선택한 BC 의 power 를 충전 결과 배열에 저장한다.
 *  5-3. 이동 방향은 X-상-우-하-좌
 *      5-3-1. 사용자 A 는 (1, 1), B 는 (10, 10) 에서 출발한다.
 */
public class Solution {
    static class Charger {
        int row;
        int col;
        boolean[][] rangeMap;
        int power;

        public Charger(int row, int col, boolean[][] rangeMap, int power) {
            this.row = row;
            this.col = col;
            this.rangeMap = rangeMap;
            this.power = power;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static final int MAP_SIZE = 10;
    static final int CHARGER_INFO = 2;
    static final int CHARGER_IDX = 0;
    static final int CHARGER_POWER = 1;
    static int totalMoveTime, chargerCount;
    static final int HUMAN_COUNT = 2;
    static int[] humanA;
    static int[] humanB;
    static Charger[] chargerList;

    static int maxPowerSum;

    static final int[] DELTA_ROW = {0, -1, 0, 1, 0};
    static final int[] DELTA_COL = {0, 0, 1, 0, -1};

    public static void goCharge() {
        int aRow = 1;
        int aCol = 1;
        int bRow = MAP_SIZE;
        int bCol = MAP_SIZE;

        for (int moveIdx = 0; moveIdx <= totalMoveTime; moveIdx++) {
            List<Integer> aConnectChargerList = new ArrayList<>();
            List<Integer> bConnectChargerList = new ArrayList<>();

            for (int chargerIdx = 0; chargerIdx < chargerCount; chargerIdx++) {
                Charger charger = chargerList[chargerIdx];

                if (charger.rangeMap[aRow][aCol]) {
                    aConnectChargerList.add(chargerIdx);
                }

                if (charger.rangeMap[bRow][bCol]) {
                    bConnectChargerList.add(chargerIdx);
                }
            }

            int max = 0;
            int temp = 0;

            if (aConnectChargerList.size() > 0 && bConnectChargerList.size() > 0) {
                for (int aConnectCharger : aConnectChargerList) {
                    for (int bConnectCharger : bConnectChargerList) {
                        temp = 0;
                        if (aConnectCharger == bConnectCharger) {
                            temp = chargerList[aConnectCharger].power;
                        } else {
                            temp += chargerList[aConnectCharger].power;
                            temp += chargerList[bConnectCharger].power;
                        }
                        max = Math.max(max, temp);
                    }
                }
            } else if (aConnectChargerList.size() > 0) {
                for (int aConnectCharger : aConnectChargerList) {
                    if (max < chargerList[aConnectCharger].power) {
                        max = chargerList[aConnectCharger].power;
                    }
                }
            } else if (bConnectChargerList.size() > 0) {
                for (int bConnectCharger : bConnectChargerList) {
                    if (max < chargerList[bConnectCharger].power) {
                        max = chargerList[bConnectCharger].power;
                    }
                }
            }
            maxPowerSum += max;
            if (moveIdx < totalMoveTime) {
                aRow = aRow + DELTA_ROW[humanA[moveIdx]];
                aCol = aCol + DELTA_COL[humanA[moveIdx]];
                bRow = bRow + DELTA_ROW[humanB[moveIdx]];
                bCol = bCol + DELTA_COL[humanB[moveIdx]];
            }
        }
    }

    public static void main(String[] args) throws IOException {
        int testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= testCase; tc++) {
            inputTestCase();

            maxPowerSum = 0;
            goCharge();

            sb.append("#").append(tc).append(" ").append(maxPowerSum).append("\n");
        }

        System.out.println(sb);
    }

    public static void inputTestCase() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        totalMoveTime = Integer.parseInt(st.nextToken());
        chargerCount = Integer.parseInt(st.nextToken());

        humanA = new int[totalMoveTime];
        humanB = new int[totalMoveTime];
        st = new StringTokenizer(br.readLine().trim());
        for (int idx = 0; idx < totalMoveTime; idx++) {
            humanA[idx] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine().trim());
        for (int idx = 0; idx < totalMoveTime; idx++) {
            humanB[idx] = Integer.parseInt(st.nextToken());
        }

        chargerList = new Charger[chargerCount];
        for (int chargerIdx = 0; chargerIdx < chargerCount; chargerIdx++) {
            st = new StringTokenizer(br.readLine().trim());
            int inputCol = Integer.parseInt(st.nextToken());
            int inputRow = Integer.parseInt(st.nextToken());
            int range = Integer.parseInt(st.nextToken());
            int power = Integer.parseInt(st.nextToken());

            boolean[][] rangeMap = new boolean[MAP_SIZE+1][MAP_SIZE+1];
            for(int row = 1; row <= MAP_SIZE; row++) {
                for (int col = 1; col <= MAP_SIZE; col++) {
                    int dis = Math.abs(inputRow - row) + Math.abs(inputCol - col);
                    if(dis <= range) {
                        rangeMap[row][col] = true;
                    }
                }
            }

            chargerList[chargerIdx] = new Charger(inputRow, inputCol, rangeMap, power);
        }
    }
}
