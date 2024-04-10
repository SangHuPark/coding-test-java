import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [ inputTestCase() ]
 * 1. 4 개 톱니바퀴의 정보를 입력받는다.
 * 2. 회전 횟수를 입력받는다.
 * 3. 톱니바퀴의 번호와 방향을 입력받는다.
 *
 * [ main() ]
 * 4. 회전 횟수만큼 반복하여 톱니바퀴의 번호와 방향을 전달한다.
 *  [ setTurnDirection() ]
 *  4-1. 현재 톱니바퀴보다 왼쪽에 있는 톱니바퀴들을 검사한다.
 *     4-1-1. 현재 톱니바퀴의 6번 인덱스와 이전 톱니바퀴의 2번 인덱스가 다르다면 회전한다.
 *  4-2. 현재 톱니바퀴보다 오른쪽에 있는 톱니바퀴들을 검사한다.
 *      4-2-1. 현재 톱니바퀴의 2번 인덱스와 다음 톱니바퀴의 6번 인덱스가 다르다면 회전한다.
 * 5. 결정한 회전 방향으로 회전한다.
 *  [ turnWheel() ]
 *  5-1. 현재 톱니바퀴의 0번 인덱스와 마지막 인덱스를 저장한다.
 *  5-2. 시계방향 회전(1)
 *      5-2-1. 마지막 인덱스를 첫번째 인덱스에 저장한다.
 *      5-2-2. 마지막 인덱스부터 이전 인덱스 값을 현재 위치로 당긴다.
 *  5-3. 반시계방향 회전(-1)
 *      5-3-1. 첫번째 인덱스는 마지막 인덱스에 저장한다.
 *      5-3-2. 첫번째 인덱스부터 다음 인덱스 값을 현재 위치로 당긴다.
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static final int WHEEL_COUNT = 4;
    static final int WHEEL_LENGTH = 8;
    static final int HEAD_IDX = 2;
    static final int TAIL_IDX = 6;
    static final int FIRST_IDX = 0;
    static final int LAST_IDX = 7;

    static int[][] wheelList;
    static int turnCount;
    static int[] targetWheelList;
    static int[] targetDirList;

    static int[] turnWheelDirList;

    static int resultScore;

    public static void setBeforeWheelDirection(int wheel, int dir) {
        if (wheel == 0) {
            return;
        }

        if (wheelList[wheel][TAIL_IDX] != wheelList[wheel - 1][HEAD_IDX]) {
            int nextDir = dir * (-1);
            turnWheelDirList[wheel - 1] = nextDir;
            setBeforeWheelDirection(wheel - 1, nextDir);
        }
    }

    public static void setNextWheelDirection(int wheel, int dir) {
        if (wheel == WHEEL_COUNT-1) {
            return;
        }

        if (wheelList[wheel][HEAD_IDX] != wheelList[wheel + 1][TAIL_IDX]) {
            int nextDir = dir * (-1);
            turnWheelDirList[wheel + 1] = nextDir;
            setNextWheelDirection(wheel + 1, nextDir);
        }
    }

    public static void turnWheel() {
        for (int wheelIdx = 0; wheelIdx < WHEEL_COUNT; wheelIdx++) {
            if(turnWheelDirList[wheelIdx] == 0) continue;

            int firstIdxNum = wheelList[wheelIdx][FIRST_IDX];
            int lastIdxNum = wheelList[wheelIdx][LAST_IDX];
            if (turnWheelDirList[wheelIdx] == 1) {
                for (int idx = WHEEL_LENGTH-1; idx > FIRST_IDX; idx--) {
                    wheelList[wheelIdx][idx] = wheelList[wheelIdx][idx-1];
                }
                wheelList[wheelIdx][FIRST_IDX] = lastIdxNum;
            }
            else if (turnWheelDirList[wheelIdx] == -1) {
                for (int idx = 1; idx < WHEEL_LENGTH; idx++) {
                    wheelList[wheelIdx][idx-1] = wheelList[wheelIdx][idx];
                }
                wheelList[wheelIdx][LAST_IDX] = firstIdxNum;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        inputTestCase();

        for (int targetIdx = 0; targetIdx < turnCount; targetIdx++) {
            int wheel = targetWheelList[targetIdx];
            int dir = targetDirList[targetIdx];

            turnWheelDirList = new int[WHEEL_COUNT];
            turnWheelDirList[wheel] = dir;
            setBeforeWheelDirection(wheel, dir);
            setNextWheelDirection(wheel, dir);
            turnWheel();
        }

        resultScore = 0;
        for (int wheelIdx = 0; wheelIdx < WHEEL_COUNT; wheelIdx++) {
            if (wheelList[wheelIdx][FIRST_IDX] == 1)
                resultScore += 1 << wheelIdx;
        }

        System.out.println(resultScore);
    }

    public static void inputTestCase() throws IOException {
        wheelList = new int[WHEEL_COUNT][WHEEL_LENGTH];
        for (int wheelIdx = 0; wheelIdx < WHEEL_COUNT; wheelIdx++) {
            String wheelInfo = br.readLine().trim();
            for (int idx = 0; idx < WHEEL_LENGTH; idx++) {
                wheelList[wheelIdx][idx] = wheelInfo.charAt(idx) - '0';
            }
        }

        turnCount = Integer.parseInt(br.readLine().trim());
        targetWheelList = new int[turnCount];
        targetDirList = new int[turnCount];
        for (int turnIdx = 0; turnIdx < turnCount; turnIdx++) {
            st = new StringTokenizer(br.readLine().trim());
            targetWheelList[turnIdx] = Integer.parseInt(st.nextToken())-1;
            targetDirList[turnIdx] = Integer.parseInt(st.nextToken());
        }
    }
}