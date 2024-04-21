import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [ inputTestCase() ]
 * 1. 땅의 가로 크기 A, 세로 크기 B 를 입력받는다.
 * 2. 로봇의 개수 N, 명령의 개수 M 을 입력받는다.
 * 3. N 개의 로봇의 시작 위치, 방향을 입력받는다.
 *  3-1. 시작 위치에 해당 로봇의 번호를 저장한다.
 * 4. M 개의 명령을 수행한다.
 *  4-1. 해당하는 로봇 번호가 명령의 종류를 횟수만큼 반복한다.
 *      4-1-1. L: 왼쪽으로 회전, R: 오른쪽으로 회전, F: 현재 방향에서 앞으로 한 칸 이동
 *
 * [ turnLeft() ]
 * 5. 명령이 L 인 경우
 *  5-1. 해당 로봇의 번호와 횟수를 전달받아 왼쪽으로 회전한다.
 *  5-2. 현재 방향에 -1
 *      5-2-1. 만약 0 보다 작다면 3 저장
 *
 * [ turnRight() ]
 * 6. 명령이 R 인 경우 호출
 *  6-1. 해당 로봇의 번호와 횟수를 전달받아 오른쪽으로 회전한다.
 *  6-2. 현재 방향에 +1 % 4
 *
 * [ moveRobot() ]
 * 7. 명령이 F 인 경우 호출
 *  7-1. 해당 로봇의 번호와 횟수를 전달받아 현재 방향만큼 이동한다.
 *      7-1-1. 인덱스를 벗어나면 잘못된 명령임을 출력하고 flag 를 false 로 전환 후 종료한다.
 *      7-1-2. 다음 인덱스에 다른 로봇이 있다면 잘못된 명령임을 출력하고 flag 를 false 로 전환 후 종료한다.
 */
public class Main {
    static class Robot {
        int row;
        int col;
        int deltaIdx;

        public Robot() {
        }

        public Robot(int row, int col, int deltaIdx) {
            this.row = row;
            this.col = col;
            this.deltaIdx = deltaIdx;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int colSize, rowSize;
    static int robotCount, orderCount;
    static int[][] map;
    static Robot[] robotList;

    static boolean errorFlag;

    static final int[] DELTA_ROW = {-1, 0, 1, 0};
    static final int[] DELTA_COL = {0, 1, 0, -1};

    public static void turnLeft(int robotIdx, int commandCount) {
        for (int turn = 0; turn < commandCount; turn++) {
            robotList[robotIdx].deltaIdx--;
            if (robotList[robotIdx].deltaIdx < 0)
                robotList[robotIdx].deltaIdx = 3;
        }
    }

    public static void turnRight(int robotIdx, int commandCount) {
        for (int turn = 0; turn < commandCount; turn++) {
            robotList[robotIdx].deltaIdx = (robotList[robotIdx].deltaIdx + 1) % 4;
        }
    }

    public static void moveRobot(int robotIdx, int commandCount) {
        map[robotList[robotIdx].row][robotList[robotIdx].col] = 0;

        for (int move = 0; move < commandCount; move++) {
            robotList[robotIdx].row = robotList[robotIdx].row + DELTA_ROW[robotList[robotIdx].deltaIdx];
            robotList[robotIdx].col = robotList[robotIdx].col + DELTA_COL[robotList[robotIdx].deltaIdx];

            if (isArrange(robotList[robotIdx].row, robotList[robotIdx].col)) {
                errorFlag = false;
                sb.append("Robot ").append(robotIdx).append(" crashes into the wall");
                return;
            }

            if (map[robotList[robotIdx].row][robotList[robotIdx].col] > 0) {
                errorFlag = false;
                sb.append("Robot ").append(robotIdx).append(" crashes into robot ").append(map[robotList[robotIdx].row][robotList[robotIdx].col]);
                return;
            }
        }

        map[robotList[robotIdx].row][robotList[robotIdx].col] = robotIdx;
    }

    public static boolean isArrange(int row, int col) {
        return row < 1 || col < 1 || row > rowSize || col > colSize;
    }

    public static void main(String[] args) throws IOException {
        inputTestCase();

        System.out.println(sb);
    }

    public static void inputTestCase() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        colSize = Integer.parseInt(st.nextToken());
        rowSize = Integer.parseInt(st.nextToken());
        map = new int[rowSize+1][colSize+1];

        st = new StringTokenizer(br.readLine().trim());
        robotCount = Integer.parseInt(st.nextToken());
        orderCount = Integer.parseInt(st.nextToken());

        robotList = new Robot[robotCount+1];
        robotList[0] = new Robot();
        for (int robotIdx = 1; robotIdx < robotCount+1; robotIdx++) {
            st = new StringTokenizer(br.readLine().trim());
            int col = Integer.parseInt(st.nextToken());
            int row = rowSize - Integer.parseInt(st.nextToken()) + 1;
            int deltaIdx = -1;
            switch (st.nextToken()) {
                case "N" : deltaIdx = 0; break;
                case "E" : deltaIdx = 1; break;
                case "S" : deltaIdx = 2; break;
                case "W" : deltaIdx = 3; break;
            }

            robotList[robotIdx] = new Robot(row, col, deltaIdx);
            map[row][col] = robotIdx;
        }

        errorFlag = true;
        for (int order = 0; order < orderCount; order++) {
            st = new StringTokenizer(br.readLine().trim());
            int robotIdx = Integer.parseInt(st.nextToken());
            String command = st.nextToken();
            int commandCount = Integer.parseInt(st.nextToken());

            switch (command) {
                case "L" : turnLeft(robotIdx, commandCount); break;
                case "R" : turnRight(robotIdx, commandCount); break;
                case "F" : moveRobot(robotIdx, commandCount); break;
            }

            if (!errorFlag) {
                return;
            }
        }

        sb.append("OK");
    }
}