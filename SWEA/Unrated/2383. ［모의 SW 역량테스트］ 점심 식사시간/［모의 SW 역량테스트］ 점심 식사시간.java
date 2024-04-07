import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * [ main() ]
 * 1. 테스트케이스 개수를 입력받는다.
 *
 * [ inputTestCase() ]
 * 1. 방의 정보를 입력받는다.
 *  1-1. 계단의 위치를 배열에 저장한다.
 *  1-2. 사람의 개수를 카운팅한다.
 *
 * [ stairsPowerSet() ]
 * - 내려갈 계단의 종류로 부분집합을 생성한다.
 * 1. 2개를 모두 선택하면 종료
 *  1-1. 사람의 위치를 찾아 리스트에 저장한다.
 *      1-1-1. 계단까지의 거리, 내려갈 계단의 번호(1 or 2), 해당 계단의 길이
 *
 * [ downStairs() ]
 * - 부분집합에서 계단을 선택한 모든 사람의 계단 시뮬레이션을 진행한다.
 * 1. 계단 입구에 위치한 사람부터 처리
 *  1-1. dis 가 0 이면 계단 입구에 도착한 것
 *  1-2. 처음 도착한 상태라면 대기
 *  1-3. 계단을 내려가는 중이면 내려간 횟수 감소
 *  1-4. 계단을 내려간 횟수가 0 이면 나가기
 *      1-4-1. 내려간 계단 종류에 나간 횟수 카운팅
 *      1-4-2. 리스트에서 제거한다.
 * 2. 계단 입구로 이동
 *  2-1. dis 가 0 보다 크면 아직 계단 입구에 도착한 것이 아니다.
 *      2-1-1. dis 를 1 감소
 *  2-2. 계단 입구에 대기하던 사람이면서 현재 계단에 내려갈 자리가 있다면 내려가기
 *      2-2-1. 해당 계단의 사람 1 증가
 *      2-2-2. 해당 사람의 계단 내려간 횟수 감소
 * 3. 나간 사람이 있다면 각 계단에 내려가고 있던 사람에서 나간 사람 수 감소
 * 4. 모두 나갈 때까지 진행된 횟수의 최솟값 갱신
 */
public class Solution {
    static class Human {
        int dis;
        int stairsNum;
        int downCount;

        public Human(int dis, int stairsNum, int downCount) {
            this.dis = dis;
            this.stairsNum = stairsNum;
            this.downCount = downCount;
        }
    }

    static class Stairs {
        int row;
        int col;
        int stairsLeng;
        int humanCount;

        public Stairs(int row, int col, int stairsLeng, int humanCount) {
            this.row = row;
            this.col = col;
            this.stairsLeng = stairsLeng;
            this.humanCount = humanCount;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static final int STAIRS_COUNT = 2;

    static int mapSize;
    static int[][] map;
    static int humanCount;
    static List<Human> humanList;
    static Stairs[] stairsList;

    // 부분집합 생성 시 사용
    static boolean[] selectList;

    static int minDownCount;

    public static void selectStairsPowerSet(int selectHumanIdx) {
        if(selectHumanIdx == humanCount) {
            humanList = new LinkedList<>();
            int selectIdx = 0;

            for (int row = 0; row < mapSize; row++) {
                for (int col = 0; col < mapSize; col++) {
                    // 첫번째 사람부터 selectList 에 선택한 계단의 종류가 담겨있다.
                    if(map[row][col] == 1) {
                        // 0 번 계단 선택한 경우 true
                        if(selectList[selectIdx++]) {
                            int dis = Math.abs(stairsList[0].row - row) + Math.abs(stairsList[0].col - col);
                            // 계단 입구에서도 일단 기다려야 하므로 거리 + 1 저장
                            humanList.add(new Human((dis + 1), 0, stairsList[0].stairsLeng));
                        } // 1번 계단 선택한 경우 false
                        else {
                            int dis = Math.abs(stairsList[1].row - row) + Math.abs(stairsList[1].col - col);
                            humanList.add(new Human((dis + 1), 1, stairsList[1].stairsLeng));
                        }
                    }
                }
            }

            // 모든 사람이 선택한 계단을 세팅하고 계단 내려가기 시작
            downStairs();

            return;
        }

        selectList[selectHumanIdx] = true;
        selectStairsPowerSet(selectHumanIdx + 1);

        selectList[selectHumanIdx] = false;
        selectStairsPowerSet(selectHumanIdx + 1);
    }

    public static void downStairs() {
        int downCount = 0;
        while(!humanList.isEmpty()) {
            downCount++;
            int firstStairsHumanCount = 0;
            int secondStairsHumanCount = 0;

            //
            for (int humanIdx = 0; humanIdx < humanList.size(); humanIdx++) {
                Human human = humanList.get(humanIdx);

                if(human.dis == 0) {
                    if(human.downCount == stairsList[human.stairsNum].stairsLeng)
                        continue;

                    if((human.downCount > 0) && (human.downCount < stairsList[human.stairsNum].stairsLeng)) {
                        human.downCount--;
                    }

                    if(human.downCount == 0) {
                        if(human.stairsNum == 0)
                            firstStairsHumanCount++;
                        else
                            secondStairsHumanCount++;

                        humanList.remove(humanIdx);
                        humanIdx--;
                    }
                }
            }

            for(int humanIdx = 0; humanIdx < humanList.size(); humanIdx++) {
                Human human = humanList.get(humanIdx);

                if(human.dis > 0) {
                    human.dis--;
                }
                else if ((human.dis == 0) && (human.downCount == stairsList[human.stairsNum].stairsLeng) && (stairsList[human.stairsNum].humanCount < 3)) {
                    stairsList[human.stairsNum].humanCount++;
                    human.downCount--;
                }
            }

            stairsList[0].humanCount -= firstStairsHumanCount;
            stairsList[1].humanCount -= secondStairsHumanCount;
        }

        minDownCount = Math.min(minDownCount, downCount);
    }

    public static void main(String[] args) throws IOException {
        int testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= testCase; tc++) {
            inputTestCase();

            minDownCount = Integer.MAX_VALUE;
            selectList = new boolean[humanCount];
            selectStairsPowerSet(0);

            sb.append("#").append(tc).append(" ").append(minDownCount).append("\n");
        }

        System.out.println(sb);
    }

    public static void inputTestCase() throws IOException {
        mapSize = Integer.parseInt(br.readLine().trim());

        map = new int[mapSize][mapSize];
        stairsList = new Stairs[STAIRS_COUNT];

        humanCount = 0;
        int stairsIdx = 0;
        for (int row = 0; row < mapSize; row++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int col = 0; col < mapSize; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());

                if(map[row][col] == 1) {
                    humanCount++;
                }
                else if(map[row][col] > 1) {
                    stairsList[stairsIdx++] = new Stairs(row, col, map[row][col], 0);
                }
            }
        }
    }
}