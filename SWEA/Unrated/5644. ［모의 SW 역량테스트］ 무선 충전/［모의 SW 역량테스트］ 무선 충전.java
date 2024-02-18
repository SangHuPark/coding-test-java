import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * [조건]
 * - 지도의 크기는 10x10 고정
 * - 총 이동 시간 M (20 <= M <= 100)
 * - BC 의 개수 A (1 <= A <= 8)
 * - BC 의 충전 범위 C (1 <= C <= 4)
 * - BC 의 성능 P (10 <= P <= 500)
 *
 * 1. 총 이동 시간 M 과 BC 의 개수 A 를 저장하는 totalMoveCount, batteryCount
 * 2. 사용자 A, B 의 이동 정보를 저장하는 userAMoveList, userBMoveList
 * 3. 각 AP 의 정보를 저장하는 Battery 형 1차원 배열 batteryList
 *  3-1. AP 의 좌표와 범위, 성능을 저장하는 Battery 클래스
 * 4. 각 배터리 별 배터리 영역을 저장하는 3차원 배열 chargeArea
 * 5. 사용자를 이동하며 배터리 충전량의 누적 합의 최대 계산
 * 6. 배터리 영역을 만들 때의 방향을 저장하는 BATTERY_DELTA_ROW, BATTERY_DELTA_COL
 * 7. 사용자 A, B 의 이동 방향을 저장하는 USER_DELTA_ROW, USER_DELTA_COL
 *  7-1. A 는 이동하지 않음, 상, 우, 하, 좌
 */
public class Solution {
    static class Battery {
        int row;
        int col;
        int range;
        int power;

        public Battery(int row, int col, int range, int power) {
            this.row = row;
            this.col = col;
            this.range = range;
            this.power = power;
        }
    }

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer st;

    public static int totalMoveCount;
    public static int batteryCount;

    public static int[] userAMoveList;
    public static int[] userBMoveList;

    public static int[] userAPosition;
    public static int[] userBPosition;

    public static Battery[] batteryList;
    public static boolean[][][] chargeArea;

    public static final int[] BATTERY_DELTA_ROW = {-1, 0, 1, 0};
    public static final int[] BATTERY_DELTA_COL = {0, 1, 0, -1};

    public static final int[] USER_DELTA_ROW = {0, -1, 0, 1, 0};
    public static final int[] USER_DELTA_COL = {0, 0, 1, 0, -1};

    public static int totalCharge;

    public static void batteryArea(Battery curBattery, int batteryIdx) {
        Deque<int[]> visitQueue = new ArrayDeque<>();
        visitQueue.offer(new int[] {curBattery.row, curBattery.col}); // 현재 배터리 위치부터 탐색 시작
        chargeArea[batteryIdx][curBattery.row][curBattery.col] = true; // 영역 표시

        // 방문 처리
        boolean[][] visit = new boolean[10][10];
        visit[curBattery.row][curBattery.col] = true;

        // 벗어난 영역인지 체크
        boolean areaStatus = false;
        while(!visitQueue.isEmpty()) {
            int[] curPosition = visitQueue.poll();
            int curRow = curPosition[0];
            int curCol = curPosition[1];

            for(int deltaIdx = 0; deltaIdx < 4; deltaIdx++) {
                int newRow = curRow + BATTERY_DELTA_ROW[deltaIdx];
                int newCol = curCol + BATTERY_DELTA_COL[deltaIdx];

                if(isArrange(newRow, newCol)) {
                    continue;
                }

                // 현재 배터리의 range 를 넘었다면 접속 불가
                if(isCharge(newRow, newCol, curBattery)) {
                    areaStatus = true;
                    break;
                }

                // 범위 안이라면
                chargeArea[batteryIdx][newRow][newCol] = true;
                visit[newRow][newCol] = true;
                visitQueue.offer(new int[] { newRow, newCol }); // 방문 찜
            }

            if(areaStatus) {
                break;
            }
        }
    }

    public static boolean isCharge(int userRow, int userCol, Battery curBattery) {
        return Math.abs(userRow - curBattery.row) + Math.abs(userCol - curBattery.col) > curBattery.range;
    }

    public static boolean isArrange(int row, int col) {
        return row < 0 || col < 0 || row > 9 || col > 9;
    }

    public static void main(String[] args) throws IOException {
        int testCase = Integer.parseInt(br.readLine().trim());

        for(int tc = 1; tc <= testCase; tc++) {
            st = new StringTokenizer(br.readLine().trim());
            totalMoveCount = Integer.parseInt(st.nextToken());
            batteryCount = Integer.parseInt(st.nextToken());

            // 2. 사용자 A, B 의 이동 정보를 저장하는 userAMoveList, userBMoveList
            st = new StringTokenizer(br.readLine().trim());
            userAMoveList = new int[totalMoveCount];
            for(int idx = 0; idx < totalMoveCount; idx++) {
                userAMoveList[idx] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine().trim());
            userBMoveList = new int[totalMoveCount];
            for(int idx = 0; idx < totalMoveCount; idx++) {
                userBMoveList[idx] = Integer.parseInt(st.nextToken());
            }

            // 3. 각 AP 의 정보를 저장하는 Battery 형 1차원 배열 batteryList
            batteryList = new Battery[batteryCount];
            for(int idx = 0; idx < batteryCount; idx++) {
                // 문제의 row, col 은 반대
                st = new StringTokenizer(br.readLine().trim());
                int col = Integer.parseInt(st.nextToken()) - 1;
                int row = Integer.parseInt(st.nextToken()) - 1;
                int range = Integer.parseInt(st.nextToken());
                int power = Integer.parseInt(st.nextToken());
                batteryList[idx] = new Battery(row, col, range, power);
            }

            // 4. 각 배터리 별 배터리 영역을 저장하는 3차원 배열 chargeArea
            chargeArea = new boolean[batteryCount][10][10];
            // BFS 로 영역 만들기
            for(int batteryIdx = 0; batteryIdx < batteryCount; batteryIdx++) {
                batteryArea(batteryList[batteryIdx], batteryIdx);
            }

            // 충전 누적합의 최대값 계산
            totalCharge = 0;
            List<Integer> userABattery = new ArrayList<>(); // 현재 사용자 A 가 접속 가능한 배터리 저장
            List<Integer> userBBattery = new ArrayList<>(); // 현재 사용자 B 가 접속 가능한 배터리 저장
            // 사용자 위치 초기화
            userAPosition = new int[] {0, 0};
            userBPosition = new int[] {9, 9};
            for(int moveIdx = 0; moveIdx <= totalMoveCount; moveIdx++) {
                // 초기화
                userABattery.clear();
                userBBattery.clear();

                // 각 사용자가 접속 가능한 배터리 찾기
                for(int batteryIdx = 0; batteryIdx < batteryCount; batteryIdx++) {
                    if(chargeArea[batteryIdx][userAPosition[0]][userAPosition[1]]) {
                        userABattery.add(batteryIdx);
                    }

                    if(chargeArea[batteryIdx][userBPosition[0]][userBPosition[1]]) {
                        userBBattery.add(batteryIdx);
                    }
                }

                // 충전량 계산
                int curCharge = 0;

                // 사용자 A 는 접속 가능한 배터리가 없고 B 는 있는 경우부터 검사
                if(userABattery.isEmpty() && !userBBattery.isEmpty()) {
                    for(int batteryBIdx : userBBattery) {
                        if(curCharge < batteryList[batteryBIdx].power) {
                            curCharge = batteryList[batteryBIdx].power;
                        }
                    }
                } else if(!userABattery.isEmpty() && userBBattery.isEmpty()) {
                    for(int batteryAIdx : userABattery) {
                        if(curCharge < batteryList[batteryAIdx].power) {
                            curCharge = batteryList[batteryAIdx].power;
                        }
                    }
                } else if(!userABattery.isEmpty() && !userBBattery.isEmpty()) {
                    for(int batteryAIdx : userABattery) {
                        for(int batteryBIdx : userBBattery) {
                            // 두 사용자가 같은 배터리의 영역에 포함된 경우
                            if(batteryAIdx == batteryBIdx) {
                                // 한쪽에
                                if(curCharge < batteryList[batteryAIdx].power) {
                                    curCharge = batteryList[batteryAIdx].power;
                                    continue;
                                }
                            } // 각자 다른 배터리 영역에 있다면
                            else if (curCharge < batteryList[batteryAIdx].power + batteryList[batteryBIdx].power) {
                                curCharge = batteryList[batteryAIdx].power + batteryList[batteryBIdx].power;
                            }
                        }
                    }
                }

                totalCharge += curCharge;

                // 마지막 충전량까지 합하고 종료
                if(moveIdx == totalMoveCount) {
                    break;
                }

                // 사용자 위치 이동
                userAPosition[0] += USER_DELTA_ROW[userAMoveList[moveIdx]];
                userAPosition[1] += USER_DELTA_COL[userAMoveList[moveIdx]];

                userBPosition[0] += USER_DELTA_ROW[userBMoveList[moveIdx]];
                userBPosition[1] += USER_DELTA_COL[userBMoveList[moveIdx]];
            }

            sb.append("#").append(tc).append(" ").append(totalCharge).append("\n");
        }

        System.out.println(sb);
    }
}